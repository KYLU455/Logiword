package com.bachelor.logiword.server.model.account;

import java.sql.Timestamp;

public class AccountDetail {

    private String username;
    private String mail;
    private Timestamp from;

    public AccountDetail() {
    }

    public AccountDetail(String username, String mail, Timestamp from) {
        this.username = username;
        this.mail = mail;
        this.from = from;
    }


    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public Timestamp getFrom() {
        return from;
    }

}
