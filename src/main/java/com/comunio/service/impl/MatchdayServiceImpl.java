package com.comunio.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunio.dao.GameDao;
import com.comunio.dao.MatchdayDao;
import com.comunio.model.Game;
import com.comunio.model.Matchday;
import com.comunio.service.GameService;
import com.comunio.service.MatchdayService;

@Service
public class MatchdayServiceImpl implements MatchdayService{

	@Autowired
	private MatchdayDao matchdayDao;
	@Autowired
	private GameService gameService;

	@Override
	public void saveMatchdays(Set<Matchday> matchdays) {
		for (Matchday matchday : matchdays) {
			saveMatchday(matchday);
		}
	}

	@Override
	public void saveMatchday(Matchday matchday) {
		matchdayDao.saveMatchday(matchday);		
		persistGamesInMatchday(matchday);
	}

	private void persistGamesInMatchday(Matchday matchday) {
		for (Game game : matchday.getMatches()) {
			gameService.saveGame(game);
		}
	}

}
