package com.bachelor.logiword.server.dao.account;

import com.bachelor.logiword.server.model.account.Account;

public interface AccountDao {
    void register(Account acc);

    int login(Account acc);

    void update(Account acc);
}
