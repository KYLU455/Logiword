package com.bachelor.logiword.server.service;


import com.bachelor.logiword.server.dao.daily_challenge_attempt.DailyChallengeAttemptDao;
import com.bachelor.logiword.server.model.daily_challenge_attempt.DailyChallengeAttemptFromUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DailyChallengeAttemptService {

    private final DailyChallengeAttemptDao attemptDao;

    @Autowired
    public DailyChallengeAttemptService(@Qualifier("attemptEm") DailyChallengeAttemptDao attemptDao) {
        this.attemptDao = attemptDao;
    }

    public void insertAttempt(DailyChallengeAttemptFromUser attempt){
        attemptDao.insertAttempt(attempt);
    }
}
