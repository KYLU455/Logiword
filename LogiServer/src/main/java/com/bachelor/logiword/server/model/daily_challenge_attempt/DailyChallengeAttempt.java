package com.bachelor.logiword.server.model.daily_challenge_attempt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="F_DAILY_CHALLENGE_ATTEMPT")
public class DailyChallengeAttempt implements Serializable {

    @Id
    @Column(name = "PLAYER_ID")
    private int playerId;

    @Id
    @Column(name = "CHALLENGE_ID")
    private int challengeId;

    @Column(name = "SCORE_NUMBER")
    private int score;

    @Column(name = "IS_SUCCESSFUL")
    private Character isSuccessful;

    public DailyChallengeAttempt(int playerId, int challengeId, int score, Character isSuccessful) {
        this.playerId = playerId;
        this.challengeId = challengeId;
        this.score = score;
        this.isSuccessful = isSuccessful;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getChallengeId() {
        return challengeId;
    }

    public int getScore() {
        return score;
    }

    public Character getIsSuccessful() {
        return isSuccessful;
    }
}
