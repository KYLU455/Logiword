package com.bachelor.logiword.server.dao;

import com.bachelor.logiword.server.model.single_player.SinglePlayerGame;
import com.bachelor.logiword.server.model.single_player.SinglePlayerGameData;
import com.bachelor.logiword.server.model.single_player.SinglePlayerGameDataWithPlayerName;
import com.bachelor.logiword.server.model.single_player.SinglePlayerGameInterval;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("em")
public class SinglePlayerDataAccess implements SinglePlayerDao {
    @PersistenceContext
    protected EntityManager em;

    @Override
    @Transactional
    public int insertGame(SinglePlayerGame game) {
        SinglePlayerGameInterval interval = new SinglePlayerGameInterval(game.getFrom(), game.getTo());
        em.persist(interval);
        em.flush();

        SinglePlayerGameData gameData = new SinglePlayerGameData(interval.getId(), game.getPlayerId(), game.getWordCreated(), game.getScore());
        em.persist(gameData);

        return 1;
    }

    @Override
    public List<SinglePlayerGameDataWithPlayerName> getAllSinglePlayerGames() {
        return em.createQuery("select new com.bachelor.logiword.server.model.single_player.SinglePlayerGameDataWithPlayerName(dbPlayer.username, FSPG.wordCreated, FSPG.score) " +
                "from com.bachelor.logiword.server.model.single_player.SinglePlayerGameInterval DSPG " +
                "inner join com.bachelor.logiword.server.model.single_player.SinglePlayerGameData FSPG on DSPG.id = FSPG.gameId " +
                "inner join com.bachelor.logiword.server.model.account.Account dbPlayer on FSPG.playerId = dbPlayer.rowId and dbPlayer.to is null " +
                "where END_TIME < sysdate and ROWNUM <= 10 " +
                "order by SCORE desc", SinglePlayerGameDataWithPlayerName.class).getResultList();
    }

    @Override
    public List<SinglePlayerGameData> getGamesByUser(int playerId) {
        return em.createQuery("select FSPG from  " +
                "com.bachelor.logiword.server.model.single_player.SinglePlayerGameData FSPG " +
                "where FSPG.playerId=" + playerId +
                " order by SCORE desc", SinglePlayerGameData.class).getResultList();
    }
}
