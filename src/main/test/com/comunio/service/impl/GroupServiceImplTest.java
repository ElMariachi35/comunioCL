package com.comunio.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import com.comunio.dao.GroupDao;
import com.comunio.model.Comunio;
import com.comunio.model.Groupe;
import com.comunio.model.Team;
import com.comunio.service.ComunioService;
import com.comunio.service.FixtureService;
import com.comunio.service.TeamService;

@RunWith(MockitoJUnitRunner.class)
public class GroupServiceImplTest {

    private static final String GROUP_NAME = "group";
    private static final long GROUP_ID = 1l;
    private static final long COMUNIO_ID = 1l;
    private static final String TEAM_STRING = "teamString";
    @Mock
    GroupDao groupDao;
    @Mock
    TeamService teamService;
    @Mock
    ComunioService comunioService;
    @Mock
    FixtureService fixtureService;

    List<Team> teams;
    GroupServiceImpl groupService = new GroupServiceImpl();

    @Before
    public void setUp() {
        groupService.groupDao = groupDao;
        groupService.teamService = teamService;
        groupService.fixtureService = fixtureService;
        groupService.comunioService = comunioService;
    }
    
    @Test
    public void findGroupsByComunioIdReturnsCorrectGroup() throws Exception {
        Groupe group = new Groupe();
        group.setGroupId(GROUP_ID);
        when(groupDao.findGroupsByComunioId(COMUNIO_ID)).thenReturn(Arrays.asList(group));
        assertEquals(GROUP_ID, groupService.findGroupsByComunioId(COMUNIO_ID).get(0).getGroupId());
    }
    
    @Test
    public void getGroupReturnsCorrectGroup() throws Exception {
        Groupe group = new Groupe();
        group.setGroupId(GROUP_ID);
        when(groupDao.getGroup(COMUNIO_ID, GROUP_NAME)).thenReturn(group);
        assertEquals(GROUP_ID, groupService.getGroup(COMUNIO_ID, GROUP_NAME).getGroupId());
    }

    @Test
    public void initializeGroupsCreatesTwoGroupsWithFiveTeams() throws Exception {
        teams = mockTeams(8);
        when(teamService.createTeamsFromString(TEAM_STRING)).thenReturn(teams);
        when(comunioService.getComunio(anyLong())).thenReturn(any(Comunio.class));

        groupService.initializeGroups(COMUNIO_ID, teams.size(), TEAM_STRING);

        verifyInitializeGroups(2, 8, 2);
    }

    @Test
    public void initializeGroupsCreatesTwoGroupsWithFourAndFiveTeams() throws Exception {
        teams = mockTeams(9);
        when(teamService.createTeamsFromString(TEAM_STRING)).thenReturn(teams);
        when(comunioService.getComunio(anyLong())).thenReturn(any(Comunio.class));

        groupService.initializeGroups(COMUNIO_ID, teams.size(), TEAM_STRING);

        verifyInitializeGroups(2, 9, 2);
    }

    @Test
    public void initializeGroupsCreatesOneGroupWithFiveTeams() throws Exception {
        teams = mockTeams(5);
        when(teamService.createTeamsFromString(TEAM_STRING)).thenReturn(teams);
        when(comunioService.getComunio(anyLong())).thenReturn(any(Comunio.class));

        groupService.initializeGroups(COMUNIO_ID, teams.size(), TEAM_STRING);
        verifyInitializeGroups(1, 5, 1);
    }

    @Test
    public void initializeGroupsCreatesThreeGroupsWithFourOrFiveTeams() throws Exception {
        teams = mockTeams(13);
        when(teamService.createTeamsFromString(TEAM_STRING)).thenReturn(teams);
        when(comunioService.getComunio(anyLong())).thenReturn(any(Comunio.class));

        groupService.initializeGroups(COMUNIO_ID, teams.size(), TEAM_STRING);

        verifyInitializeGroups(3, 13, 3);
    }

    @Test
    public void initializeGroupsCreatesFourGroupsWithFourTeams() throws Exception {
        teams = mockTeams(16);
        when(teamService.createTeamsFromString(TEAM_STRING)).thenReturn(teams);
        when(comunioService.getComunio(anyLong())).thenReturn(any(Comunio.class));

        groupService.initializeGroups(COMUNIO_ID, teams.size(), TEAM_STRING);

        verifyInitializeGroups(4, 16, 4);
    }
    
    private void verifyInitializeGroups(int numberGroupDao, int numberTeamService, int numberFixtureService) {
        verify(groupDao, times(numberGroupDao)).add(any(Groupe.class));
        verify(teamService, times(numberTeamService)).saveTeam(any(Team.class), any(Groupe.class));
        verify(fixtureService, times(numberFixtureService)).createFixture(any(Groupe.class));
    }

    private List<Team> mockTeams(int numberOfTeams) {
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < numberOfTeams; i++) {
            teams.add(new Team());
        }
        return teams;
    }

}
