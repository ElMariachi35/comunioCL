package com.comunio.service.impl;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.PlayoffDao;
import com.comunio.exception.NoPlayoffFoundException;
import com.comunio.model.Playoff;
import com.comunio.model.SessionData;
import com.comunio.model.Team;
import com.comunio.service.PlayoffFinalService;
import com.comunio.service.PlayoffFixtureService;
import com.comunio.service.PlayoffService;

@Service
public class PlayoffServiceImpl implements PlayoffService, Serializable {
    private static final long serialVersionUID = -4586483467938948380L;

    @Autowired
    SessionData sessionData;
    @Autowired
    PlayoffFixtureService playoffFixtureService;
    @Autowired
    PlayoffDao playoffDao;
    @Autowired
    PlayoffFinalService playoffFinalService;

    @Override
    @Transactional
    public void initializePlayoffs(Map<Integer, Team> teams) {
	Playoff playoff = new Playoff();
	playoff.setComunio(sessionData.getComunio());
	if (teams.size() == 8) {
	    playoff.setQuaterFinal(playoffFixtureService.createFixture(teams));
	}
	if (teams.size() == 4) {
	    playoff.setSemiFinal(playoffFixtureService.createFixture(teams));
	}
	playoffDao.save(playoff);
	sessionData.getComunio().setPlayoff(playoff);
    }

    @Transactional
    public void initializeSemifinal(Map<Integer, Team> teams) {
	Playoff playoff = sessionData.getComunio().getPlayoff();
	if (playoff == null) {
	    throw new NoPlayoffFoundException(
		    "Could not find playoff for comunio: "
			    + sessionData.getComunioId());
	}
	playoff.setSemiFinal(playoffFixtureService.createFixture(teams));
	playoffDao.update(playoff);
    }
    
    @Transactional
    public void initializeFinal(Team team1, Team team2){
	Playoff playoff = sessionData.getComunio().getPlayoff();
	if (playoff == null) {
	    throw new NoPlayoffFoundException(
		    "Could not find playoff for comunio: "
			    + sessionData.getComunioId());
	}
	playoff.setPlayoffFinal(playoffFinalService.createFinal(team1, team2));
	playoffDao.update(playoff);
    }
}
