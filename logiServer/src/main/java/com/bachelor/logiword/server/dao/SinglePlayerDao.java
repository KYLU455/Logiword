package com.bachelor.logiword.server.dao;

import com.bachelor.logiword.server.model.SinglePlayerGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SinglePlayerDao extends JpaRepository<SinglePlayerGame, Integer> {
//    int insertGame(SinglePlayerGame game);
//
//    List<SinglePlayerGame> getAllSinglePlayerGames();
//
//    List<SinglePlayerGame> getGamesByUser(int playerId);
}
