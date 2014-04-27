package com.comunio.service;

import java.util.Map;

import com.comunio.model.Team;

public interface PlayoffService {
    void initializePlayoffs(Map<Integer, Team> teams);
    void initializeSemifinal(Map<Integer, Team> teams);
    void initializeFinal(Team team1, Team team2);
}
