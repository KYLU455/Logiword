package com.kyluandkylu.android.logiword.Retrofit;

import java.sql.Date;
import java.sql.Timestamp;

public class GameResults {

    private int playerId;
    private String wordCreated;
    private int score;
    private Timestamp from;
    private Timestamp to;

    public GameResults(int playerId, String wordCreated, int score, Timestamp from, Timestamp to) {
        this.playerId = playerId;
        this.wordCreated = wordCreated;
        this.score = score;
        this.from = from;
        this.to = to;
    }
}
