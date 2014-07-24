package com.comunio.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.comunio.model.Team;
import com.comunio.service.KnockoutPairingService;

@RunWith(MockitoJUnitRunner.class)
public class PlayoffFixtureServiceImplTest {

    @Mock
    private KnockoutPairingService knockoutPairingService;

    private static final long TEAM_1_ID = 1l;
    private static final long TEAM_2_ID = 2l;
    private static final long TEAM_3_ID = 3l;
    private static final long TEAM_4_ID = 4l;
    private PlayoffFixtureServiceImpl playoffFixtureService = new PlayoffFixtureServiceImpl();

    @Before
    public void setUp() {
        playoffFixtureService.knockoutPairingService = knockoutPairingService;
    }

    @Test
    public void itContainsTwoKnockoutPairings() {
        // Map<Integer, Team> teams = createMapWithFourTeams();
        // PlayoffFixture fixture = playoffFixtureService.createFixture(teams);
        // assertEquals(2, fixture.getPairings().size());
    }

    // @Test
    // public void theNumberOneRankedTeamPlaysTheNumberFourRankedTeam() {
    // Map<Integer, Team> teams = createMapWithFourTeams();
    // playoffFixtureService.createFixture(teams);
    // verify(knockoutPairingService).createPairing(teams.get(1), teams.get(4));
    // verify(knockoutPairingService).createPairing(teams.get(2), teams.get(3));
    // }

    private Map<Integer, Team> createMapWithFourTeams() {
        Map<Integer, Team> teams = new HashMap<>();
        teams.put(1, createTeam(TEAM_1_ID));
        teams.put(2, createTeam(TEAM_2_ID));
        teams.put(3, createTeam(TEAM_3_ID));
        teams.put(4, createTeam(TEAM_4_ID));
        return teams;
    }

    private Team createTeam(long teamId) {
        Team team = new Team();
        team.setTeamId(teamId);
        return team;
    }
}
