package com.bachelor.logiword.server.dao.friend;

import com.bachelor.logiword.server.model.friend.Friend;
import com.bachelor.logiword.server.model.friend.FriendPair;
import com.bachelor.logiword.server.model.friend.FriendRequest;
import com.bachelor.logiword.server.model.friend.FriendResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Repository("friendEm")
public class FriendDataAccess implements FriendDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void makeFriendRequest(FriendPair request) {
        int friendId = findPlayerId(request.getFriendName());

        if(friendId == -1){
            return;
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());

        FriendRequest friendRequest = new FriendRequest(null, now, null);

        em.persist(friendRequest);
        em.flush();

        Friend friends = new Friend(friendRequest.getId(), request.getPlayerId(), friendId);
        em.persist(friends);
    }

    @Override
    public List getFriendRequests(int playerId) {
        return em.createNativeQuery("select PLAYER_NAME from D_FRIEND_REQUEST req " +
                "inner join F_FRIEND frnd " +
                "on req.ID = frnd.FRIEND_REQUEST_ID " +
                "inner join D_PLAYER plyr " +
                "on frnd.PLAYER1_ID = plyr.PLAYER_ID " +
                "and plyr.VALID_TO is null " +
                "and frnd.PLAYER2_ID = ? " +
                "and req.STATUS is null ")
                .setParameter(1, playerId)
                .getResultList();
    }

    @Transactional
    @Override
    public void responseToRequest(FriendResponse response) {
        int friendId = findPlayerId(response.getFriendName());

        if(friendId == -1){
            return;
        }

        Timestamp sysdate = null;

        String status = response.getResponse().toUpperCase();
        if(status.equals("REJECTED")){
            sysdate = new Timestamp(System.currentTimeMillis());
        } else if(!status.equals("ACCEPTED")){
            return;
        }

        int requestId = findRequestId(response.getPlayerId(), friendId);

        if(requestId == -1){
            return;
        }

        if(sysdate == null) {
            em.createNativeQuery("update D_FRIEND_REQUEST " +
                    "set STATUS = ? " +
                    "where ID = ?")
                    .setParameter(1, status)
                    .setParameter(2, requestId)
                    .executeUpdate();
        } else {
            em.createNativeQuery("update D_FRIEND_REQUEST " +
                    "set STATUS = ?, VALID_TO = ? " +
                    "where ID = ?")
                    .setParameter(1, status)
                    .setParameter(2, sysdate)
                    .setParameter(3, requestId)
                    .executeUpdate();
        }
    }

    public List getFriends(int playerId){
        List firstPartOfFriends = em.createNativeQuery("select PLAYER_NAME from D_FRIEND_REQUEST req " +
                "inner join F_FRIEND frnd " +
                "on req.ID = frnd.FRIEND_REQUEST_ID " +
                "inner join D_PLAYER plyr " +
                "on frnd.PLAYER1_ID = plyr.PLAYER_ID " +
                "and plyr.VALID_TO is null " +
                "and frnd.PLAYER2_ID = ? " +
                "and req.STATUS = 'ACCEPTED' " +
                "and req.VALID_TO is null ")
                .setParameter(1, playerId)
                .getResultList();

        List secondPartOfFriends = em.createNativeQuery("select PLAYER_NAME from D_FRIEND_REQUEST req " +
                "inner join F_FRIEND frnd " +
                "on req.ID = frnd.FRIEND_REQUEST_ID " +
                "inner join D_PLAYER plyr " +
                "on frnd.PLAYER2_ID = plyr.PLAYER_ID " +
                "and plyr.VALID_TO is null " +
                "and frnd.PLAYER1_ID = ? " +
                "and req.STATUS = 'ACCEPTED' " +
                "and req.VALID_TO is null ")
                .setParameter(1, playerId)
                .getResultList();

        firstPartOfFriends.addAll(secondPartOfFriends);
        return firstPartOfFriends;
    }

    @Transactional
    @Override
    public void removeFriend(FriendPair request) {
        int friendId = findPlayerId(request.getFriendName());

        if(friendId == -1){
            return;
        }

        int requestId = findRequestId(request.getPlayerId(), friendId);

        if(requestId == -1){
            requestId = findRequestId(friendId, request.getPlayerId());

            if(requestId == -1){
                return;
            }
        }

        em.createNativeQuery("update D_FRIEND_REQUEST " +
                "set VALID_TO = sysdate " +
                "where ID = ? " +
                "and STATUS = 'ACCEPTED' " +
                "and VALID_TO is null ")
                .setParameter(1, requestId)
                .executeUpdate();
    }

    private int findPlayerId(String name){
        List playerIds = em.createNativeQuery("select PLAYER_ID from D_PLAYER " +
                "where PLAYER_NAME = ? " +
                "and VALID_TO is null")
                .setParameter(1, name)
                .getResultList();

        if(playerIds.isEmpty()){
            return -1;
        }

        return ((BigDecimal)playerIds.get(0)).intValue();
    }

    private int findRequestId(int playerId, int friendId){
        List requestIds = em.createNativeQuery("select FRIEND_REQUEST_ID from F_FRIEND frnd " +
                "where frnd.PLAYER1_ID = ? " +
                "and frnd.PLAYER2_ID = ?")
                .setParameter(1, friendId)
                .setParameter(2, playerId)
                .getResultList();

        if(requestIds.isEmpty()){
            return -1;
        }

        return ((BigDecimal)requestIds.get(0)).intValue();
    }
}
