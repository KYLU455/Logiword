package com.bachelor.logiword.server.model.single_player;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class SinglePlayerGame {

    private int playerId;
    private String wordCreated;
    private int score;
    private Timestamp from;
    private Timestamp to;

    public SinglePlayerGame(){}

    public SinglePlayerGame(@JsonProperty("playerId") int playerId,
                            @JsonProperty("wordCreated") String wordCreated,
                            @JsonProperty("score") int score,
                            @JsonProperty("from") Timestamp from,
                            @JsonProperty("to") Timestamp to) {
        this.playerId = playerId;
        this.wordCreated = wordCreated;
        this.score = score;
        this.from = from;
        this.to = to;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getWordCreated() {
        return wordCreated;
    }

    public int getScore() {
        return score;
    }

    public Timestamp getFrom() {
        return from;
    }

    public Timestamp getTo() {
        return to;
    }

}
