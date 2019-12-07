package com.kyluandkylu.android.logiword.Retrofit;

public class DailyChallengeAttempt {

    private int playerId;
    private String word;
    private int score;
    private char isSuccessful;

    public DailyChallengeAttempt(int playerId, String word, int score, char isSuccessful) {
        this.playerId = playerId;
        this.word = word;
        this.score = score;
        this.isSuccessful = isSuccessful;
    }
}
