package com.kyluandkylu.android.logiword.LocalScore;

public class LocalScoreModel {
    private int gameId;
    private int playerId;
    private String wordCreated;
    private int score;

    public LocalScoreModel(int gameId, int playerId, String wordCreated, int score) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.wordCreated = wordCreated;
        this.score = score;
    }

    public String getWordCreated() {
        return wordCreated;
    }

    public int getScore() {
        return score;
    }

    public String getStringScore() {
        return String.valueOf(getScore());
    }
}
