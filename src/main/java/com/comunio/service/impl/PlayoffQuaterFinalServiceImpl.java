package com.comunio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunio.model.KnockoutPairing;
import com.comunio.model.PlayoffFixture;
import com.comunio.model.PlayoffGame;
import com.comunio.model.Result;
import com.comunio.model.SessionData;
import com.comunio.model.Team;
import com.comunio.service.PlayoffGameUpdatingService;
import com.comunio.service.PlayoffInitializationService;
import com.comunio.service.PlayoffPromotedTeamsService;
import com.comunio.service.PlayoffQuaterFinalService;

@Service
public class PlayoffQuaterFinalServiceImpl implements PlayoffQuaterFinalService {

    @Autowired
    SessionData sessionData;
    @Autowired
    PlayoffInitializationService playoffInitializationService;
    @Autowired
    PlayoffGameUpdatingService playoffGameUpdatingService;
    @Autowired
    PlayoffPromotedTeamsService playoffPromotedTeamsService;

    @Override
    public void updateFirstLeg(List<Result> results) {
        PlayoffFixture quaterFinal = sessionData.getComunio().getPlayoff().getQuaterFinal();
        if (quaterFinal == null) {
            return;
        }
        for (KnockoutPairing pairing : quaterFinal.getPairings()) {
            PlayoffGame firstLeg = pairing.getFirstLeg();
            playoffGameUpdatingService.updatePlayoffGame(firstLeg, results);
        }
    }

    public void updateSecondLeg(List<Result> results) {
        PlayoffFixture quaterFinal = sessionData.getComunio().getPlayoff().getQuaterFinal();
        if (quaterFinal == null) {
            return;
        }
        int index = 1;
        for (KnockoutPairing pairing : quaterFinal.getPairings()) {
            PlayoffGame secondLeg = pairing.getSecondLeg();
            playoffGameUpdatingService.updatePlayoffGame(secondLeg, results);
            Team promotedTeam = playoffPromotedTeamsService.determinePromotedTeam(pairing);
            pairing.setPromotedTeam(promotedTeam);
            quaterFinal.getPromotedTeams().put(index, promotedTeam);
            index++;
        }
        playoffInitializationService.initializeSemiFinal(quaterFinal.getPromotedTeams());
    }
}
