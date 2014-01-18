package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.ScheduleDao;
import com.comunio.model.Groupe;
import com.comunio.model.Schedule;
import com.comunio.model.Team;
import com.comunio.service.GroupService;
import com.comunio.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private GroupService groupService;
	@Autowired
	private ScheduleDao scheduleDao;
	
	@Transactional
	public void addSchedule(Schedule schedule, Groupe group) {
		schedule.setGroupe(group);
		scheduleDao.addSchedule(schedule);
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
