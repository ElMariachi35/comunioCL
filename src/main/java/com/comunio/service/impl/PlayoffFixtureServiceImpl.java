package com.comunio.service.impl;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunio.model.KnockoutPairing;
import com.comunio.model.PlayoffFixture;
import com.comunio.model.Team;
import com.comunio.service.KnockoutPairingService;
import com.comunio.service.PlayoffFixtureService;

@Service
public class PlayoffFixtureServiceImpl implements PlayoffFixtureService, Serializable {
    private static final long serialVersionUID = 5126369397389980389L;

    @Autowired
    KnockoutPairingService knockoutPairingService;

    @Override
    public PlayoffFixture createFixture(Map<Integer, Team> teams) {
        PlayoffFixture fixture = new PlayoffFixture();
        for (int i = 1; i <= teams.size() / 2; i++) {
            Team team1 = teams.get(i);
            Team team2 = teams.get(teams.size() - (1 + i));
            KnockoutPairing pairing = knockoutPairingService.createPairing(team1, team2);
            fixture.addPairing(pairing);
        }
        return fixture;
    }

    @Override
    public void saveFixture(PlayoffFixture fixture) {
    }

}
