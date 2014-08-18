package com.comunio.service.impl;

import org.springframework.stereotype.Service;

import com.comunio.model.PlayoffGame;
import com.comunio.model.Team;
import com.comunio.service.PlayoffGameService;

@Service
public class PlayoffGameServiceImpl implements PlayoffGameService {

    public PlayoffGame createPlayoffGame(Team homeTeam, Team awayTeam) {
        PlayoffGame game = new PlayoffGame();
        game.setHomeTeam(homeTeam);
        game.setAwayTeam(awayTeam);
        game.setHomeGoals(0);
        game.setAwayGoals(0);
        return game;
    }
}
