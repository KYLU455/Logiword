package com.kyluandkylu.android.logiword.Score;

public class ScoreModel {

    private String playerName;
    private String wordCreated;
    private int score;

    public ScoreModel(String playerName, String wordCreated, int score) {
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

    public String getStringScore()
    {
       return String.valueOf(getScore());
    }
}
