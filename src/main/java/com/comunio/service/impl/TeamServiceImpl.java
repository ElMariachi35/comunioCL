package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.TeamDao;
import com.comunio.model.Groupe;
import com.comunio.model.Team;
import com.comunio.service.GroupService;
import com.comunio.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private GroupService groupService;
	@Autowired
	private TeamDao teamDao;
	
	@Transactional
	public void saveTeam(Team team, Groupe group) {
		team.setGroupe(group);
		teamDao.saveTeam(team);
	}

	public List<Team> createTeamsFromString(String teamsString) {
		String[] teamsStringArr = teamsString.split(";");
		List<Team> teams = new ArrayList<>();
		for(int i=0;i<teamsStringArr.length;i++){
			Team team = new Team();
			team.setTeamName(teamsStringArr[i]);
			teams.add(team);
		}
		return teams;
	}
	
}
