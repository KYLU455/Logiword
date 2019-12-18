package com.bachelor.logiword.server.dao.daily_challenge_attempt;

import com.bachelor.logiword.server.model.daily_challenge_attempt.DailyChallengeAttemptFromUser;

public interface DailyChallengeAttemptDao {

    void insertAttempt(DailyChallengeAttemptFromUser challengeAttempt);

}
