package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.comunio.model.Game;
import com.comunio.model.Matchday;
import com.comunio.model.Schedule;
import com.comunio.model.Team;

public class RoundRobinServiceImpl {
	
	public Schedule roundRobinCreateSchedule(List<Team> teams) {
		Schedule schedule = new Schedule();
		schedule.setMatchdays(createMatchdays(teams, schedule));
		return schedule;
	}

	private Set<Matchday> createMatchdays(List<Team> teams, Schedule schedule) {
		List<Matchday> matchdays = new ArrayList<>();
		List<Team> team1 = new ArrayList<>();
		List<Team> team2 = new ArrayList<>();
		List<Team> team3 = new ArrayList<>();
		List<Team> team4 = new ArrayList<>();
		
		if(teams.size()%2!=0){
			Team dummyTeam = new Team();
			dummyTeam.setTeamName("DummyTeam");
			teams.add(dummyTeam);
		}

		team1 = teams.subList(0, teams.size()/2);
		team2 = teams.subList(teams.size()/2, teams.size());
		
		for(int matchdayNumber=0;matchdayNumber<teams.size()-1;matchdayNumber++){
			matchdays.add(createMatches(team1, team2, matchdayNumber, schedule));
			Collections.rotate(team1, 1);
			Collections.rotate(team2, -1);
			team3 = new ArrayList<>(team1);
			team4 = new ArrayList<>(team2);
				
			team4.set(team4.size()-1, team1.get(0));
			team3.set(0, team1.get(1));
			team3.set(1, team2.get(team2.size()-1));

			team1 = new ArrayList<>(team3);
			team2 = new ArrayList<>(team4);
		}
		return new HashSet<>(matchdays);
	}

	private Matchday createMatches(List<Team> team1, List<Team> team2, int matchdayNumber, Schedule schedule) {
		Matchday matchday = new Matchday();
		matchday.setComunioMatchdayNumber(matchdayNumber+1);
		matchday.setSchedule(schedule);
		List<Game> games = new ArrayList<>();
		for(int j=0;j<team1.size();j++){
			Game game = new Game();
			game.setMatchday(matchday);
			game.setHomeTeam(team1.get(j));
			game.setAwayTeam(team2.get(j));
			games.add(game);
		}
		matchday.setMatches(new HashSet<>(games));
		return matchday;
	}
}
