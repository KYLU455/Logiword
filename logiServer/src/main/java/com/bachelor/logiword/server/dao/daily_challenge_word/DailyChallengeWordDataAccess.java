package com.bachelor.logiword.server.dao.daily_challenge_word;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("wordEm")
public class DailyChallengeWordDataAccess implements DailyChallengeWordDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public String getTodaysWord() {
        List alreadyInTheTable = em.createNativeQuery("select word " +
                "from D_DAILY_CHALLENGE " +
                "where VALID_FROM >= TRUNC(sysdate) " +
                "and VALID_TO <= TRUNC(sysdate + 1) - 1 / 86400")
                .getResultList();

        if(!alreadyInTheTable.isEmpty()){
            return (String) alreadyInTheTable.get(0);
        }

        List calculateDailyWord = em.createNativeQuery("select get_daily_challenge_for_today() " +
                "from dual")
                .getResultList();

        if(!calculateDailyWord.isEmpty()){
            return (String) calculateDailyWord.get(0);
        }

        String[] lastResortWords = new String[]{
                "merry",
                "christmas",
                "and",
                "happy",
                "new",
                "year"
        };

        return "asd";
    }
}
