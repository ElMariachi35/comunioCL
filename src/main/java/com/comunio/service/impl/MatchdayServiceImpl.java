package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunio.dao.MatchdayDao;
import com.comunio.model.Groupe;
import com.comunio.model.Match;
import com.comunio.model.Matchday;
import com.comunio.model.Schedule;
import com.comunio.model.Team;
import com.comunio.service.MatchdayService;

@Service
public class MatchdayServiceImpl implements MatchdayService{

	@Autowired
	private MatchdayDao matchdayDao;
	
	@Override
	public Set<Matchday> createMatchdays(Groupe group, Schedule schedule) {
		List<Matchday> matchdays = new ArrayList<Matchday>();
		
		List<Team> teams = new ArrayList<>(group.getTeams());
		if(teams.size()%2!=0){
			Team team = new Team();
			team.setTeamName("Dummy");
			teams.add(team);
			
		}
		
		for(int i=0;i<teams.size()-1;i++){
			Matchday matchday = new Matchday();
			matchday.setComunioMatchdayNumber(i+1);
			matchday.setSchedule(schedule);
			List<Match> matches = new ArrayList<Match>();
			for(int j=0;j<teams.size()/2;j++){
				Match match = new Match();
				match.setHomeTeam(teams.get(j));
				match.setAwayTeam(teams.get(j+teams.size()/2));
				matches.add(match);
			}
			matchday.setMatches(new HashSet(matches));
			matchdays.add(matchday);
			Team firstFixedTeam = teams.remove(0);
			Collections.rotate(teams, 1);
			teams.add(0, firstFixedTeam);
		}
		for(Matchday matchday : matchdays){	
			System.out.println(matchday.getComunioMatchdayNumber());
			for(Match match : matchday.getMatches()){
				System.out.println(match.getHomeTeam().getTeamName()+" : "+match.getAwayTeam().getTeamName());
			}
			System.out.println();
		}
		return new HashSet(matchdays);
	}	
}
