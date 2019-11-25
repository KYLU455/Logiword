package com.bachelor.logiword.server.dao;

import com.bachelor.logiword.server.model.single_player.SinglePlayerGame;
import com.bachelor.logiword.server.model.single_player.SinglePlayerGameData;

import java.util.List;

public interface SinglePlayerDao {
    int insertGame(SinglePlayerGame game);

    List<SinglePlayerGameData> getAllSinglePlayerGames();

    List<SinglePlayerGameData> getGamesByUser(int playerId);
}
