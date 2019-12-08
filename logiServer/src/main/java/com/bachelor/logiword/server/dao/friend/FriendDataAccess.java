package com.bachelor.logiword.server.dao.friend;

import com.bachelor.logiword.server.model.friend.Friend;
import com.bachelor.logiword.server.model.friend.FriendPair;
import com.bachelor.logiword.server.model.friend.FriendRequest;
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
        List friendIds = em.createNativeQuery("select PLAYER_ID from D_PLAYER " +
                "where PLAYER_NAME = ?")
                .setParameter(1, request.getFriendName())
                .getResultList();

        if(friendIds.isEmpty()){
            return;
        }

        int friendId = ((BigDecimal)friendIds.get(0)).intValue();

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
                "and frnd.PLAYER2_ID = ?")
                .setParameter(1, playerId)
                .getResultList();
    }
}
