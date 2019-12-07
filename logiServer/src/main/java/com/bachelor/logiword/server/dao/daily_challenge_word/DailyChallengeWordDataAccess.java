package com.bachelor.logiword.server.dao.daily_challenge_word;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository("wordEm")
public class DailyChallengeWordDataAccess implements DailyChallengeWordDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
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

       return generateDailyWord();
    }

    private String generateDailyWord(){
        List calculateDailyWord = em.createNativeQuery("select get_daily_challenge_for_today() " +
                "from dual")
                .getResultList();

        if(!calculateDailyWord.isEmpty()){
            em.createNativeQuery("call INSERT_DAILY_CHALLENGE_FOR_TODAY(?)")
                    .setParameter(1, calculateDailyWord.get(0))
                    .executeUpdate();
            return (String) calculateDailyWord.get(0);
        }

        return generateFromHardcodedValue();
    }

    private String generateFromHardcodedValue(){
        List<String> lastResortWords = new ArrayList<>();
        lastResortWords.add("merry");
        lastResortWords.add("christmas");
        lastResortWords.add("and");
        lastResortWords.add("happy");
        lastResortWords.add("new");
        lastResortWords.add("year");

        List yesterdaysWord = em.createNativeQuery("select word " +
                "from D_DAILY_CHALLENGE " +
                "where VALID_FROM >= TRUNC(sysdate - 1) " +
                "and VALID_TO <= TRUNC(sysdate) - 1 / 86400")
                .getResultList();

        if(!yesterdaysWord.isEmpty()){
            lastResortWords.remove(yesterdaysWord.get(0));
        }

        int randIndex = (int) Math.floor(Math.random()*lastResortWords.size());

        String randWord = lastResortWords.get(randIndex);

        em.createNativeQuery("call INSERT_DAILY_CHALLENGE_FOR_TODAY(?)")
                .setParameter(1, randWord)
                .executeUpdate();

        return randWord;
    }
}
