package com.comunio.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.comunio.dao.FixtureDao;
import com.comunio.model.Fixture;
import com.comunio.model.Groupe;
import com.comunio.model.Team;
import com.comunio.service.MatchdayService;

@RunWith(MockitoJUnitRunner.class)
public class FixtureServiceImplTest {
    
    private static final long GROUP_ID = 1l;
    @Mock
    private FixtureDao fixtureDao;
    @Mock
    private MatchdayService matchdayService;
    @Mock
    private RoundRobinServiceImpl roundRobinService;

    private FixtureServiceImpl fixtureService = new FixtureServiceImpl();
    private Groupe group;
    
    @Before
    public void setUp(){
        fixtureService.fixtureDao = fixtureDao;
        fixtureService.matchdayService = matchdayService;
        fixtureService.roundRobinService = roundRobinService;
        group = new Groupe();
        group.setGroupId(GROUP_ID);
    }
    
    @Test
    public void createFixtureCreatesFixture() throws Exception {
        
        Fixture fixture = new Fixture();
        Set<Team> teams = new HashSet<Team>();
        teams.add(new Team());
        group.setTeams(teams);
        when(roundRobinService.roundRobinCreateFixture(new ArrayList<Team>(group.getTeams()))).thenReturn(fixture);
        fixtureService.createFixture(group);
        verify(fixtureDao).addFixture(fixture);
        verify(matchdayService).saveMatchdays(fixture.getMatchdays());
    }
    
    @Test
    public void getFixtureReturnsCorrectFixtureForGroup() throws Exception {
        fixtureService.getFixture(group);
        verify(fixtureDao).getFixtureByGroupId(group.getGroupId());
    }
}
