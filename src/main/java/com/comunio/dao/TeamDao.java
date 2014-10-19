package com.comunio.dao;

import com.comunio.model.Team;

public interface TeamDao {
    void saveTeam(Team team);

    void updateTeam(Team team);

    Team findBy(long teamId);

    long findNumberOfTeams(long comunioId);
}
