package com.comunio.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.ResultDao;
import com.comunio.model.Result;
import com.comunio.model.Team;

@Repository
public class ResultDaoImpl implements ResultDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveOrUpdate(Result result) {
        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery("Select * from result where matchday=:matchday and teamId=:teamId")
                .addEntity(Result.class).setParameter("teamId", result.getTeam().getTeamId())
                .setParameter("matchday", result.getMatchday());
        List<Result> resultList = query.list();
        if (resultList.size() == 0) {
            sessionFactory.getCurrentSession().save(result);
        } else {
            Result result2Update = resultList.get(0);
            result2Update.setPoints(result.getPoints());
            result2Update.setGoals(result.getGoals());
            sessionFactory.getCurrentSession().update(result2Update);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Result> getResultsByTeam(Team team) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM result WHERE teamId=:teamId")
                .addEntity(Result.class).setParameter("teamId", team.getTeamId());
        return query.list();
    }
}