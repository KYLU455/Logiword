package com.bachelor.logiword.server.dao;

import com.bachelor.logiword.server.model.SinglePlayerGame;
import com.bachelor.logiword.server.model.SinglePlayerGameData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SinglePlayerDao {
    int insertGame(SinglePlayerGame game);

//    List<SinglePlayerGame> getAllSinglePlayerGames();
//
//    List<SinglePlayerGame> getGamesByUser(int playerId);
}
