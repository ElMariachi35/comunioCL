package com.comunio.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PlayoffFinale implements Serializable {
    private static final long serialVersionUID = -2037855491866687846L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long playoffFinalId;
    @OneToOne
    @JoinColumn(name = "teamOne", referencedColumnName = "teamId")
    private Team teamOne;
    @OneToOne
    @JoinColumn(name = "teamTwo", referencedColumnName = "teamId")
    private Team teamTwo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "firstLeg", referencedColumnName = "playoffGameId")
    private PlayoffGame firstLeg;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "secondLeg", referencedColumnName = "playoffGameId")
    private PlayoffGame secondLeg;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "thirdLeg", referencedColumnName = "playoffGameId")
    private PlayoffGame thirdLeg;

    public PlayoffFinale() {
    }

    public long getPlayoffFinalId() {
        return playoffFinalId;
    }

    public void setPlayoffFinalId(long playoffFinalId) {
        this.playoffFinalId = playoffFinalId;
    }

    public Team getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
    }

    public PlayoffGame getFirstLeg() {
        return firstLeg;
    }

    public void setFirstLeg(PlayoffGame firstLeg) {
        this.firstLeg = firstLeg;
    }

    public PlayoffGame getSecondLeg() {
        return secondLeg;
    }

    public void setSecondLeg(PlayoffGame secondLeg) {
        this.secondLeg = secondLeg;
    }

    public PlayoffGame getThirdLeg() {
        return thirdLeg;
    }

    public void setThirdLeg(PlayoffGame thirdLeg) {
        this.thirdLeg = thirdLeg;
    }
}
