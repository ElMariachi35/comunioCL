package com.comunio.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.comunio.model.Comunio;
import com.comunio.model.Playoff;
import com.comunio.model.PlayoffFixture;
import com.comunio.model.SessionData;
import com.comunio.model.Team;
import com.comunio.service.PlayoffFixtureService;

@RunWith(MockitoJUnitRunner.class)
public class PlayoffServiceImplTest {
    @Mock
    private Playoff playoff;
    @Mock
    private PlayoffFixtureService playoffFixtureService;
    @Mock
    private SessionData sessionData;

    private PlayoffServiceImpl playoffService = new PlayoffServiceImpl();

    @Before
    public void setUp() {
        playoffService.sessionData = sessionData;
        playoffService.playoffFixtureService = playoffFixtureService;
    }

    @Test
    public void quaterFinalIsSetWithEightTeams() {
        Map<Integer, Team> teams = createTeams(8);
        when(playoffFixtureService.createFixture(teams)).thenReturn(new PlayoffFixture());
        Comunio comunio = new Comunio();
        when(sessionData.getComunio()).thenReturn(comunio);
        playoffService.initializePlayoffs(teams);
        assertNotNull(comunio.getPlayoff().getQuaterfinal());
    }

    @Test
    public void semiFinalIsSetWithFourTeams() {
        Map<Integer, Team> teams = createTeams(4);
        when(playoffFixtureService.createFixture(teams)).thenReturn(new PlayoffFixture());
        Comunio comunio = new Comunio();
        when(sessionData.getComunio()).thenReturn(comunio);
        playoffService.initializePlayoffs(teams);
        assertNotNull(comunio.getPlayoff().getSemifinal());
    }

    private Map<Integer, Team> createTeams(int numberOfTeams) {
        Map<Integer, Team> teams = new HashMap<Integer, Team>();
        for (int i = 0; i < numberOfTeams; i++) {
            teams.put(i + 1, new Team());
        }
        return teams;
    }
}
