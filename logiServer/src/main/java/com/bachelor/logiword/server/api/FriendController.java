package com.bachelor.logiword.server.api;

import com.bachelor.logiword.server.model.friend.FriendPair;
import com.bachelor.logiword.server.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("friends")
@RestController
public class FriendController {

    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping
    public void makeFriendRequest(@RequestBody FriendPair pair){
        friendService.makeFriendRequest(pair);
    }

    @GetMapping(path = "/requests/{playerId}")
    public List getFriendRequests(@PathVariable("playerId") int playerId){
        return friendService.getFriendRequests(playerId);
    }
}
