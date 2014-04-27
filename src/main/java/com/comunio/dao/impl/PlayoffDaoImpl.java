package com.comunio.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.PlayoffDao;
import com.comunio.model.Playoff;

@Repository
public class PlayoffDaoImpl implements PlayoffDao, Serializable {
    private static final long serialVersionUID = 755666301771987768L;
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(Playoff playoff) {
	sessionFactory.getCurrentSession().save(playoff);
    }

    @Override
    public void update(Playoff playoff) {
	sessionFactory.getCurrentSession().update(playoff);
    }

}
