package com.comunio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Game implements Serializable {

    private static final long serialVersionUID = 2127456767893314926L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long gameId;
    @ManyToOne
    @JoinColumn(name = "matchdayId")
    private Matchday matchday;
    @OneToOne
    @JoinColumn(name = "homeTeam", referencedColumnName = "teamId")
    private Team homeTeam;
    @OneToOne
    @JoinColumn(name = "awayTeam", referencedColumnName = "teamId")
    private Team awayTeam;
    @Column
    private int homeGoals;
    @Column
    private int awayGoals;

    public Game() {
    }

    public Matchday getMatchday() {
        return matchday;
    }

    public void setMatchday(Matchday matchday) {
        this.matchday = matchday;
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

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }
}
