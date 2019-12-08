package com.kyluandkylu.android.logiword.Retrofit;

public class FriendResponse {

    private int playerId;
    private String friendName;
    private String response;

    public FriendResponse(int playerId, String friendName, String response) {
        this.playerId = playerId;
        this.friendName = friendName;
        this.response = response;
    }
}
