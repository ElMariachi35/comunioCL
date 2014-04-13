package com.comunio.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.GameDao;
import com.comunio.model.Game;

@Repository
public class GameDaoImpl implements GameDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveMatch(Game game) {
        sessionFactory.getCurrentSession().save(game);
    }

    @Override
    public void updateGame(Game game) {
        sessionFactory.getCurrentSession().merge(game);
    }
}
