package com.comunio.service;

import java.util.List;

import com.comunio.model.Groupe;

public interface GroupService {
	public Groupe getGroup(long comunioId, String groupName);
	public List<Groupe> findGroupsByComunioId(long comunioId);
	public void initializeGroups(long comunioId, int numberOfTeams, String teamsString);
	public List<String> determineGroupNames(long comunioId);

}
