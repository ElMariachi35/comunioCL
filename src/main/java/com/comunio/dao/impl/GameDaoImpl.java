package com.comunio.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.GameDao;
import com.comunio.model.Game;
import com.comunio.model.Team;

@Repository
public class GameDaoImpl implements GameDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveMatch(Game game) {
        sessionFactory.getCurrentSession().save(game);
    }

    @Override
    public List<Game> getGamesByTeam(Team team) {
        Query query = sessionFactory
                .getCurrentSession()
                .createSQLQuery(
                        "SELECT * FROM Game WHERE homeTeam=" + team.getTeamId() + " OR awayTeam=" + team.getTeamId()
                                + ";").addEntity(Game.class);
        return query.list();
    }

    @Override
    public void updateGame(Game game) {
        sessionFactory.getCurrentSession().update(game);
    }
}
