package com.comunio.service.impl;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.model.Comunio;
import com.comunio.model.Groupe;
import com.comunio.model.Playoff;
import com.comunio.model.PlayoffFinale;
import com.comunio.model.SessionData;
import com.comunio.model.Team;
import com.comunio.service.ComunioService;
import com.comunio.service.PlayoffFinalService;
import com.comunio.service.PlayoffFixtureService;
import com.comunio.service.PlayoffGameService;
import com.comunio.service.PlayoffInitializationService;
import com.comunio.service.PlayoffService;
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

    @Override
    @Transactional
    public void initializePlayoff() {
        Set<Groupe> groups = sessionData.getComunio().getGroups();
        int numberOfTeams = teamService.findAllTeamNames().size();
        Map<Integer, Team> playoffTeams = playoffRankingService.determinePlayoffTeam(groups, numberOfTeams);
        createPlayoffs(playoffTeams);
    }

    private void createPlayoffs(Map<Integer, Team> teams) {
        Playoff playoff = sessionData.getComunio().getPlayoff();
        if (teams.size() == 8) {
            playoff.setQuaterFinal(playoffFixtureService.createFixture(teams));
        }
        if (teams.size() == 4) {
            playoff.setSemiFinal(playoffFixtureService.createFixture(teams));
        }
        playoffService.save(playoff);
        Comunio comunio = sessionData.getComunio();
        comunio.setPlayoff(playoff);
        comunioService.save(comunio);
    }

    @Override
    public void initializeSemiFinal(Map<Integer, Team> semiFinalTeams) {
        Playoff playoff = sessionData.getComunio().getPlayoff();
        playoff.setSemiFinal(playoffFixtureService.createFixture(semiFinalTeams));
    }

    @Override
    public void initializeFinal(Map<Integer, Team> promotedTeams) {
        Playoff playoff = sessionData.getComunio().getPlayoff();
        if (playoff == null) {
            return;
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
}
