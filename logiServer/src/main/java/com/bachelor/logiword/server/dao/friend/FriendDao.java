package com.bachelor.logiword.server.dao.friend;

import com.bachelor.logiword.server.model.friend.FriendPair;

import java.util.List;

public interface FriendDao {

    void makeFriendRequest(FriendPair request);

    List getFriendRequests(int playerId);

//    void responseToRequest()
}
