package com.bachelor.logiword.server.dao.daily_challenge_attempt;

import com.bachelor.logiword.server.model.daily_challenge_attempt.DailyChallengeAttempt;
import com.bachelor.logiword.server.model.daily_challenge_attempt.DailyChallengeAttemptFromUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository("attemptEm")
public class DailyChallengeAttemptDataAccess implements DailyChallengeAttemptDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void insertAttempt(DailyChallengeAttemptFromUser challengeAttempt) {
        List challengeIdList = em.createNativeQuery("select ID from(" +
                "select id from D_DAILY_CHALLENGE " +
                "where WORD = ? order by VALID_TO desc) " +
                "where ROWNUM = 1")
                .setParameter(1, challengeAttempt.getWord())
                .getResultList();

        if(challengeIdList.isEmpty()){
            return;
        }

        int challengeId = ((BigDecimal)challengeIdList.get(0)).intValue();

        DailyChallengeAttempt attempt = new DailyChallengeAttempt(
                challengeAttempt.getPlayerId(),
                challengeId,
                challengeAttempt.getScore(),
                Character.toUpperCase(challengeAttempt.getIsSuccessful()));

        em.persist(attempt);
    }
}
