package com.comunio.service.impl;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.comunio.model.Playoff;
import com.comunio.model.SessionData;
import com.comunio.model.Team;
import com.comunio.service.PlayoffFixtureService;
import com.comunio.service.PlayoffService;

public class PlayoffServiceImpl implements PlayoffService, Serializable {
    private static final long serialVersionUID = -4586483467938948380L;

    @Autowired
    SessionData sessionData;
    @Autowired
    PlayoffFixtureService playoffFixtureService;

    @Override
    public void initializePlayoffs(Map<Integer, Team> teams) {
        Playoff playoff = new Playoff();
        if (teams.size() == 8) {
            playoff.setQuaterFinal(playoffFixtureService.createFixture(teams));
        }
        if (teams.size() == 4) {
            playoff.setSemiFinal(playoffFixtureService.createFixture(teams));
        }
        sessionData.getComunio().setPlayoff(playoff);
    }
}
