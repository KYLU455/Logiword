//package com.bachelor.logiword.server.dao;
//
//import com.bachelor.logiword.server.model.SinglePlayerGame;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository("test")
//public class SinglePlayerGameDataAccessService implements SinglePlayerDao {
//    private static List<SinglePlayerGame> DB = new ArrayList();
//
//    @Override
//    public int insertGame(SinglePlayerGame game) {
//        DB.add(game);
//        return 1;
//    }
//
//    @Override
//    public List<SinglePlayerGame> getAllSinglePlayerGames() {
//        return DB;
//    }
//
//    @Override
//    public List<SinglePlayerGame> getGamesByUser(int playerId) {
//        return DB.stream()
//                .filter(game -> game.getPlayerId() == playerId)
//                .collect(Collectors.toList());
//    }
//}
