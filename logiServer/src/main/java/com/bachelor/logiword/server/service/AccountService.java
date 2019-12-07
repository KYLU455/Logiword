package com.bachelor.logiword.server.service;

import com.bachelor.logiword.server.dao.account.AccountDao;
import com.bachelor.logiword.server.model.account.Account;
import com.bachelor.logiword.server.model.account.AccountDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountDao accountDao;

    @Autowired
    public AccountService(@Qualifier("accountEm") AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void addUser(Account account){
        accountDao.register(account);
    }

    public int login(String username, String password){
        return accountDao.login(username, password);
    }

    public void updateUser(Account account) {
        accountDao.update(account);
    }

    public AccountDetail accountDetails(int playerId){
        return accountDao.accountDetails(playerId);
    }
}
