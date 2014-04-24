package com.comunio.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

public class PlayoffFinal implements Serializable {
    private static final long serialVersionUID = -2037855491866687846L;

    private long playoffFinalId;
    @JsonIgnore
    private Playoff playoff;
    private Team teamOne;
    private Team teamTwo;

    private PlayoffGame firstLeg;
    private PlayoffGame secondLeg;
    private PlayoffGame thirdLeg;

    public PlayoffFinal() {
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
