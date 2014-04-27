package com.comunio.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.PlayoffFixtureDao;
import com.comunio.model.PlayoffFixture;

@Repository
public class PlayoffFixtureDaoImpl implements PlayoffFixtureDao, Serializable {
    private static final long serialVersionUID = -1775052575826919433L;

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(PlayoffFixture fixture) {
	sessionFactory.getCurrentSession().save(fixture);
    }

}
