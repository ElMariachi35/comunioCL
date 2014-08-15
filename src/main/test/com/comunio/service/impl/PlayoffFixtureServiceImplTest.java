package com.comunio.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
}
