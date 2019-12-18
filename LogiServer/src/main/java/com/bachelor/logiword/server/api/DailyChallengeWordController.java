package com.bachelor.logiword.server.api;

import com.bachelor.logiword.server.service.DailyChallengeWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("dailyword")
@RestController
public class DailyChallengeWordController {

    private final DailyChallengeWordService wordService;

    @Autowired
    public DailyChallengeWordController(DailyChallengeWordService wordService){
        this.wordService = wordService;
    }

    @GetMapping
    public String getWord(){
        return wordService.getDailyChallenge();
    }
}
