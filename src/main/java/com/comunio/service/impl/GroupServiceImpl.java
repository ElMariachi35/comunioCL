package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.GroupDao;
import com.comunio.model.Groupe;
import com.comunio.model.Team;
import com.comunio.service.ComunioService;
import com.comunio.service.GroupService;
import com.comunio.service.ScheduleService;
import com.comunio.service.TeamService;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupDao groupDao;
	@Autowired
	private ComunioService comunioService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private ScheduleService scheduleService;

	@Transactional
	public void add(Groupe group) {
		getGroupDao().add(group);
	}

	@Transactional
	public void edit(Groupe group) {
		getGroupDao().edit(group);
	}

	@Transactional
	public void delete(long groupId) {
		getGroupDao().delete(groupId);
	}

	@Transactional
	public Groupe getGroup(long groupId) {
		return getGroupDao().getGroup(groupId);
	}

	@Transactional
	public void initializeGroups(long comunioId, int numberOfTeams,
			int numberOfGroups, String teamsString) {
		List<Groupe> groups = createGroups(comunioId, numberOfGroups);
		List<Team> teams = getTeamService().createTeamsFromString(teamsString);
		Collections.shuffle(teams);
		Map<Groupe, Integer> groupSizes = determineGroupSizes(numberOfTeams,
				groups);

		for (Groupe group : groups) {
			int size = groupSizes.get(group);
			Set<Team> teamsInGroup = new HashSet<>();
			for (int i = 0; i < size; i++) {
				Team team = teams.remove(0);
				teamsInGroup.add(team);
				getTeamService().saveTeam(team, group);
			}
			group.setTeams(teamsInGroup);
			scheduleService.createSchedule(group);
		}
	}

	@Transactional
	public List<Groupe> findGroupsByComunioId(long comunioId) {
		return groupDao.findGroupsByComunioId(comunioId);
	}

	@Transactional
	public Groupe getGroup(long comunioId, String groupName) {
		return groupDao.getGroup(comunioId, groupName);
	}

	public GroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public ComunioService getComunioService() {
		return comunioService;
	}

	public void setComunioService(ComunioService comunioService) {
		this.comunioService = comunioService;
	}

	public TeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	private List<Groupe> createGroups(long comunioId, int numberOfGroups) {
		List<Groupe> groups = new ArrayList<>();
		String groupNames = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < numberOfGroups; i++) {
			Groupe group = new Groupe();
			group.setGroupName(groupNames.charAt(i) + "");
			group.setComunio(getComunioService().getComunio(comunioId));
			getGroupDao().add(group);
			groups.add(group);
		}
		return groups;
	}

	private Map<Groupe, Integer> determineGroupSizes(int numberOfTeams,
			List<Groupe> groups) {
		Map<Groupe, Integer> groupSizes = new HashMap<>();
		int remainder = getRemainder(numberOfTeams, groups);
		int groupSize = getGroupSize(numberOfTeams, groups);
		int counter = 0;
		for (Groupe group : groups) {
			if (counter < remainder) {
				groupSizes.put(group, groupSize + 1);
			} else {
				groupSizes.put(group, groupSize);
			}
			counter++;
		}
		return groupSizes;
	}

	private int getGroupSize(int numberOfTeams, List<Groupe> group) {
		return numberOfTeams / group.size();
	}

	private int getRemainder(int numberOfTeams, List<Groupe> group) {
		return numberOfTeams % group.size();
	}

}
