package com.comunio.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.ResultDao;
import com.comunio.model.Result;

@Repository
public class ResultDaoImpl implements ResultDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveOrUpdate(Result result) {
        //
        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery("Select * from result where matchday=:matchday and teamId=:teamId")
                .addEntity(Result.class).setParameter("teamId", result.getTeam().getTeamId())
                .setParameter("matchday", result.getMatchday());
        if (query.list().size() == 0) {
            sessionFactory.getCurrentSession().saveOrUpdate(result);
        }
    }
}
