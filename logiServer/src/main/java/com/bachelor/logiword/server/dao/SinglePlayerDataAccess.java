package com.bachelor.logiword.server.dao;

import com.bachelor.logiword.server.model.SinglePlayerGame;
import com.bachelor.logiword.server.model.SinglePlayerGameData;
import com.bachelor.logiword.server.model.SinglePlayerGameInterval;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository("em")
public class SinglePlayerDataAccess implements SinglePlayerDao {
    @PersistenceContext
    protected EntityManager em;

    @Override
    @Transactional
    public int insertGame(SinglePlayerGame game) {
        SinglePlayerGameInterval interval = new SinglePlayerGameInterval(game.getFrom(), game.getTo());
//        em.createNativeQuery("INSERT INTO D_SINGLE_PLAYER_GAME (ID, START_TIME, END_TIME) VALUES (D_SINGLE_PLAYER_ID.nextval, ?, ?)")
//                .setParameter(1, interval.getFrom())
//                .setParameter(2, interval.getTo())
//                .executeUpdate();
        em.persist(interval);
        em.flush();

        SinglePlayerGameData gameData = new SinglePlayerGameData(interval.getId(), game.getPlayerId(), game.getWordCreated(), game.getScore());
        em.persist(gameData);

        return 1;
    }
}
