package com.bachelor.logiword.server.service;

import com.bachelor.logiword.server.dao.daily_challenge_word.DailyChallengeWordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DailyChallengeWordService {

    private final DailyChallengeWordDao wordDao;

    @Autowired
    public DailyChallengeWordService(@Qualifier("wordEm") DailyChallengeWordDao wordDao){
        this.wordDao = wordDao;
    }

    public String getDailyChallenge(){
        return wordDao.getTodaysWord();
    }
}
