package com.kyluandkylu.android.logiword.Retrofit;

import java.sql.Date;

public class User {

    private int playerID;
    private String username;
    private String password;
    private String mail;
    private Date from;

    public User(int playerID, String username, String password, String mail, Date from) {
        this.playerID = playerID;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.from = from;
    }

    public int getPlayerID() {
        return playerID;
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

    public Date getFrom() {
        return from;
    }
}
