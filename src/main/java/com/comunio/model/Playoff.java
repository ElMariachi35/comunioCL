package com.comunio.model;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;

@Entity
public class Playoff implements Serializable {
    private static final long serialVersionUID = 8354340646218407497L;

    private long playoffId;
    private Map<Integer, Team> teams;
    private PlayoffFixture quaterfinal;
    private PlayoffFixture semifinal;
    private PlayoffFinal finale;

    public Playoff() {
    }

    public long getPlayoffId() {
        return playoffId;
    }

    public void setPlayoffId(long playoffId) {
        this.playoffId = playoffId;
    }

    public Map<Integer, Team> getTeams() {
        return teams;
    }

    public void setTeams(Map<Integer, Team> teams) {
        this.teams = teams;
    }

    public PlayoffFixture getQuaterfinal() {
        return quaterfinal;
    }

    public void setQuaterfinal(PlayoffFixture quaterfinal) {
        this.quaterfinal = quaterfinal;
    }

    public PlayoffFixture getSemifinal() {
        return semifinal;
    }

    public void setSemifinal(PlayoffFixture semifinal) {
        this.semifinal = semifinal;
    }

    public PlayoffFinal getFinale() {
        return finale;
    }

    public void setFinale(PlayoffFinal finale) {
        this.finale = finale;
    }
}
