package com.comunio.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.comunio.dao.TeamDao;
import com.comunio.model.Groupe;
import com.comunio.model.Team;
import com.comunio.service.GroupService;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceImplTest {
    private static final long COMUNIO_ID = 1l;
    private static final String TEAM3_NAME = "Team3";
    private static final String TEAM2_NAME = "Team2";
    private static final String TEAM1_NAME = "Team1";
    private static final String TEAMS_STRING = "Team1;Team2;Team3;";
    @Mock
    TeamDao teamDao;
    @Mock
    GroupService groupService;

    private TeamServiceImpl teamService = new TeamServiceImpl();

    @Before
    public void setUp() {
        teamService.teamDao = teamDao;
        teamService.groupService = groupService;
    }

    @Test
    public void saveTeamCallsCorrectMethod() {
        Groupe group = mock(Groupe.class);
        Team team = mock(Team.class);
        teamService.saveTeam(team, group);
        verify(team).setGroupe(group);
        verify(teamDao).saveTeam(team);
    }

    @Test
    public void createTeamsFromStringReturnsCorrectListOfTeams() {
        List<Team> teams = teamService.createTeamsFromString(TEAMS_STRING);
        assertEquals(3, teams.size());
        assertEquals(TEAM1_NAME, teams.get(0).getTeamName());
        assertEquals(TEAM2_NAME, teams.get(1).getTeamName());
        assertEquals(TEAM3_NAME, teams.get(2).getTeamName());
    }

    @Test
    public void findTeamNameByComunioIdCallsCorrectMethod() {
        teamService.findAllTeamNames();
        verify(groupService).getGroups();
    }
}
