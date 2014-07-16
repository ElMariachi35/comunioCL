package com.comunio.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunio.model.Groupe;
import com.comunio.model.KnockoutPairing;
import com.comunio.model.Playoff;
import com.comunio.model.PlayoffFixture;
import com.comunio.model.PlayoffGame;
import com.comunio.model.Result;
import com.comunio.model.SessionData;
import com.comunio.model.Team;
import com.comunio.service.KnockoutPairingService;
import com.comunio.service.PlayoffGameService;
import com.comunio.service.PlayoffResultService;
import com.comunio.service.PlayoffService;
import com.comunio.service.TeamService;

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
    TeamService teamService;
    @Autowired
    PlayoffService playoffService;
    @Autowired
    ResultGoalFinder resultGoalFinder;
    @Autowired
    PlayoffGameService playoffGameService;
    @Autowired
    PlayoffRankingService playoffRankingService;
    @Autowired
    KnockoutPairingService knockoutPairingService;

    public void handlePlayoff(List<Result> results) {
        for (Result result : results) {
            if (result.getMatchday() == 10) {
                initializePlayoff();
            }
            Playoff playoff = sessionData.getComunio().getPlayoff();
            if (result.getMatchday() == MATCHDAY_NUMBER_QUATER_FINAL_FIRST_LEG) {
                PlayoffFixture quaterFinal = playoff.getQuaterFinal();
                if (quaterFinal != null) {
                    Set<KnockoutPairing> quaterFinalsPairings = quaterFinal.getPairings();
                    for (KnockoutPairing knockoutPairing : quaterFinalsPairings) {
                        updatePlayoffGame(results, knockoutPairing.getFirstLeg(),
                                MATCHDAY_NUMBER_QUATER_FINAL_FIRST_LEG);
                    }
                }
            }
            if (result.getMatchday() == MATCHDAY_NUMBER_QUATER_FINAL_SECOND_LEG) {
                PlayoffFixture quaterFinal = playoff.getQuaterFinal();
                if (quaterFinal != null) {
                    Set<KnockoutPairing> quaterFinalPairings = quaterFinal.getPairings();
                    for (KnockoutPairing knockoutPairing : quaterFinalPairings) {
                        updatePlayoffGame(results, knockoutPairing.getSecondLeg(),
                                MATCHDAY_NUMBER_QUATER_FINAL_SECOND_LEG);
                    }
                    finishQuaterFinal();
                }
            }
            if (result.getMatchday() == MATCHDAY_NUMBER_SEMI_FINAL_FIRST_LEG) {
                Set<KnockoutPairing> semiFinal = playoff.getSemiFinal().getPairings();
                for (KnockoutPairing knockoutPairing : semiFinal) {
                    updatePlayoffGame(results, knockoutPairing.getFirstLeg(), MATCHDAY_NUMBER_SEMI_FINAL_FIRST_LEG);
                }
            }
            if (result.getMatchday() == MATCHDAY_NUMBER_SEMI_FINAL_SECOND_LEG) {
                Set<KnockoutPairing> semiFinal = playoff.getSemiFinal().getPairings();
                for (KnockoutPairing knockoutPairing : semiFinal) {
                    updatePlayoffGame(results, knockoutPairing.getSecondLeg(), MATCHDAY_NUMBER_SEMI_FINAL_SECOND_LEG);
                }
                finishSemiFinal();
            }
            if (result.getMatchday() == MATCHDAY_NUMBER_PLAYOFF_FINAL_FIRST_LEG) {
                updatePlayoffGame(results, playoff.getPlayoffFinal().getFirstLeg(),
                        MATCHDAY_NUMBER_PLAYOFF_FINAL_FIRST_LEG);
            }
            if (result.getMatchday() == MATCHDAY_NUMBER_PLAYOFF_FINAL_SECOND_LEG) {
                updatePlayoffGame(results, playoff.getPlayoffFinal().getSecondLeg(),
                        MATCHDAY_NUMBER_PLAYOFF_FINAL_SECOND_LEG);
            }
            if (result.getMatchday() == MATCHDAY_NUMBER_PLAYOFF_FINAL_THIRD_LEG) {
                updatePlayoffGame(results, playoff.getPlayoffFinal().getThirdLeg(),
                        MATCHDAY_NUMBER_PLAYOFF_FINAL_THIRD_LEG);
            }

        }
    }

    private void finishQuaterFinal() {
        Set<KnockoutPairing> pairings = sessionData.getComunio().getPlayoff().getQuaterFinal().getPairings();
        List<Team> promotedTeams = findPromotedTeams(pairings);
        initializeSemiFinal(promotedTeams);
    }

    private void finishSemiFinal() {
        Set<KnockoutPairing> pairings = sessionData.getComunio().getPlayoff().getSemiFinal().getPairings();
        List<Team> promotedTeams = findPromotedTeams(pairings);
        initializePlayoffFinal(promotedTeams);
    }

    private List<Team> findPromotedTeams(Set<KnockoutPairing> pairings) {
        List<Team> promotedTeams = new ArrayList<>();
        for (KnockoutPairing pairing : pairings) {
            Team pairingWinner = findPairingWinner(pairing);
            pairing.setPromotedTeam(pairingWinner);
            knockoutPairingService.update(pairing);
            promotedTeams.add(pairingWinner);
        }
        return promotedTeams;
    }

    private void initializeSemiFinal(List<Team> promotedTeams) {
        Collections.shuffle(promotedTeams);
        Map<Integer, Team> semiFinalTeams = new HashMap<Integer, Team>();
        for (int i = 0; i < promotedTeams.size(); i++) {
            semiFinalTeams.put(i + 1, promotedTeams.get(i));
        }
        playoffService.initializeSemifinal(semiFinalTeams);
    }

    private void initializePlayoffFinal(List<Team> promotedTeams) {
        playoffService.initializeFinal(promotedTeams.get(0), promotedTeams.get(1));
    }

    private Team findPairingWinner(KnockoutPairing pairing) {
        PlayoffGame firstLeg = pairing.getFirstLeg();

        PlayoffGame secondLeg = pairing.getSecondLeg();
        // TODO
        return firstLeg.getHomeTeam();
    }

    private void initializePlayoff() {
        Set<Groupe> groups = sessionData.getComunio().getGroups();
        int numberOfTeams = teamService.findAllTeamNames().size();
        Map<Integer, Team> playoffTeams = playoffRankingService.determinePlayoffTeam(groups, numberOfTeams);
        playoffService.initializePlayoffs(playoffTeams);
    }

    private void updatePlayoffGame(List<Result> results, PlayoffGame playoffGame, int matchdayNumber) {
        Team homeTeam = playoffGame.getHomeTeam();
        Team awayTeam = playoffGame.getAwayTeam();
        playoffGame.setHomeGoals(resultGoalFinder.findGoalsFromResult(homeTeam, matchdayNumber, results));
        playoffGame.setAwayGoals(resultGoalFinder.findGoalsFromResult(awayTeam, matchdayNumber, results));
        playoffGameService.update(playoffGame);
    }
}
