package com.bachelor.logiword.server.api;


import com.bachelor.logiword.server.model.daily_challenge_attempt.DailyChallengeAttemptFromUser;
import com.bachelor.logiword.server.service.DailyChallengeAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("challengeattempt")
@RestController
public class DailyChallengeAttemptController {

    private final DailyChallengeAttemptService attemptService;

    @Autowired
    public DailyChallengeAttemptController(DailyChallengeAttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PostMapping
    public void challengeAttempt(@RequestBody DailyChallengeAttemptFromUser attempt){
        attemptService.insertAttempt(attempt);
    }
}
