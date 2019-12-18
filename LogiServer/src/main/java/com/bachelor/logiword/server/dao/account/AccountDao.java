package com.bachelor.logiword.server.dao.account;

import com.bachelor.logiword.server.model.account.Account;
import com.bachelor.logiword.server.model.account.AccountDetail;
import com.bachelor.logiword.server.model.account.AccountUpdate;

public interface AccountDao {
    void register(Account acc);

    int login(String username, String password);

    void update(AccountUpdate acc);

    AccountDetail accountDetails(int playerId);
}
