package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunio.dao.MatchdayDao;
import com.comunio.model.Fixture;
import com.comunio.model.Game;
import com.comunio.model.Groupe;
import com.comunio.model.Matchday;
import com.comunio.service.FixtureService;
import com.comunio.service.GameService;
import com.comunio.service.MatchdayService;

@Service
public class MatchdayServiceImpl implements MatchdayService{

	@Autowired
	private MatchdayDao matchdayDao;
	@Autowired
	private GameService gameService;
	@Autowired
	private FixtureService fixtureService;
	
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
	
	public List<Matchday> getSortedMatchdays(Groupe group){
		Fixture fixture = fixtureService.getFixture(group);
		List<Matchday> matchdays = new ArrayList<>(fixture.getMatchdays());
		Collections.sort(matchdays);
		return matchdays;
	}

	private void persistGamesInMatchday(Matchday matchday) {
		for (Game game : matchday.getMatches()) {
			gameService.saveGame(game);
		}
	}
}
