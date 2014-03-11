package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.comunio.model.Fixture;
import com.comunio.model.Game;
import com.comunio.model.Matchday;
import com.comunio.model.Team;

public class RoundRobinServiceImpl {

    private static final String DUMMY_TEAM = "SWCW13Q56";

    public Fixture roundRobinCreateFixture(List<Team> teams) {
        Fixture fixture = new Fixture();
        fixture.setMatchdays(createMatchdays(teams, fixture));
        return fixture;
    }

    private Set<Matchday> createMatchdays(List<Team> teamsOriginal, Fixture fixture) {
        List<Team> teams = new ArrayList<>(teamsOriginal);
        Set<Matchday> matchdays = new LinkedHashSet<>();
        List<Team> team1 = new ArrayList<>();
        List<Team> team2 = new ArrayList<>();
        List<Team> team3 = new ArrayList<>();
        List<Team> team4 = new ArrayList<>();

        int numberOfRounds = determineNumberOfRounds(teams);

        if (teams.size() % 2 != 0) {
            Team dummyTeam = new Team();
            dummyTeam.setTeamName(DUMMY_TEAM);
            teams.add(dummyTeam);
        }

        team1 = teams.subList(0, teams.size() / 2);
        team2 = teams.subList(teams.size() / 2, teams.size());

        for (int i = 0; i < numberOfRounds; i++) {
            for (int comunioMatchdayNumber = 1; comunioMatchdayNumber < teams.size(); comunioMatchdayNumber++) {
                matchdays.add(createMatches(team1, team2, fixture, numberOfRounds));
                Collections.rotate(team1, 1);
                Collections.rotate(team2, -1);
                team3 = new ArrayList<>(team1);
                team4 = new ArrayList<>(team2);

                team4.set(team4.size() - 1, team1.get(0));
                team3.set(0, team1.get(1));
                team3.set(1, team2.get(team2.size() - 1));

                team1 = new ArrayList<>(team3);
                team2 = new ArrayList<>(team4);
            }
        }
        matchdays = new LinkedHashSet(setMatchdayNumbers(new ArrayList(matchdays)));
        return matchdays;
    }

    private Matchday createMatches(List<Team> team1, List<Team> team2, Fixture fixture, int numberOfRounds) {
        Matchday matchday = new Matchday();
        matchday.setFixture(fixture);
        List<Game> games = new ArrayList<>();

        for (int j = 0; j < team1.size(); j++) {
            Game game = new Game();
            game.setMatchday(matchday);
            game.setHomeTeam(team1.get(j));
            game.setAwayTeam(team2.get(j));
            if (gameHasDummyTeam(game)) {
                if (homeTeamIsDummy(game)) {
                    matchday.setByeTeam(game.getAwayTeam());
                }
                if (awayTeamIsDummy(game)) {
                    matchday.setByeTeam(game.getHomeTeam());
                }
            } else {
                games.add(game);
            }
        }

        matchday.setMatches(new LinkedHashSet<>(games));
        return matchday;
    }

    private List<Matchday> setMatchdayNumbers(List<Matchday> matchdays) {
        int i = 1;
        for (Matchday matchday : matchdays) {
            matchday.setComunioMatchdayNumber(i);
            i++;
        }
        return matchdays;
    }

    private boolean awayTeamIsDummy(Game game) {
        return game.getAwayTeam().getTeamName().equals(DUMMY_TEAM);
    }

    private boolean homeTeamIsDummy(Game game) {
        return game.getHomeTeam().getTeamName().equals(DUMMY_TEAM);
    }

    private boolean gameHasDummyTeam(Game game) {
        return awayTeamIsDummy(game) || homeTeamIsDummy(game);
    }

    private int determineNumberOfRounds(List<Team> teams) {
        switch (teams.size()) {
        case 4:
            return 3;
        case 5:
            return 2;
        case 6:
            return 2;
        }
        return 1;
    }
}
