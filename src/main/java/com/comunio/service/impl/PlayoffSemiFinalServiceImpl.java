package com.comunio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunio.model.KnockoutPairing;
import com.comunio.model.Playoff;
import com.comunio.model.PlayoffFixture;
import com.comunio.model.PlayoffGame;
import com.comunio.model.Result;
import com.comunio.model.SessionData;
import com.comunio.model.Team;
import com.comunio.service.PlayoffGameUpdatingService;
import com.comunio.service.PlayoffInitializationService;
import com.comunio.service.PlayoffPromotedTeamsService;
import com.comunio.service.PlayoffSemiFinalService;

@Service
public class PlayoffSemiFinalServiceImpl implements PlayoffSemiFinalService {

    @Autowired
    SessionData sessionData;
    @Autowired
    PlayoffInitializationService playoffInitializationService;
    @Autowired
    PlayoffGameUpdatingService playoffGameUpdatingService;
    @Autowired
    PlayoffPromotedTeamsService playoffPromotedTeamsService;

    @Override
    public Playoff updateFirstLeg(List<Result> results, Playoff playoff) {
        PlayoffFixture semiFinal = playoff.getSemiFinal();
        if (semiFinal == null) {
            return null;
        }
        for (KnockoutPairing pairing : semiFinal.getPairings()) {
            PlayoffGame firstLeg = pairing.getFirstLeg();
            firstLeg = playoffGameUpdatingService.updatePlayoffGame(firstLeg, results);
            pairing.setFirstLeg(firstLeg);
        }
        return playoff;
    }

    @Override
    public Playoff updateSecondLeg(List<Result> results, Playoff playoff) {
        PlayoffFixture semiFinal = playoff.getSemiFinal();
        if (semiFinal == null) {
            return null;
        }
        int index = 1;
        for (KnockoutPairing pairing : semiFinal.getPairings()) {
            PlayoffGame secondLeg = pairing.getSecondLeg();
            secondLeg = playoffGameUpdatingService.updatePlayoffGame(secondLeg, results);
            Team promotedTeam = playoffPromotedTeamsService.determinePromotedTeam(pairing);
            pairing.setSecondLeg(secondLeg);
            pairing.setPromotedTeam(promotedTeam);
            semiFinal.getPromotedTeams().put(index, promotedTeam);
            index++;
        }
        return playoffInitializationService.initializeFinal(semiFinal.getPromotedTeams(), playoff);
    }
}
