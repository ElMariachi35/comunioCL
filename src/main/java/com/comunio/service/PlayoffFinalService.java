package com.comunio.service;

import com.comunio.model.PlayoffFinale;
import com.comunio.model.Team;

public interface PlayoffFinalService {

    PlayoffFinale createFinal(Team team1, Team team2);

}
