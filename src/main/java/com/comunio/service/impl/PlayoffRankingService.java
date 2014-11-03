package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.comunio.comparator.PlayoffTeamComparator;
import com.comunio.model.Groupe;
import com.comunio.model.Team;

@Service
public class PlayoffRankingService {

	public Map<Integer, Team> determinePlayoffTeam(List<Groupe> groups,
			int numberOfTeams) {
		List<Team> preselectedTeams = getPreselectedTeams(groups, numberOfTeams);
		preselectedTeams.addAll(determineRemainingTeams(groups,
				preselectedTeams.size()));
		List<Team> sortedPlayoffTeams = sortPreselectedTeams(preselectedTeams);
		return createPlayoffMap(sortedPlayoffTeams);
	}

	private List<Team> getPreselectedTeams(List<Groupe> groups,
			int numberOfTeams) {
		List<Team> playoffTeams = new ArrayList<Team>();

		if (numberOfTeams < 8 || numberOfTeams == 11) {
			for (Groupe groupe : groups) {
				playoffTeams.addAll(getPlayoffTeam(groupe, 4));
			}
			return playoffTeams;
		}
		if (numberOfTeams >= 8) {
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

	private List<Team> determineRemainingTeams(List<Groupe> groups,
			int numberOfPreselectedTeams) {
		if (numberOfPreselectedTeams == 4 || numberOfPreselectedTeams == 8) {
			return new ArrayList<Team>();
		}
		List<Team> teams = new ArrayList<Team>();
		for (Groupe groupe : groups) {
			List<Team> sortedTeams = groupe.getSortedTeams();
			teams.add(sortedTeams.get(2));
		}
		Collections.sort(teams, new PlayoffTeamComparator());
		return teams.subList(0, 2);
	}

	private Map<Integer, Team> createPlayoffMap(List<Team> teams) {
		Map<Integer, Team> playoffTeams = new HashMap<Integer, Team>();
		for (int i = 0; i < teams.size(); i++) {
			playoffTeams.put(i + 1, teams.get(i));
		}
		return playoffTeams;
	}

	private List<Team> getPlayoffTeam(Groupe group, int numberOfTeams) {
		List<Team> playoffTeams = new ArrayList<Team>();
		List<Team> sortedTeams = group.getSortedTeams();
		for (int i = 0; i < numberOfTeams; i++) {
			playoffTeams.add(sortedTeams.get(i));
		}
		return playoffTeams;
	}
}
