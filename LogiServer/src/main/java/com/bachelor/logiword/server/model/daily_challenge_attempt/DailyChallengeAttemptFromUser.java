package com.bachelor.logiword.server.model.daily_challenge_attempt;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyChallengeAttemptFromUser {

    private int playerId;
    private String word;
    private int score;
    private Character isSuccessful;

    public DailyChallengeAttemptFromUser(@JsonProperty("playerId") int playerId,
                                         @JsonProperty("word") String word,
                                         @JsonProperty("score") int score,
                                         @JsonProperty("isSuccessful") Character isSuccessful) {
        this.playerId = playerId;
        this.word = word;
        this.score = score;
        this.isSuccessful = isSuccessful;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getWord() {
        return word;
    }

    public int getScore() {
        return score;
    }

    public Character getIsSuccessful() {
        return isSuccessful;
    }
}
