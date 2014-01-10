package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.TeamDao;
import com.comunio.model.Team;
import com.comunio.service.ComunioService;
import com.comunio.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private ComunioService comunioService;
	@Autowired
	private TeamDao teamDao;
	
	@Transactional
	public void addTeamsFromString(String teamsString, long comunioId) {
		List<Team> teams = createTeamsFromString(teamsString, comunioId);
		teamDao.addTeams(teams);
	}

	private List<Team> createTeamsFromString(String teamsString, long comunioId) {
		String[] teamsStringArr = teamsString.split(";");
		List<Team> teams = new ArrayList<>();
		for(int i=0;i<teamsStringArr.length;i++){
			Team team = new Team();
			team.setTeamName(teamsStringArr[i]);
			team.setComunio(comunioService.getComunio(comunioId));
			teams.add(team);
		}
		return teams;
	}
	
}
