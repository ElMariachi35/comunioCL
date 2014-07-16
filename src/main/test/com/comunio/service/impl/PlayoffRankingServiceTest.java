package com.comunio.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.comunio.model.Groupe;
import com.comunio.model.Team;

public class PlayoffRankingServiceTest {
    PlayoffRankingService playoffRankingService = new PlayoffRankingService();

    @Test
    public void returnsCorrectMapForFourTeamComunio() {
        Set<Groupe> groups = createGroups(1, 4);
        System.out.println(groups.size());
        Map<Integer, Team> playoffTeams = playoffRankingService.determinePlayoffTeam(groups, 4);
        assertEquals(4l, playoffTeams.get(1).getTeamId());
        assertEquals(3l, playoffTeams.get(2).getTeamId());
        assertEquals(2l, playoffTeams.get(3).getTeamId());
        assertEquals(1l, playoffTeams.get(4).getTeamId());
    }

    private Set<Groupe> createGroups(int numberOfGroups, int numberOfTeamsPerGroup) {
        Set<Groupe> groups = new HashSet<Groupe>();
        for (int i = 0; i < numberOfGroups; i++) {
            Groupe groupe = new Groupe();
            groupe.setTeams(createTeams(numberOfTeamsPerGroup));
            groups.add(groupe);

        }
        return groups;
    }

    private Set<Team> createTeams(int numberOfTeamsPerGroup) {
        Set<Team> teams = new HashSet<Team>();
        for (int i = 0; i < numberOfTeamsPerGroup; i++) {
            Team team = new Team();
            team.setPoints(i + 1);
            team.setTeamId(i + 1);
            team.setGamesPlayed(3);
            team.setGoalsFor(4);
            team.setGoalsAgainst(3);
            team.setGamesWon(1);
            team.setGamesDrawn(2);
            team.setGamesLost(4);
            team.setGoalDifference(-2);
            team.setTeamName("team" + i);
            teams.add(team);
        }
        return teams;
    }
}
