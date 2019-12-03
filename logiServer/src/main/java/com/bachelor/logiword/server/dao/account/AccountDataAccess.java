package com.bachelor.logiword.server.dao.account;

import com.bachelor.logiword.server.model.account.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository("accountEm")
public class AccountDataAccess implements AccountDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void register(Account acc) {
        em.persist(acc);
    }

    @Override
    public int login(String username, String password) {
        int thing = ((BigDecimal) em.createNativeQuery("select PLAYER_ID from D_PLAYER " +
                "where PLAYER_NAME = ? and PASSWORD = ? and VALID_TO is null ")
                .setParameter(1, username)
                .setParameter(2, password)
                .getResultList().get(0)).intValue();
        return thing;
    }

    @Override
    public void update(Account acc) {

    }
}
