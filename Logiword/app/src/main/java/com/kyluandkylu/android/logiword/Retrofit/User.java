package com.kyluandkylu.android.logiword.Retrofit;

import java.sql.Date;
import java.sql.Timestamp;

public class User {

    private int playerId;
    private String username;
    private String password;
    private String mail;
    private Timestamp from;

    public User(int playerId, String username, String password, String mail, Timestamp from) {
        this.playerId = playerId;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.from = from;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }
}
