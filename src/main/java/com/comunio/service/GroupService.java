package com.comunio.service;

import java.util.List;

import com.comunio.model.Groupe;

public interface GroupService {
	public void add(Groupe group);
	public void edit(Groupe group);
	public void delete(long groupId);
	public Groupe getGroup(long groupId);
	public Groupe getGroup(long comunioId, String groupName);
	public List<Groupe> findGroupsByComunioId(long comunioId);
	public void initializeGroups(long comunioId, int numberOfTeams, int numberOfGroups, String teamsString);
	
}
