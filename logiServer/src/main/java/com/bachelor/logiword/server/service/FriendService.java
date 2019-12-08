package com.bachelor.logiword.server.service;

import com.bachelor.logiword.server.dao.friend.FriendDao;
import com.bachelor.logiword.server.model.friend.FriendPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    private final FriendDao friendDao;

    @Autowired
    public FriendService(@Qualifier("friendEm") FriendDao friendDao) {
        this.friendDao = friendDao;
    }

    public void makeFriendRequest(FriendPair pair){
        friendDao.makeFriendRequest(pair);
    }

    public List getFriendRequests(int playerId){
        return friendDao.getFriendRequests(playerId);
    }
}
