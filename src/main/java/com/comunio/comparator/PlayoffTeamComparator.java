package com.comunio.comparator;

import java.util.Comparator;

import com.comunio.model.Team;
import com.google.common.collect.ComparisonChain;

public class PlayoffTeamComparator implements Comparator<Team> {

    @Override
    public int compare(Team team2, Team team1) {
        return ComparisonChain.start().compare(getPointsPerGame(team1), getPointsPerGame(team2))
                .compare(team1.getGoalDifference(), team2.getGoalDifference())
                .compare(getGoalsForPerGame(team1), getGoalsForPerGame(team2))
                .compare(getGoalsAgainstPerGame(team1), getGoalsAgainstPerGame(team2))
                .compare(getGamesWonPerGame(team1), getGamesWonPerGame(team2))
                .compare(getHashCode(team1), getHashCode(team2)).result();
    }

    private int getHashCode(Team team) {
        return team.hashCode();
    }

    private double getGamesWonPerGame(Team team) {
        return Double.valueOf(team.getGamesWon()) / Double.valueOf(team.getGamesPlayed());
    }

    private double getGoalsForPerGame(Team team) {
        return Double.valueOf(team.getGoalsFor()) / Double.valueOf(team.getGamesPlayed());
    }

    private double getGoalsAgainstPerGame(Team team) {
        return Double.valueOf(team.getGoalsAgainst()) / Double.valueOf(team.getGamesPlayed());
    }

    private double getPointsPerGame(Team team) {
        return Double.valueOf(team.getPoints()) / Double.valueOf(team.getGamesPlayed());
    }
}
