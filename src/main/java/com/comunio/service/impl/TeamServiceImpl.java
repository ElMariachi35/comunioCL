package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.TeamDao;
import com.comunio.model.Groupe;
import com.comunio.model.SessionData;
import com.comunio.model.Team;
import com.comunio.service.GroupService;
import com.comunio.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    GroupService groupService;
    @Autowired
    TeamDao teamDao;
    @Autowired
    SessionData sessionData;

    @Transactional
    public void saveTeam(Team team, Groupe group) {
        team.setGroupe(group);
        teamDao.saveTeam(team);
    }

    public List<Team> createTeamsFromString(String teamsString) {
        String[] teamsStringArr = teamsString.split(";");
        List<Team> teams = new ArrayList<Team>();
        for (int i = 0; i < teamsStringArr.length; i++) {
            Team team = new Team();
            team.setTeamName(teamsStringArr[i]);
            teams.add(team);
        }
        return teams;
    }

    @Transactional
    public List<String> findAllTeamNames() {
        List<String> teamNames = new ArrayList<String>();
        for (Groupe group : groupService.getGroups()) {
            for (Team team : group.getSortedTeams()) {
                teamNames.add(team.getTeamName());
            }
        }
        return teamNames;
    }

    @Override
    public void updateTeam(Team team) {
        teamDao.updateTeam(team);
    }

    @Override
    public Team findTeamByTeamNameAndComunioId(String teamName, long comunioId) {
        for (Groupe groupe : groupService.getGroups()) {
            for (Team team : groupe.getTeams()) {
                if (team.getTeamName().equals(teamName)) {
                    return team;
                }
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Team findBy(long teamId) {
        return teamDao.findBy(teamId);
    }
}
