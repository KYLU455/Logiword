package com.bachelor.logiword.server.model.account;

public class AccountUpdate {

    private int playerId;
    private String username;

    public AccountUpdate(int playerId, String username) {
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
