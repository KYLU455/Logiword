package com.bachelor.logiword.server.service;

import com.bachelor.logiword.server.dao.SinglePlayerDao;
import com.bachelor.logiword.server.model.SinglePlayerGame;
import com.bachelor.logiword.server.model.SinglePlayerGameData;
import com.bachelor.logiword.server.model.SinglePlayerGameInterval;
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
        SinglePlayerGameInterval interval = new SinglePlayerGameInterval(game.getFrom(), game.getTo());
//        SinglePlayerGameInterval dbInterval = singlePlayerIntervalDao.save(interval);
//        singlePlayerIntervalDao.flush();
//        SinglePlayerGameData data = new SinglePlayerGameData(dbInterval.getId(), game.getPlayerId(), game.getWordCreated(), game.getScore());

        return singlePlayerDao.insertGame(game);
    }

//    public List<SinglePlayerGameData> getAllSinglePlayerGames(){
//        return singlePlayerDao.findAll();
//    }

//    public List<SinglePlayerGame> getGamesByUser(int id){
//        return singlePlayerDao.getGamesByUser(id);
//    }
}
