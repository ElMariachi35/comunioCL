package com.comunio.service;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.comunio.dao.GroupDao;
import com.comunio.model.Comunio;
import com.comunio.model.Groupe;
import com.comunio.model.Team;
import com.comunio.service.impl.GroupServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class GroupServiceTest {
    
    private static final String TEAMS_STRING = "asdf;sdfg;dfgh;ghjk;jklö;";
    private static final int NUMBER_OF_GROUPS = 2;
    private static final int NUMBER_OF_TEAMS = 6;
    private static final long COMUNIO_ID = 1l;
    @Mock
    TeamService teamService;
    @Mock
    Groupe group;
    @Mock
    Team team;
    @Mock
    GroupDao groupDao;
    @Mock
    ComunioService comunioService;
    
    GroupServiceImpl groupService = new GroupServiceImpl();
    
    @Before
    public void setUp(){
	groupService.setTeamService(teamService);
	groupService.setGroupDao(groupDao);
	groupService.setComunioService(comunioService);
    }
    
    @Test
    public void initializeGroupsCreatesCorrectGroups() {
	Comunio comunio = new Comunio();
	when(comunioService.getComunio(COMUNIO_ID)).thenReturn(comunio);
	when(teamService.createTeamsFromString(TEAMS_STRING)).thenReturn(Arrays.asList(new Team()));
	groupService.initializeGroups(COMUNIO_ID, NUMBER_OF_TEAMS, NUMBER_OF_GROUPS, TEAMS_STRING);
	verify(groupDao, times(NUMBER_OF_TEAMS)).add(any(Groupe.class));
	
    }
}
