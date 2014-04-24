package com.comunio.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.comunio.model.KnockoutPairing;
import com.comunio.model.PlayoffGame;
import com.comunio.model.Team;
import com.comunio.service.KnockoutPairingService;

@Service
public class KnockoutPairingServiceImpl implements KnockoutPairingService, Serializable {
    private static final long serialVersionUID = -3022115382308667780L;

    @Override
    public KnockoutPairing createPairing(Team team1, Team team2) {
        KnockoutPairing pairing = new KnockoutPairing();
        PlayoffGame firstLeg = createPlayoffGame(team1, team2);
        PlayoffGame secondLeg = createPlayoffGame(team2, team1);
        pairing.setFirstLeg(firstLeg);
        pairing.setSecondLeg(secondLeg);
        return pairing;
    }

    private PlayoffGame createPlayoffGame(Team homeTeam, Team awayTeam) {
        PlayoffGame game = new PlayoffGame();
        game.setHomeTeam(homeTeam);
        game.setAwayTeam(awayTeam);
        return game;
    }
}
