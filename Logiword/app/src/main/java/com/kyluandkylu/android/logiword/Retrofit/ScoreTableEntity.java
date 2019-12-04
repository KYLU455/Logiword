package com.kyluandkylu.android.logiword.Retrofit;

public class ScoreTableEntity {

    private String playerName;
    private String wordCreated;
    private int score;

    public ScoreTableEntity(String playerName, String wordCreated, int score) {
        this.playerName = playerName;
        this.wordCreated = wordCreated;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getWordCreated() {
        return wordCreated;
    }

    public int getScore() {
        return score;
    }
}
