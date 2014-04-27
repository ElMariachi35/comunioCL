package com.comunio.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.KnockoutPairingDao;
import com.comunio.model.KnockoutPairing;

@Repository
public class KnockoutPairingDaoImpl implements KnockoutPairingDao, Serializable {
    private static final long serialVersionUID = 5305232944763922322L;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(KnockoutPairing pairing) {
	sessionFactory.getCurrentSession().save(pairing);
    }

}
