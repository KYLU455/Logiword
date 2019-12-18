package com.kyluandkylu.android.logiword.FriendList;

public class FriendResponseModel {

    private int playerId;
    private String friendName;
    private String response;

    public FriendResponseModel(int playerId, String friendName, String response) {
        this.playerId = playerId;
        this.friendName = friendName;
        this.response = response;
    }
}
