package com.comunio.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.KnockoutPairingDao;
import com.comunio.model.KnockoutPairing;
import com.comunio.model.PlayoffGame;
import com.comunio.model.Team;
import com.comunio.service.KnockoutPairingService;
import com.comunio.service.PlayoffGameService;

@Service
public class KnockoutPairingServiceImpl implements KnockoutPairingService,
	Serializable {
    private static final long serialVersionUID = -3022115382308667780L;

    @Autowired
    KnockoutPairingDao knockoutPairingDao;
    @Autowired
    PlayoffGameService playoffGameService;

    @Override
    @Transactional
    public KnockoutPairing createPairing(Team team1, Team team2) {
	KnockoutPairing pairing = new KnockoutPairing();
	PlayoffGame firstLeg = playoffGameService.createPlayoffGame(team1,
		team2);
	PlayoffGame secondLeg = playoffGameService.createPlayoffGame(team2,
		team1);
	pairing.setFirstLeg(firstLeg);
	pairing.setSecondLeg(secondLeg);
	knockoutPairingDao.save(pairing);
	return pairing;
    }
}
