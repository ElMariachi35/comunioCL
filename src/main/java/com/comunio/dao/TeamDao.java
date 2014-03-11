package com.comunio.dao;

import java.util.List;

import com.comunio.model.Team;

public interface TeamDao {
    void saveTeam(Team team);

    List<String> findTeamNamesByComunioId(long comunioId);

    Team findTeamByTeamNameAndComunioId(String teamName, long comunioId);

    void updateTeam(Team team);
}
