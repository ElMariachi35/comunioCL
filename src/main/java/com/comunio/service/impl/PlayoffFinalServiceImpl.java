package com.comunio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.PlayoffFinalDao;
import com.comunio.model.PlayoffFinale;
import com.comunio.model.Team;
import com.comunio.service.PlayoffFinalService;
import com.comunio.service.PlayoffGameService;

@Service
public class PlayoffFinalServiceImpl implements PlayoffFinalService {

    @Autowired
    PlayoffGameService playoffGameService;
    @Autowired
    PlayoffFinalDao playoffFinalDao;
    
    @Override
    @Transactional
    public PlayoffFinale createFinal(Team team1, Team team2) {
	PlayoffFinale playoffFinale=new PlayoffFinale();
	playoffFinale.setTeamOne(team1);
	playoffFinale.setTeamTwo(team2);
	playoffFinale.setFirstLeg(playoffGameService.createPlayoffGame(team1, team2));
	playoffFinale.setSecondLeg(playoffGameService.createPlayoffGame(team2, team1));
	playoffFinale.setThirdLeg(playoffGameService.createPlayoffGame(team1, team2));
	playoffFinalDao.save(playoffFinale);
	return playoffFinale;
    }

}
