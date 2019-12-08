package com.bachelor.logiword.server.model.friend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "F_FRIEND")
public class Friend {

    @Id
    @Column(name = "FRIEND_REQUEST_ID")
    private int friendRequestId;

    @Column(name = "PLAYER1_ID")
    private int playerId;

    @Column(name = "PLAYER2_ID")
    private int friendId;

    public Friend() {
    }

    public Friend(int friendRequestId, int playerId, int friendId) {
        this.friendRequestId = friendRequestId;
        this.playerId = playerId;
        this.friendId = friendId;
    }

    public int getFriendRequestId() {
        return friendRequestId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getFriendId() {
        return friendId;
    }
}
