package com.kyluandkylu.android.logiword.Game;

import java.sql.Timestamp;

public class GameResultsModel {

    private int playerId;
    private String wordCreated;
    private int score;
    private Timestamp from;
    private Timestamp to;

    public GameResultsModel(int playerId, String wordCreated, int score, Timestamp from, Timestamp to) {
        this.playerId = playerId;
        this.wordCreated = wordCreated;
        this.score = score;
        this.from = from;
        this.to = to;
    }
}
