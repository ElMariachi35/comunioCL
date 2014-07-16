package com.comunio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunio.dao.PlayoffGameDao;
import com.comunio.model.PlayoffGame;
import com.comunio.model.Team;
import com.comunio.service.PlayoffGameService;

@Service
public class PlayoffGameServiceImpl implements PlayoffGameService {
    @Autowired
    PlayoffGameDao playoffGameDao;

    public PlayoffGame createPlayoffGame(Team homeTeam, Team awayTeam) {
        PlayoffGame game = new PlayoffGame();
        game.setHomeTeam(homeTeam);
        game.setAwayTeam(awayTeam);
        game.setHomeGoals(0);
        game.setAwayGoals(0);
        playoffGameDao.save(game);
        return game;
    }

    @Override
    public void update(PlayoffGame playoffGame) {
        playoffGameDao.update(playoffGame);
    }
}
