package com.comunio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunio.model.Playoff;
import com.comunio.model.PlayoffFinale;
import com.comunio.model.PlayoffGame;
import com.comunio.model.Result;
import com.comunio.model.SessionData;
import com.comunio.service.PlayoffFinalService;
import com.comunio.service.PlayoffGameUpdatingService;

@Service
public class PlayoffFinalServiceImpl implements PlayoffFinalService {

    @Autowired
    SessionData sessionData;
    @Autowired
    PlayoffGameUpdatingService playoffGameUpdatingService;

    @Override
    public void updateFirstLeg(List<Result> results, Playoff playoff) {
        PlayoffFinale playoffFinal = playoff.getPlayoffFinal();
        if (playoffFinal == null) {
            return;
        }
        PlayoffGame firstLeg = playoffFinal.getFirstLeg();
        playoffGameUpdatingService.updatePlayoffGame(firstLeg, results);
        playoffFinal.setFirstLeg(firstLeg);
    }

    @Override
    public void updateSecondLeg(List<Result> results, Playoff playoff) {
        PlayoffFinale playoffFinal = playoff.getPlayoffFinal();
        if (playoffFinal == null) {
            return;
        }
        PlayoffGame secondLeg = playoffFinal.getSecondLeg();
        playoffGameUpdatingService.updatePlayoffGame(secondLeg, results);
        playoffFinal.setSecondLeg(secondLeg);
    }

    @Override
    public void updateThirdLeg(List<Result> results, Playoff playoff) {
        PlayoffFinale playoffFinal = playoff.getPlayoffFinal();
        if (playoffFinal == null) {
            return;
        }
        PlayoffGame thirdLeg = playoffFinal.getThirdLeg();
        playoffGameUpdatingService.updatePlayoffGame(thirdLeg, results);
        playoffFinal.setThirdLeg(thirdLeg);
    }
}
