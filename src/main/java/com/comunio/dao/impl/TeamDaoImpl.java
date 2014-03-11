package com.comunio.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.TeamDao;
import com.comunio.model.Team;

@Repository
public class TeamDaoImpl implements TeamDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveTeam(Team team) {
        sessionFactory.getCurrentSession().save(team);
    }

    @Override
    public List<String> findTeamNamesByComunioId(long comunioId) {
        List<String> teamNames = new ArrayList<>();
        Query query = sessionFactory
                .getCurrentSession()
                .createSQLQuery(
                        "SELECT t.teamName FROM team t WHERE t.groupId = (SELECT g.groupId FROM groupe g WHERE comunioId=:comunioId) ORDER BY t.teamName ASC")
                .setParameter("comunioId", comunioId);
        teamNames = query.list();
        return teamNames;
    }

    @Override
    public Team findTeamByTeamNameAndComunioId(String teamName, long comunioId) {
        Query query = sessionFactory
                .getCurrentSession()
                .createSQLQuery(
                        "SELECT * FROM team t WHERE t.groupId = (SELECT g.groupId FROM groupe g WHERE comunioId=:comunioId) AND t.teamName =:teamName")
                .addEntity(Team.class).setParameter("comunioId", comunioId).setParameter("teamName", teamName);
        return (Team) query.list().get(0);
    }

    @Override
    public void updateTeam(Team team) {
        sessionFactory.getCurrentSession().update(team);
    }
}
