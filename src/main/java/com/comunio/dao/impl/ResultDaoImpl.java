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
	Query query = sessionFactory
		.getCurrentSession()
		.createSQLQuery(
			"Select * from Result where matchday=:matchday and teamId=:teamId")
		.addEntity(Result.class)
		.setParameter("teamId", result.getTeam().getTeamId())
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
	Query query = sessionFactory.getCurrentSession()
		.createSQLQuery("SELECT * FROM Result WHERE teamId=:teamId")
		.addEntity(Result.class)
		.setParameter("teamId", team.getTeamId());
	return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Result> getResults(long comunioId) {
	Query query = sessionFactory
		.getCurrentSession()
		.createSQLQuery(
			"SELECT DISTINCT r.resultId, r.teamId, r.matchday, r.points, r.goals FROM Groupe g JOIN Team t ON g.groupId=t.groupId JOIN Result r ON t.teamId=r.teamId WHERE g.comunioId=:comunioId")
		.addEntity(Result.class).setParameter("comunioId", comunioId);
	return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Result> findResultsBy(long comunioId, int matchdayNumber) {
	Query query = sessionFactory
		.getCurrentSession()
		.createSQLQuery(
			"SELECT r.resultId, r.teamId, r.matchday, r.points, r.goals FROM Result r JOIN Team t ON r.teamId=t.teamId JOIN Groupe g ON t.groupId=g.groupId WHERE comunioId="
				+ comunioId
				+ " AND matchday="
				+ matchdayNumber
				+ ";").addEntity(Result.class);
	return query.list();
    }

    @Override
    public int findLatestMatchday(long comunioId) {
	SQLQuery query = sessionFactory
		.getCurrentSession()
		.createSQLQuery(
			"SELECT MAX(matchday) FROM Result r JOIN Team t ON r.teamId=t.teamId JOIN Groupe g ON t.groupId=g.groupId WHERE comunioId="
				+ comunioId + ";");
	int latestMatchday = (Integer) query.uniqueResult();
	return latestMatchday;
    }

    @Override
    public long findNumberOfResults(long comunioId) {
	Query query = sessionFactory
		.getCurrentSession()
		.createSQLQuery(
			"SELECT COUNT(*) FROM Result r INNER JOIN Team t ON r.teamId=t.teamId INNER JOIN Groupe g ON t.groupId=g.groupId WHERE g.comunioId= :comunioId")
		.setParameter("comunioId", comunioId);
	return Long.valueOf(query.uniqueResult().toString());
    }
}
