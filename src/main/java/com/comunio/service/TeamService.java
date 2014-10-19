package com.comunio.service;

import java.util.List;

import com.comunio.model.Groupe;
import com.comunio.model.Team;

public interface TeamService {
    public void saveTeam(Team team, Groupe group);

    public List<Team> createTeamsFromString(String teamsString);

    public void updateTeam(Team team);

    public Team findTeamByTeamNameAndComunioId(String string, long comunioId);

    public Team findBy(long teamId);
    
    public long findNumberOfTeams(long comunioId);
}
