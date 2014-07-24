package com.comunio.service;

import java.io.Serializable;
import java.util.Map;

import com.comunio.model.PlayoffFinale;
import com.comunio.model.Team;

public interface PlayoffInitializationService extends Serializable {

    void initializePlayoff();

    void initializeSemiFinal(Map<Integer, Team> map);

    void initializeFinal(Map<Integer, Team> promotedTeams);

    PlayoffFinale createFinal(Team team1, Team team2);
}
