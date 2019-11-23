package com.bachelor.logiword.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "F_SINGLE_PLAYER_GAME")
public class SinglePlayerGame {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GAME_ID", updatable = false, nullable = false)
    private int gameId;

    @Column(name = "PLAYER_ID")
    private int playerId;

    @Column(name = "WORD_CREATED")
    private String wordCreated;

    @Column(name = "SCORE")
    private int score;

    public SinglePlayerGame(){}

    public SinglePlayerGame(@JsonProperty("gameId") int gameId,
                            @JsonProperty("playerId") int playerId,
                            @JsonProperty("wordCreated") String wordCreated,
                            @JsonProperty("score") int score) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.wordCreated = wordCreated;
        this.score = score;
    }

    public int getGameId() {
        return gameId;
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


}
