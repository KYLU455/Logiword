package com.bachelor.logiword.server.dao.single_player;

import com.bachelor.logiword.server.model.single_player.SinglePlayerGame;
import com.bachelor.logiword.server.model.single_player.SinglePlayerGameData;
import com.bachelor.logiword.server.model.single_player.SinglePlayerGameDataWithPlayerName;

import java.util.List;

public interface SinglePlayerDao {
    int insertGame(SinglePlayerGame game);

    List<SinglePlayerGameDataWithPlayerName> getAllSinglePlayerGames();

    List<SinglePlayerGameData> getGamesByUser(int playerId);
}
