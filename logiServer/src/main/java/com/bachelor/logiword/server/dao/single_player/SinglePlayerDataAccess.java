package com.bachelor.logiword.server.dao.single_player;

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
        return em.createNativeQuery("select PLAYER_NAME, WORD_CREATED, SCORE from (select * from D_SINGLE_PLAYER_GAME inner join F_SINGLE_PLAYER_GAME FSPG on D_SINGLE_PLAYER_GAME.ID = FSPG.GAME_ID inner join D_PLAYER on FSPG.PLAYER_ID = D_PLAYER.ROW_ID and D_PLAYER.VALID_TO is null order by SCORE desc) where ROWNUM <= 10", "SinglePlayerWithName").getResultList();
    }

    @Override
    public List<SinglePlayerGameData> getGamesByUser(int playerId) {
        return em.createQuery("select FSPG from  " +
                "com.bachelor.logiword.server.model.single_player.SinglePlayerGameData FSPG " +
                "where FSPG.playerId=" + playerId +
                " order by SCORE desc", SinglePlayerGameData.class).getResultList();
    }
}
