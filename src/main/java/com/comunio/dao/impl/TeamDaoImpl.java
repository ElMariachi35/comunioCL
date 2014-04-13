package com.comunio.dao.impl;

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
    public void updateTeam(Team team) {
        String queryString = "UPDATE team t SET t.gamesPlayed=" + team.getGamesPlayed() + ", t.gamesWon="
                + team.getGamesWon() + ", t.gamesDrawn=" + team.getGamesDrawn() + ", t.gamesLost="
                + team.getGamesLost() + ", t.goalsFor=" + team.getGoalsFor() + ", t.goalsAgainst="
                + team.getGoalsAgainst() + ", t.goalDifference=" + team.getGoalDifference() + ", t.points="
                + team.getPoints() + " WHERE t.teamId=" + team.getTeamId();
        System.out.println(queryString);
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryString);
        query.executeUpdate();
    }
}
