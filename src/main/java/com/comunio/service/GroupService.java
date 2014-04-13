package com.comunio.service;

import java.util.List;
import java.util.Set;

import com.comunio.model.Groupe;

public interface GroupService {
    public Groupe getGroup(long comunioId, String groupName);

    public List<Groupe> findGroupsByComunioId(long comunioId);

    public void initializeGroups(int numberOfTeams, String teamsString);

    public List<String> determineGroupNames(int numberOfGroups);

    public Groupe getGroup(String groupName);

    public Set<Groupe> getGroups();

}
