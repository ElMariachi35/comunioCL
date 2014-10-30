package com.comunio.service.impl;

import java.io.Serializable;
import java.util.List;

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
import com.comunio.service.ResultService;
import com.comunio.service.TeamService;

@Service
public class PlayoffResultServiceImpl implements PlayoffResultService, Serializable {

	public static final int MATCHDAY_NUMBER_LAST_GROUP_GAME = 10;
	public static final int MATCHDAY_NUMBER_QUATER_FINAL_FIRST_LEG = 11;
	public static final int MATCHDAY_NUMBER_QUATER_FINAL_SECOND_LEG = 12;
	public static final int MATCHDAY_NUMBER_SEMI_FINAL_FIRST_LEG = 13;
	public static final int MATCHDAY_NUMBER_SEMI_FINAL_SECOND_LEG = 14;
	public static final int MATCHDAY_NUMBER_PLAYOFF_FINAL_FIRST_LEG = 15;
	public static final int MATCHDAY_NUMBER_PLAYOFF_FINAL_SECOND_LEG = 16;
	public static final int MATCHDAY_NUMBER_PLAYOFF_FINAL_THIRD_LEG = 17;

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
	@Autowired
	TeamService teamService;
	@Autowired
	ResultService resultService;

	@Override
	public void handlePlayoff(List<Result> results, long comunioId) {
		int matchdayNumber = findMatchdayNumber(results);
		Playoff playoff = playoffService.findBy(comunioId);
		if (matchdayNumber < MATCHDAY_NUMBER_LAST_GROUP_GAME) {
			return;
		}

		if (playoffInitializationService.playoffsCanBeInitialized(matchdayNumber, comunioId)) {
			playoff = playoffInitializationService.initializePlayoff(playoff, comunioId);
		}

		switch (matchdayNumber) {
		case MATCHDAY_NUMBER_QUATER_FINAL_FIRST_LEG:
			playoff = playoffQuaterFinalService.updateFirstLeg(results, playoff);
			break;
		case MATCHDAY_NUMBER_QUATER_FINAL_SECOND_LEG:
			playoff = playoffQuaterFinalService.updateSecondLeg(results, playoff);
			break;
		case MATCHDAY_NUMBER_SEMI_FINAL_FIRST_LEG:
			playoffSemiFinalService.updateFirstLeg(results, playoff);
			break;
		case MATCHDAY_NUMBER_SEMI_FINAL_SECOND_LEG:
			playoffSemiFinalService.updateSecondLeg(results, playoff);
			break;
		case MATCHDAY_NUMBER_PLAYOFF_FINAL_FIRST_LEG:
			playoffFinalService.updateFirstLeg(results, playoff);
			break;
		case MATCHDAY_NUMBER_PLAYOFF_FINAL_SECOND_LEG:
			playoffFinalService.updateSecondLeg(results, playoff);
			break;
		case MATCHDAY_NUMBER_PLAYOFF_FINAL_THIRD_LEG:
			playoffFinalService.updateThirdLeg(results, playoff);
			break;
		}
		playoffService.save(playoff);
	}

	private int findMatchdayNumber(List<Result> results) {
		return results.get(0).getMatchday();
	}
}
