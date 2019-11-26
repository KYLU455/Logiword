package com.bachelor.logiword.server.dao.account;

import com.bachelor.logiword.server.model.account.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("accountEm")
public class AccountDataAccess implements AccountDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void register(Account acc) {
        em.persist(acc);
    }

    @Override
    public int login(Account acc) {
        return 0;
    }

    @Override
    public void update(Account acc) {

    }
}
