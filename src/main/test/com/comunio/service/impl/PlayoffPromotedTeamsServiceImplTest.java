package com.comunio.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.comunio.model.KnockoutPairing;
import com.comunio.model.PlayoffGame;
import com.comunio.model.Team;

public class PlayoffPromotedTeamsServiceImplTest {

    private static final String TEAM_1 = "MyTeam1";
    private static final String TEAM_2 = "SuperTeam";
    private PlayoffPromotedTeamsServiceImpl playoffPromotedTeamsServiceImpl = new PlayoffPromotedTeamsServiceImpl();

    @Test
    public void oneTeamWinsBothGames() {
        KnockoutPairing pairing = createKnockoutPairing(2, 0, 1, 3);
        assertEquals(TEAM_1, playoffPromotedTeamsServiceImpl.determinePromotedTeam(pairing).getTeamName());
    }

    @Test
    public void oneTeamWinsOneGameWithBetterGoalDifference() throws Exception {
        KnockoutPairing pairing = createKnockoutPairing(0, 2, 0, 1);
        assertEquals(TEAM_2, playoffPromotedTeamsServiceImpl.determinePromotedTeam(pairing).getTeamName());
    }

    @Test
    public void teamOneAdvancesOnAwayGoals() throws Exception {
        KnockoutPairing pairing = createKnockoutPairing(1, 0, 2, 1);
        assertEquals(TEAM_1, playoffPromotedTeamsServiceImpl.determinePromotedTeam(pairing).getTeamName());
    }

    @Test
    public void teamTwoAdvancesOnAwayGoals() {
        KnockoutPairing pairing = createKnockoutPairing(4, 1, 3, 0);
        assertEquals(TEAM_2, playoffPromotedTeamsServiceImpl.determinePromotedTeam(pairing).getTeamName());
    }

    @Test
    public void teamTwoAdvancesAndAllGamesAreDraws() {
        KnockoutPairing pairing = createKnockoutPairing(2, 2, 1, 1);
        assertEquals(TEAM_2, playoffPromotedTeamsServiceImpl.determinePromotedTeam(pairing).getTeamName());
    }

    @Test
    public void teamTwoAdvancesOnHashCode() throws Exception {
        KnockoutPairing pairing = createKnockoutPairing(1, 1, 1, 1);
        assertEquals(TEAM_2, playoffPromotedTeamsServiceImpl.determinePromotedTeam(pairing).getTeamName());
    }

    private KnockoutPairing createKnockoutPairing(int homeGoalsFirstLeg, int awayGoalsFirstLeg, int homeGoalsSecondLeg,
            int awayGoalsSecondLeg) {
        Team team1 = new Team();
        team1.setTeamName(TEAM_1);
        Team team2 = new Team();
        team2.setTeamName(TEAM_2);
        PlayoffGame firstLeg = new PlayoffGame();
        firstLeg.setHomeTeam(team1);
        firstLeg.setAwayTeam(team2);
        firstLeg.setHomeGoals(homeGoalsFirstLeg);
        firstLeg.setAwayGoals(awayGoalsFirstLeg);
        PlayoffGame secondLeg = new PlayoffGame();
        secondLeg.setHomeTeam(team2);
        secondLeg.setAwayTeam(team1);
        secondLeg.setHomeGoals(homeGoalsSecondLeg);
        secondLeg.setAwayGoals(awayGoalsSecondLeg);
        KnockoutPairing pairing = new KnockoutPairing();
        pairing.setFirstLeg(firstLeg);
        pairing.setSecondLeg(secondLeg);
        return pairing;
    }
}
