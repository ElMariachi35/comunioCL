package com.comunio.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.comunio.model.KnockoutPairing;
import com.comunio.model.Team;
import com.comunio.service.KnockoutPairingService;

public class KnockoutPairingServiceImplTest {

    private static final long TEAM_1_ID = 1l;
    private static final long TEAM_2_ID = 2l;
    private KnockoutPairingService knockoutPairingService = new KnockoutPairingServiceImpl();

    @Test
    public void createPairingsCreatesACorrectPairing() {
        Team team1 = new Team();
        team1.setTeamId(TEAM_1_ID);
        Team team2 = new Team();
        team2.setTeamId(TEAM_2_ID);

        KnockoutPairing pairing = knockoutPairingService.createPairing(team1, team2);

        assertEquals(team1.getTeamId(), pairing.getFirstLeg().getHomeTeam().getTeamId());
        assertEquals(team2.getTeamId(), pairing.getFirstLeg().getAwayTeam().getTeamId());
        assertEquals(team1.getTeamId(), pairing.getSecondLeg().getAwayTeam().getTeamId());
        assertEquals(team2.getTeamId(), pairing.getSecondLeg().getHomeTeam().getTeamId());
    }
}
