package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.GroupDao;
import com.comunio.model.Groupe;
import com.comunio.model.SessionData;
import com.comunio.model.Team;
import com.comunio.service.ComunioService;
import com.comunio.service.FixtureService;
import com.comunio.service.GroupService;
import com.comunio.service.TeamService;

@Service
public class GroupServiceImpl implements GroupService {
    private static final String GROUP_NAME_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    @Autowired
    GroupDao groupDao;
    @Autowired
    ComunioService comunioService;
    @Autowired
    TeamService teamService;
    @Autowired
    FixtureService fixtureService;
    @Autowired
    SessionData sessionData;

    @Transactional
    public void initializeGroups(long comunioId, int numberOfTeams, String teamsString) {
        List<Groupe> groups = createGroups(comunioId, determineNumberOfGroups(numberOfTeams));
        Map<Groupe, Integer> groupSizes = determineGroupSizes(numberOfTeams, groups);
        List<Team> teams = createShuffledTeams(teamsString);

        for (Groupe group : groups) {
            setUpGroup(teams, groupSizes.get(group), group);
        }
    }

    private List<Team> createShuffledTeams(String teamsString) {
        List<Team> teams = new ArrayList<>();
        teams = teamService.createTeamsFromString(teamsString);
        Collections.shuffle(teams);
        return teams;
    }

    private void setUpGroup(List<Team> teams, int groupSize, Groupe group) {
        Set<Team> teamsInGroup = new HashSet<>();
        for (int i = 0; i < groupSize; i++) {
            Team team = teams.remove(0);
            teamsInGroup.add(team);
            teamService.saveTeam(team, group);
        }
        group.setTeams(teamsInGroup);
        fixtureService.createFixture(group);
    }

    @Transactional
    public List<Groupe> findGroupsByComunioId(long comunioId) {
        return groupDao.findGroupsByComunioId(comunioId);
    }

    @Transactional
    public Groupe getGroup(long comunioId, String groupName) {
        return groupDao.getGroup(comunioId, groupName);
    }

    @Transactional
    public List<String> determineGroupNames(int numberOfGroups) {
        List<String> groupNames = new ArrayList<>();
        for (int i = 0; i < numberOfGroups; i++) {
            groupNames.add(GROUP_NAME_STRING.charAt(i) + "");
        }
        return groupNames;
    }

    private List<Groupe> createGroups(long comunioId, int numberOfGroups) {
        List<Groupe> groups = new ArrayList<>();
        String groupNames = GROUP_NAME_STRING;
        for (int i = 0; i < numberOfGroups; i++) {
            Groupe group = new Groupe();
            group.setGroupName(groupNames.charAt(i) + "");
            comunioService.loadComunio(comunioId);
            group.setComunio(comunioService.getComunio());
            groupDao.add(group);
            groups.add(group);
        }
        return groups;
    }

    private Map<Groupe, Integer> determineGroupSizes(int numberOfTeams, List<Groupe> groups) {
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

    private int determineNumberOfGroups(int numberOfTeams) {
        if (numberOfTeams <= 7) {
            return 1;
        } else if (numberOfTeams <= 11) {
            return 2;
        } else if (numberOfTeams <= 15) {
            return 3;
        } else {
            return 4;
        }
    }

    public Groupe getGroup(String groupName) {
        for (Groupe group : sessionData.getComunio().getGroups()) {
            if (group.getGroupName().equals(groupName)) {
                return group;
            }
        }
        throw new NoResultException();
    }

    @Override
    public Set<Groupe> getGroups() {
        return sessionData.getComunio().getGroups();
    }

}
