package com.comunio.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

public class PlayoffGame implements Serializable {
    private static final long serialVersionUID = 7228420617446723308L;

    private long playoffGameId;
    @JsonIgnore
    private KnockoutPairing pairing;
    private Team homeTeam;
    private Team awayTeam;
    private int homeGoals;
    private int awayGoals;

    public PlayoffGame() {
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public KnockoutPairing getPairing() {
        return pairing;
    }

    public void setPairing(KnockoutPairing pairing) {
        this.pairing = pairing;
    }

    public long getPlayoffGameId() {
        return playoffGameId;
    }

    public void setPlayoffGameId(long playoffGameId) {
        this.playoffGameId = playoffGameId;
    }

}
