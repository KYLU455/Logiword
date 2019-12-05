package com.bachelor.logiword.server.model.single_player;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

// This annotation is not connected to this model, but it had to be declared in a class that is an entity as well
@SqlResultSetMapping(name = "SinglePlayerWithName", classes = {
        @ConstructorResult(targetClass = SinglePlayerGameDataWithPlayerName.class,
                columns = {
                        @ColumnResult(name = "PLAYER_NAME", type = String.class),
                        @ColumnResult(name = "WORD_CREATED", type = String.class),
                        @ColumnResult(name = "SCORE", type = Integer.class)
                })
})

@Entity
@Table(name = "D_SINGLE_PLAYER_GAME")
public class SinglePlayerGameInterval {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "singlePlayerGameGenerator")
    @SequenceGenerator(name = "singlePlayerGameGenerator", sequenceName = "D_SINGLE_PLAYER_ID", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    private int id;

    @Column(name = "START_TIME")
    private Timestamp from;

    @Column(name = "END_TIME")
    private Timestamp to;

    public SinglePlayerGameInterval(){}

    public SinglePlayerGameInterval(@JsonProperty("validFrom") Timestamp from,
                                      @JsonProperty("validTo") Timestamp to){
        this.from = from;
        this.to = to;
    }

    public SinglePlayerGameInterval(int id,
                                    @JsonProperty("validFrom") Timestamp from,
                                    @JsonProperty("validTo") Timestamp to){
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public Timestamp getFrom() {
        return from;
    }

    public Timestamp getTo() {
        return to;
    }

}
