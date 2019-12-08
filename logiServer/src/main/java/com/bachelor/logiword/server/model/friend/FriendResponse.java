package com.bachelor.logiword.server.model.friend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FriendResponse {

    private int playerId;
    private String friendName;
    private String response;

    public FriendResponse() {
    }

    public FriendResponse(@JsonProperty("playerId") int playerId,
                          @JsonProperty("friendName") String friendName,
                          @JsonProperty("response") String response) {
        this.playerId = playerId;
        this.friendName = friendName;
        this.response = response;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getFriendName() {
        return friendName;
    }

    public String getResponse() {
        return response;
    }
}
