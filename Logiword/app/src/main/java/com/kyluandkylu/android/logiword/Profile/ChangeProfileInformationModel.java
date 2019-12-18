package com.kyluandkylu.android.logiword.Profile;

public class ChangeProfileInformationModel {
    private int playerId;
    private String username;

    public ChangeProfileInformationModel(int playerId, String username) {
        this.playerId = playerId;
        this.username = username;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getUsername() {
        return username;
    }
}
