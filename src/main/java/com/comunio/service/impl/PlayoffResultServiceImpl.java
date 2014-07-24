package com.comunio.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunio.model.Playoff;
import com.comunio.model.Result;
import com.comunio.model.SessionData;
import com.comunio.service.PlayoffFinalService;
import com.comunio.service.PlayoffInitializationService;
import com.comunio.service.PlayoffQuaterFinalService;
import com.comunio.service.PlayoffResultService;
import com.comunio.service.PlayoffSemiFinalService;
import com.comunio.service.PlayoffService;

@Service
public class PlayoffResultServiceImpl implements PlayoffResultService, Serializable {

    private static final int MATCHDAY_NUMBER_QUATER_FINAL_FIRST_LEG = 11;
    private static final int MATCHDAY_NUMBER_QUATER_FINAL_SECOND_LEG = 12;
    private static final int MATCHDAY_NUMBER_SEMI_FINAL_FIRST_LEG = 13;
    private static final int MATCHDAY_NUMBER_SEMI_FINAL_SECOND_LEG = 14;
    private static final int MATCHDAY_NUMBER_PLAYOFF_FINAL_FIRST_LEG = 15;
    private static final int MATCHDAY_NUMBER_PLAYOFF_FINAL_SECOND_LEG = 16;
    private static final int MATCHDAY_NUMBER_PLAYOFF_FINAL_THIRD_LEG = 17;

    @Autowired
    SessionData sessionData;
    @Autowired
    PlayoffService playoffService;
    @Autowired
    PlayoffInitializationService playoffInitializationService;
    @Autowired
    PlayoffQuaterFinalService playoffQuaterFinalService;
    @Autowired
    PlayoffSemiFinalService playoffSemiFinalService;
    @Autowired
    PlayoffFinalService playoffFinalService;

    @Override
    public void handlePlayoff(Map<Integer, List<Result>> preparedResults) {
        if (preparedResults.isEmpty()) {
            return;
        }

        playoffInitializationService.initializePlayoff();
        Playoff playoff = sessionData.getComunio().getPlayoff();
        // for (Integer matchdayNumber : preparedResults.keySet().) {
        for (int matchdayNumber = 10; matchdayNumber < 18; matchdayNumber++) {
            System.out.println("#################################   " + matchdayNumber);
            List<Result> results = preparedResults.get(matchdayNumber);
            if (results == null || results.isEmpty()) {
                continue;
            }
            switch (matchdayNumber) {
            case MATCHDAY_NUMBER_QUATER_FINAL_FIRST_LEG:
                playoffQuaterFinalService.updateFirstLeg(results);
                break;
            case MATCHDAY_NUMBER_QUATER_FINAL_SECOND_LEG:
                playoffQuaterFinalService.updateSecondLeg(results);
                break;
            case MATCHDAY_NUMBER_SEMI_FINAL_FIRST_LEG:
                playoffSemiFinalService.updateFirstLeg(results);
                break;
            case MATCHDAY_NUMBER_SEMI_FINAL_SECOND_LEG:
                playoffSemiFinalService.updateSecondLeg(results);
                break;
            case MATCHDAY_NUMBER_PLAYOFF_FINAL_FIRST_LEG:
                playoffFinalService.updateFirstLeg(results);
                break;
            case MATCHDAY_NUMBER_PLAYOFF_FINAL_SECOND_LEG:
                playoffFinalService.updateSecondLeg(results);
                break;
            case MATCHDAY_NUMBER_PLAYOFF_FINAL_THIRD_LEG:
                playoffFinalService.updateThirdLeg(results);
                break;
            }
        }
        playoffService.save(playoff);
    }
}
