package com.kyluandkylu.android.logiword.Game;

public class DailyChallengeAttemptModel {

    private int playerId;
    private String word;
    private int score;
    private char isSuccessful;

    public DailyChallengeAttemptModel(int playerId, String word, int score, char isSuccessful) {
        this.playerId = playerId;
        this.word = word;
        this.score = score;
        this.isSuccessful = isSuccessful;
    }
}
