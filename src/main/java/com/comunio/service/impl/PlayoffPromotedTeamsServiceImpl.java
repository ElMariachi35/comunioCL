package com.comunio.service.impl;

import org.springframework.stereotype.Service;

import com.comunio.model.KnockoutPairing;
import com.comunio.model.PlayoffGame;
import com.comunio.model.Team;
import com.comunio.service.PlayoffPromotedTeamsService;

@Service
public class PlayoffPromotedTeamsServiceImpl implements PlayoffPromotedTeamsService {

    @Override
    public Team determinePromotedTeam(KnockoutPairing pairing) {
        PlayoffGame firstLeg = pairing.getFirstLeg();
        PlayoffGame secondLeg = pairing.getSecondLeg();
        Team promotedTeam = checkWins(firstLeg, secondLeg);
        if (promotedTeam == null) {
            promotedTeam = checkAwayGoals(firstLeg, secondLeg);
        }
        if (promotedTeam == null) {
            // promotedTeam = checkPoints(pairing);
        }
        if (promotedTeam == null) {
            promotedTeam = checkHashCode(firstLeg);
        }
        return promotedTeam;
    }

    private Team checkWins(PlayoffGame firstLeg, PlayoffGame secondLeg) {
        int teamAGoals = firstLeg.getHomeGoals() + secondLeg.getAwayGoals();
        int teamBGoals = firstLeg.getAwayGoals() + secondLeg.getHomeGoals();
        if (teamAGoals > teamBGoals) {
            return firstLeg.getHomeTeam();
        } else if (teamAGoals < teamBGoals) {
            return firstLeg.getAwayTeam();
        }
        return null;
    }

    private Team checkAwayGoals(PlayoffGame firstLeg, PlayoffGame secondLeg) {
        int awayGoalsTeamA = firstLeg.getAwayGoals();
        int awayGoalsTeamB = secondLeg.getAwayGoals();
        if (awayGoalsTeamA > awayGoalsTeamB) {
            return firstLeg.getAwayTeam();
        } else if (awayGoalsTeamA < awayGoalsTeamB) {
            return secondLeg.getAwayTeam();
        }
        return null;
    }

    private Team checkHashCode(PlayoffGame firstLeg) {
        int homeTeam = firstLeg.getHomeTeam().getTeamName().hashCode();
        int awayTeam = firstLeg.getAwayTeam().getTeamName().hashCode();
        if (homeTeam > awayTeam) {
            return firstLeg.getHomeTeam();
        }
        return firstLeg.getAwayTeam();
    }
}
