package com.bachelor.logiword.server.service;

import com.bachelor.logiword.server.dao.SinglePlayerDao;
import com.bachelor.logiword.server.model.single_player.SinglePlayerGame;
import com.bachelor.logiword.server.model.single_player.SinglePlayerGameData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinglePlayerGameService {

    private final SinglePlayerDao singlePlayerDao;

    @Autowired
    public SinglePlayerGameService(@Qualifier("em") SinglePlayerDao singlePlayerDao) {
        this.singlePlayerDao = singlePlayerDao;
    }

    public int addGame(SinglePlayerGame game){
        return singlePlayerDao.insertGame(game);
    }

    public List<SinglePlayerGameData> getAllSinglePlayerGames(){
        return singlePlayerDao.getAllSinglePlayerGames();
    }

    public List<SinglePlayerGameData> getGamesByUser(int id){
        return singlePlayerDao.getGamesByUser(id);
    }
}
