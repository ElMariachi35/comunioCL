package com.comunio.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.comunio.model.Fixture;
import com.comunio.model.Game;
import com.comunio.model.Matchday;
import com.comunio.model.Team;

@RunWith(MockitoJUnitRunner.class)
public class RoundRobinServiceImplTest {
    private RoundRobinServiceImpl roundRobinService = new RoundRobinServiceImpl();

    @Test
    public void createFixtureWithFourTeamsReturnsACorrectFixture() {
        List<Team> teams = mockTeams(4);
        Fixture fixture = roundRobinService.roundRobinCreateFixture(teams);

        assertEquals(9, getNumberOfMatchesForTeam(teams.get(0), fixture));
        assertEquals(9, getNumberOfMatchesForTeam(teams.get(1), fixture));
        assertEquals(9, getNumberOfMatchesForTeam(teams.get(2), fixture));
        assertEquals(9, getNumberOfMatchesForTeam(teams.get(3), fixture));

        assertEquals(3, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(1), fixture));
        assertEquals(3, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(2), fixture));
        assertEquals(3, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(3), fixture));
        assertEquals(3, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(2), fixture));
        assertEquals(3, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(3), fixture));
        assertEquals(3, getNumberOfMatchesAgainstEachOther(teams.get(2), teams.get(3), fixture));

        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(0), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(1), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(2), teams.get(2), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(3), teams.get(3), fixture));
    }

    @Test
    public void createFixtureWithFiveTeamsReturnsACorrectFixture() {
        List<Team> teams = mockTeams(5);

        Fixture fixture = roundRobinService.roundRobinCreateFixture(teams);

        assertEquals(8, getNumberOfMatchesForTeam(teams.get(0), fixture));
        assertEquals(8, getNumberOfMatchesForTeam(teams.get(1), fixture));
        assertEquals(8, getNumberOfMatchesForTeam(teams.get(2), fixture));
        assertEquals(8, getNumberOfMatchesForTeam(teams.get(3), fixture));
        assertEquals(8, getNumberOfMatchesForTeam(teams.get(4), fixture));

        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(1), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(2), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(3), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(4), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(2), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(3), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(4), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(2), teams.get(3), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(2), teams.get(4), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(3), teams.get(4), fixture));

        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(0), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(1), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(2), teams.get(2), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(3), teams.get(3), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(4), teams.get(4), fixture));
    }

    @Test
    public void createFixtureWithSixTeamsReturnsACorrectFixture() {
        List<Team> teams = mockTeams(6);

        Fixture fixture = roundRobinService.roundRobinCreateFixture(teams);

        assertEquals(10, getNumberOfMatchesForTeam(teams.get(0), fixture));
        assertEquals(10, getNumberOfMatchesForTeam(teams.get(1), fixture));
        assertEquals(10, getNumberOfMatchesForTeam(teams.get(2), fixture));
        assertEquals(10, getNumberOfMatchesForTeam(teams.get(3), fixture));
        assertEquals(10, getNumberOfMatchesForTeam(teams.get(4), fixture));
        assertEquals(10, getNumberOfMatchesForTeam(teams.get(5), fixture));

        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(1), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(2), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(3), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(4), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(5), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(2), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(3), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(4), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(5), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(2), teams.get(3), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(2), teams.get(4), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(2), teams.get(5), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(3), teams.get(4), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(3), teams.get(5), fixture));
        assertEquals(2, getNumberOfMatchesAgainstEachOther(teams.get(4), teams.get(5), fixture));

        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(0), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(1), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(2), teams.get(2), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(3), teams.get(3), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(4), teams.get(4), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(5), teams.get(5), fixture));
    }

    @Test
    public void createFixtureWithSevenTeamsReturnsACorrectFixture() {
        List<Team> teams = mockTeams(7);

        Fixture fixture = roundRobinService.roundRobinCreateFixture(teams);

        assertEquals(6, getNumberOfMatchesForTeam(teams.get(0), fixture));
        assertEquals(6, getNumberOfMatchesForTeam(teams.get(1), fixture));
        assertEquals(6, getNumberOfMatchesForTeam(teams.get(2), fixture));
        assertEquals(6, getNumberOfMatchesForTeam(teams.get(3), fixture));
        assertEquals(6, getNumberOfMatchesForTeam(teams.get(4), fixture));
        assertEquals(6, getNumberOfMatchesForTeam(teams.get(5), fixture));
        assertEquals(6, getNumberOfMatchesForTeam(teams.get(6), fixture));

        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(1), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(2), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(3), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(4), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(5), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(6), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(2), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(3), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(4), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(5), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(6), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(2), teams.get(3), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(2), teams.get(4), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(2), teams.get(5), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(2), teams.get(6), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(3), teams.get(4), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(3), teams.get(5), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(3), teams.get(6), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(4), teams.get(5), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(4), teams.get(6), fixture));
        assertEquals(1, getNumberOfMatchesAgainstEachOther(teams.get(5), teams.get(6), fixture));

        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(0), teams.get(0), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(1), teams.get(1), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(2), teams.get(2), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(3), teams.get(3), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(4), teams.get(4), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(5), teams.get(5), fixture));
        assertEquals(0, getNumberOfMatchesAgainstEachOther(teams.get(6), teams.get(6), fixture));
    }

    private int getNumberOfMatchesAgainstEachOther(Team teamA, Team teamB, Fixture fixture) {
        int numberOfMatchesAgainstEachOther = 0;
        for (Matchday matchday : fixture.getMatchdays()) {
            for (Game game : matchday.getMatches()) {
                if (isGameBetweenTeamAAndTeamB(teamA, teamB, game)) {
                    numberOfMatchesAgainstEachOther++;
                }
            }
        }
        return numberOfMatchesAgainstEachOther;
    }

    private boolean isGameBetweenTeamAAndTeamB(Team teamA, Team teamB, Game game) {
        return game.getAwayTeam().equals(teamA) && game.getHomeTeam().equals(teamB)
                || (game.getAwayTeam().equals(teamB) && game.getHomeTeam().equals(teamA));
    }

    private int getNumberOfMatchesForTeam(Team team, Fixture fixture) {
        int numberOfMatches = 0;
        for (Matchday matchday : fixture.getMatchdays()) {
            for (Game game : matchday.getMatches()) {
                if (game.getAwayTeam().equals(team) || game.getHomeTeam().equals(team)) {
                    numberOfMatches++;
                }
            }
        }
        return numberOfMatches;
    }

    private List<Team> mockTeams(int numberOfTeams) {
        List<Team> teams = new ArrayList<Team>();
        for (long i = 0; i < numberOfTeams; i++) {
            Team team = new Team();
            team.setTeamId(i);
            team.setTeamName("Team" + i);
            teams.add(team);
        }
        return teams;
    }
}
