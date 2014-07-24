package com.comunio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PlayoffGame implements Serializable {
    private static final long serialVersionUID = 7228420617446723308L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long playoffGameId;
    @OneToOne
    @JoinColumn(name = "homeTeam", referencedColumnName = "teamId")
    private Team homeTeam;
    @OneToOne
    @JoinColumn(name = "awayTeam", referencedColumnName = "teamId")
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

    public long getPlayoffGameId() {
        return playoffGameId;
    }

    public void setPlayoffGameId(long playoffGameId) {
        this.playoffGameId = playoffGameId;
    }

}
