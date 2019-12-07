package com.bachelor.logiword.server.dao.account;

import com.bachelor.logiword.server.model.account.Account;

public interface AccountDao {
    void register(Account acc);

    int login(String username, String password);

    void update(Account acc);

    Account accountDetails(int playerId);
}
