package com.comunio.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.exception.PlayoffCannotBeInitializedException;
import com.comunio.model.Groupe;
import com.comunio.model.Playoff;
import com.comunio.model.PlayoffFinale;
import com.comunio.model.SessionData;
import com.comunio.model.Team;
import com.comunio.service.ComunioService;
import com.comunio.service.GroupService;
import com.comunio.service.PlayoffFinalService;
import com.comunio.service.PlayoffFixtureService;
import com.comunio.service.PlayoffGameService;
import com.comunio.service.PlayoffInitializationService;
import com.comunio.service.PlayoffService;
import com.comunio.service.ResultService;
import com.comunio.service.TeamService;

@Service
public class PlayoffInitializationServiceImpl implements PlayoffInitializationService {

    @Autowired
    SessionData sessionData;
    @Autowired
    TeamService teamService;
    @Autowired
    PlayoffRankingService playoffRankingService;
    @Autowired
    PlayoffFixtureService playoffFixtureService;
    @Autowired
    PlayoffFinalService playoffFinalService;
    @Autowired
    PlayoffGameService playoffGameService;
    @Autowired
    PlayoffService playoffService;
    @Autowired
    ComunioService comunioService;
    @Autowired
    GroupService groupService;
    @Autowired
    ResultService resultService;

    @Override
    @Transactional
    public Playoff initializePlayoff(Playoff playoff, long comunioId) {
        if (isAreadyInitialized(playoff)) {
            return playoff;
        }
        List<Groupe> groups = groupService.findGroupsByComunioId(comunioId);
        int numberOfTeams = determineNumberOfTeams(groups);
        Map<Integer, Team> playoffTeams = playoffRankingService.determinePlayoffTeam(groups, numberOfTeams);
        return createPlayoffs(playoffTeams, playoff);
    }

    private boolean isAreadyInitialized(Playoff playoff) {
        return playoff.getQuaterFinal() != null || playoff.getSemiFinal() != null;
    }

    private int determineNumberOfTeams(List<Groupe> groups) {
        int numberOfTeams = 0;
        for (Groupe groupe : groups) {
            numberOfTeams += groupe.getTeams().size();
        }
        return numberOfTeams;
    }

    private Playoff createPlayoffs(Map<Integer, Team> teams, Playoff playoff) {
        if (teams.size() == 8) {
            playoff.setQuaterFinal(playoffFixtureService.createFixture(teams));
        }
        if (teams.size() == 4) {
            playoff.setSemiFinal(playoffFixtureService.createFixture(teams));
        }
        return playoff;
    }

    @Override
    public Playoff initializeSemiFinal(Map<Integer, Team> semiFinalTeams, Playoff playoff) {
        playoff.setSemiFinal(playoffFixtureService.createFixture(semiFinalTeams));
        return playoff;
    }

    @Override
    public Playoff initializeFinal(Map<Integer, Team> promotedTeams, Playoff playoff) {
        if (playoff == null) {
            return null;
        }
        PlayoffFinale playoffFinal = playoff.getPlayoffFinal();
        if (playoffFinal == null) {
            playoffFinal = createFinal(promotedTeams.get(1), promotedTeams.get(2));
        }
        if (playoffFinal.getTeamOne().getTeamId() != promotedTeams.get(1).getTeamId()
                || playoffFinal.getTeamTwo().getTeamId() != promotedTeams.get(2).getTeamId()) {
            playoffFinal = createFinal(promotedTeams.get(1), promotedTeams.get(2));
        }
        playoff.setPlayoffFinal(playoffFinal);
        return playoff;
    }

    @Override
    public PlayoffFinale createFinal(Team team1, Team team2) {
        PlayoffFinale playoffFinale = new PlayoffFinale();
        playoffFinale.setTeamOne(team1);
        playoffFinale.setTeamTwo(team2);
        playoffFinale.setFirstLeg(playoffGameService.createPlayoffGame(team1, team2));
        playoffFinale.setSecondLeg(playoffGameService.createPlayoffGame(team2, team1));
        playoffFinale.setThirdLeg(playoffGameService.createPlayoffGame(team1, team2));
        return playoffFinale;
    }
    

    @Override
    @Transactional
    public boolean playoffsCanBeInitialized(int matchdayNumber, long comunioId) {
	if (matchdayNumber != PlayoffResultServiceImpl.MATCHDAY_NUMBER_LAST_GROUP_GAME) {
	    return false;
	}
	long numberOfResults = resultService.findNumberOfResults(comunioId);
	long expectedNumberOfResults = determineExpectedNumberOfResults(comunioId);
	if (numberOfResults != expectedNumberOfResults) {
	    throw new PlayoffCannotBeInitializedException();
	}
	return true;
    }

    private long determineExpectedNumberOfResults(long comunioId) {
	return teamService
		.findNumberOfTeams(comunioId)
		* PlayoffResultServiceImpl.MATCHDAY_NUMBER_LAST_GROUP_GAME;
    }
}
