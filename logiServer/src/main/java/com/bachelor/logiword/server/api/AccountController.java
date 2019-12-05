package com.bachelor.logiword.server.api;

import com.bachelor.logiword.server.model.account.Account;
import com.bachelor.logiword.server.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("account")
@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public void addUser(@RequestBody Account account){
        accountService.addUser(account);
    }

    @GetMapping(path = "{playerName}/{password}")
    public int login(@PathVariable("playerName") String playerName, @PathVariable("password") String password){
        return accountService.login(playerName, password);
    }

    @PutMapping
    public void updateUser(@RequestBody Account account){
        accountService.updateUser(account);
    }
}
