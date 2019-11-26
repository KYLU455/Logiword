package com.bachelor.logiword.server.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "D_PLAYER")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rowGenerator")
    @SequenceGenerator(name = "rowGenerator", sequenceName = "D_PLAYER_ROW_ID", allocationSize = 1)
    @Column(name = "ROW_ID", updatable = false, nullable = false)
    private int rowId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playerGenerator")
    @SequenceGenerator(name = "playerGenerator", sequenceName = "D_PLAYER_PLAYER_ID", allocationSize = 1)
    @Column(name = "PLAYER_ID", updatable = false, nullable = false)
    private int playerId;

    @Column(name = "PLAYER_NAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "VALID_FROM")
    private Date from;

    @Column(name = "VALID_TO")
    private Date to;


    public Account() {
    }

    public Account(@JsonProperty("username") String username,
                   @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public Account(@JsonProperty("username") String username,
                   @JsonProperty("password") String password,
                   @JsonProperty("mail") String mail,
                   @JsonProperty("from") Date from) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.from = from;
    }

    public Account(@JsonProperty("playerId") int playerId,
                   @JsonProperty("username") String username,
                   @JsonProperty("password") String password,
                   @JsonProperty("mail") String mail,
                   @JsonProperty("from") Date from) {
        this.playerId = playerId;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.from = from;
    }
}
