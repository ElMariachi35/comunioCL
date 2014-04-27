package com.comunio.service;

import com.comunio.model.PlayoffGame;
import com.comunio.model.Team;

public interface PlayoffGameService {
    public PlayoffGame createPlayoffGame(Team homeTeam, Team awayTeam);
}
