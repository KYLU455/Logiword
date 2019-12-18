package com.bachelor.logiword.server.model.friend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FriendPair {

    private int playerId;
    private String friendName;

    public FriendPair() {
    }

    public FriendPair(@JsonProperty("playerId") int playerId,
                      @JsonProperty("friendName") String friendName) {
        this.playerId = playerId;
        this.friendName = friendName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getFriendName() {
        return friendName;
    }
}
