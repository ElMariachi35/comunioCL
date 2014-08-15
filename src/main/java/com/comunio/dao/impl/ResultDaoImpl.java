package com.comunio.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
    @SuppressWarnings("unchecked")
    public Result saveOrUpdate(Result result) {
        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery("Select * from result where matchday=:matchday and teamId=:teamId")
                .addEntity(Result.class).setParameter("teamId", result.getTeam().getTeamId())
                .setParameter("matchday", result.getMatchday());
        List<Result> resultList = query.list();
        if (resultList.size() == 0) {
            sessionFactory.getCurrentSession().save(result);
            return result;
        } else {
            Result result2Update = resultList.get(0);
            result2Update.setPoints(result.getPoints());
            result2Update.setGoals(result.getGoals());
            sessionFactory.getCurrentSession().update(result2Update);
            return result;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Result> getResultsByTeam(Team team) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM result WHERE teamId=:teamId")
                .addEntity(Result.class).setParameter("teamId", team.getTeamId());
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Result> getResults(long comunioId) {
        Query query = sessionFactory
                .getCurrentSession()
                .createSQLQuery(
                        "SELECT DISTINCT r.resultId, r.teamId, r.matchday, r.points, r.goals FROM groupe g JOIN team t ON g.groupId=t.groupId JOIN result r ON t.teamId=r.teamId WHERE g.comunioId=:comunioId")
                .addEntity(Result.class).setParameter("comunioId", comunioId);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Result> findResultsBy(long comunioId, int matchdayNumber) {
        Query query = sessionFactory
                .getCurrentSession()
                .createSQLQuery(
                        "SELECT result.resultId, result.teamId, result.matchday, result.points, result.goals FROM result JOIN team ON result.teamId=team.teamId JOIN groupe ON team.groupId=groupe.groupId WHERE comunioId="
                                + comunioId + " AND matchday=" + matchdayNumber + ";").addEntity(Result.class);
        return query.list();
    }

    @Override
    public int findLatestMatchday(long comunioId) {
        SQLQuery query = sessionFactory
                .getCurrentSession()
                .createSQLQuery(
                        "SELECT MAX(matchday) FROM result JOIN team ON result.teamId=team.teamId JOIN groupe ON team.groupId=groupe.groupId WHERE comunioId="
                                + comunioId + ";");
        int latestMatchday = (Integer) query.uniqueResult();
        return latestMatchday;
    }
}
