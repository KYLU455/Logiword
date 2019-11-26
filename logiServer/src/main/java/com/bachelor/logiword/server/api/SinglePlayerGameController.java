package com.bachelor.logiword.server.api;

import com.bachelor.logiword.server.model.single_player.SinglePlayerGame;
import com.bachelor.logiword.server.model.single_player.SinglePlayerGameData;
import com.bachelor.logiword.server.model.single_player.SinglePlayerGameDataWithPlayerName;
import com.bachelor.logiword.server.service.SinglePlayerGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("singleplayer")
@RestController
public class SinglePlayerGameController {

    private final SinglePlayerGameService singlePlayerGameService;

    @Autowired
    public SinglePlayerGameController(SinglePlayerGameService singlePlayerGameService) {
        this.singlePlayerGameService = singlePlayerGameService;
    }

    @PostMapping
    public void addSinglePlayerGame(@RequestBody SinglePlayerGame game){
        singlePlayerGameService.addGame(game);
    }

    @GetMapping
    public List<SinglePlayerGameDataWithPlayerName> getAllSinglePlayerGames(){
        return singlePlayerGameService.getAllSinglePlayerGames();
    }

    @GetMapping(path = "{playerId}")
    public List<SinglePlayerGameData> getGamesByUser(@PathVariable("playerId") int playerId){
        return singlePlayerGameService.getGamesByUser(playerId);
    }
}
