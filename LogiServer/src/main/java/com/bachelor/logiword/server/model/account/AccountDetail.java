package com.bachelor.logiword.server.model.account;

import java.sql.Timestamp;

public class AccountDetail {

    private String username;
    private Timestamp from;

    public AccountDetail() {
    }

    public AccountDetail(String username, Timestamp from) {
        this.username = username;
        this.from = from;
    }


    public String getUsername() {
        return username;
    }

    public Timestamp getFrom() {
        return from;
    }

}
