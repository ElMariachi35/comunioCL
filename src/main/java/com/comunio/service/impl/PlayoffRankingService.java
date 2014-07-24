package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.comunio.comparator.PlayoffTeamComparator;
import com.comunio.model.Groupe;
import com.comunio.model.Team;

@Service
public class PlayoffRankingService {

    public Map<Integer, Team> determinePlayoffTeam(Set<Groupe> groups, int numberOfTeams) {
        List<Team> preselectedTeams = getPreselectedTeams(groups, numberOfTeams);
        List<Team> sortedPlayoffTeams = sortPreselectedTeams(preselectedTeams);
        return createPlayoffMap(sortedPlayoffTeams);
    }

    private List<Team> getPreselectedTeams(Set<Groupe> groups, int numberOfTeams) {
        List<Team> playoffTeams = new ArrayList<>();
        if (numberOfTeams < 7) {
            playoffTeams = getPlayoffTeam(groups.iterator().next(), 4);
        } else if (numberOfTeams < 11) {
            for (Groupe groupe : groups) {
                playoffTeams.addAll(getPlayoffTeam(groupe, 2));
            }
        } else if (numberOfTeams == 11) {
            for (Groupe groupe : groups) {
                playoffTeams.addAll(getPlayoffTeam(groupe, 4));
            }
        } else if (numberOfTeams < 16) {
            for (Groupe groupe : groups) {
                playoffTeams.addAll(getPlayoffTeam(groupe, 3));
            }
        } else {
            for (Groupe groupe : groups) {
                playoffTeams.addAll(getPlayoffTeam(groupe, 2));
            }
        }
        return playoffTeams;
    }

    private List<Team> sortPreselectedTeams(List<Team> preselectedTeams) {
        List<Team> sortedTeams = new ArrayList<Team>(preselectedTeams);
        Collections.sort(sortedTeams, new PlayoffTeamComparator());
        if (sortedTeams.size() > 8) {
            return sortedTeams.subList(0, 8);
        }
        return sortedTeams;
    }

    private Map<Integer, Team> createPlayoffMap(List<Team> teams) {
        Map<Integer, Team> playoffTeams = new HashMap<>();
        for (int i = 0; i < teams.size(); i++) {
            playoffTeams.put(i + 1, teams.get(i));
        }
        return playoffTeams;
    }

    private List<Team> getPlayoffTeam(Groupe group, int numberOfTeams) {
        List<Team> playoffTeams = new ArrayList<>();
        List<Team> sortedTeams = group.getSortedTeams();
        for (int i = 0; i < numberOfTeams; i++) {
            playoffTeams.add(sortedTeams.get(i));
        }
        return playoffTeams;
    }
}
