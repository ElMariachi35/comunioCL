package com.comunio.comparator;

import java.util.Comparator;

import com.comunio.model.Team;

public class TeamByTeamnameComparator implements Comparator<Team>{

    @Override
    public int compare(Team team1, Team team2) {
	return team1.getTeamName().compareToIgnoreCase(team2.getTeamName());
    }

}
