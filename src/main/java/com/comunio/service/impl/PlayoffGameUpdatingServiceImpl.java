package com.comunio.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.comunio.model.PlayoffGame;
import com.comunio.model.Result;
import com.comunio.model.Team;
import com.comunio.service.PlayoffGameUpdatingService;

@Service
public class PlayoffGameUpdatingServiceImpl implements PlayoffGameUpdatingService {

    @Override
    public void updatePlayoffGame(PlayoffGame playoffGame, List<Result> results) {
        Team homeTeam = playoffGame.getHomeTeam();
        Team awayTeam = playoffGame.getAwayTeam();
        for (Result result : results) {
            if (result.getTeam().getTeamId() == homeTeam.getTeamId()) {
                playoffGame.setHomeGoals(result.getGoals());
            }
            if (result.getTeam().getTeamId() == awayTeam.getTeamId()) {
                playoffGame.setAwayGoals(result.getGoals());
            }
        }
    }
}
