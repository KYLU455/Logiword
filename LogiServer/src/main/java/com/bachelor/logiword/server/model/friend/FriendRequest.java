package com.bachelor.logiword.server.model.friend;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "D_FRIEND_REQUEST")
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friendRequestGenerator")
    @SequenceGenerator(name = "friendRequestGenerator", sequenceName = "D_FRIEND_REQUEST_ID", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    private int id;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "VALID_FROM")
    private Timestamp from;

    @Column(name = "VALID_TO")
    private Timestamp to;

    public FriendRequest() {
    }

    public FriendRequest(String status, Timestamp from, Timestamp to) {
        this.status = status;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getFrom() {
        return from;
    }

    public Timestamp getTo() {
        return to;
    }
}
