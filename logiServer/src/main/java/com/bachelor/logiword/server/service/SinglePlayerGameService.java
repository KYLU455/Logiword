package com.bachelor.logiword.server.service;

import com.bachelor.logiword.server.dao.SinglePlayerDao;
import com.bachelor.logiword.server.model.SinglePlayerGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinglePlayerGameService {

    private final SinglePlayerDao singlePlayerDao;

    @Autowired
    public SinglePlayerGameService(SinglePlayerDao singlePlayerDao) {
        this.singlePlayerDao = singlePlayerDao;
    }

    public SinglePlayerGame addGame(SinglePlayerGame game){
        return singlePlayerDao.save(game);
    }

    public List<SinglePlayerGame> getAllSinglePlayerGames(){
        return singlePlayerDao.findAll();
    }

//    public List<SinglePlayerGame> getGamesByUser(int id){
//        return singlePlayerDao.getGamesByUser(id);
//    }
}
