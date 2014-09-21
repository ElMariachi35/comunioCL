package com.comunio.service;

import java.io.Serializable;
import java.util.Map;

import com.comunio.model.Playoff;
import com.comunio.model.PlayoffFinale;
import com.comunio.model.Team;

public interface PlayoffInitializationService extends Serializable {

    Playoff initializePlayoff(Playoff playoff, long comunioId);

    Playoff initializeSemiFinal(Map<Integer, Team> map, Playoff playoff);

    Playoff initializeFinal(Map<Integer, Team> promotedTeams, Playoff playoff);

    PlayoffFinale createFinal(Team team1, Team team2);
}
