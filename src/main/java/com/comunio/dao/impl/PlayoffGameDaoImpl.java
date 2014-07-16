package com.comunio.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.PlayoffGameDao;
import com.comunio.model.PlayoffGame;

@Repository
public class PlayoffGameDaoImpl implements PlayoffGameDao, Serializable {
    private static final long serialVersionUID = 1295903019833001862L;

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(PlayoffGame game) {
        sessionFactory.getCurrentSession().save(game);
    }

    @Override
    public void update(PlayoffGame playoffGame) {
        sessionFactory.getCurrentSession().merge(playoffGame);
    }

}
