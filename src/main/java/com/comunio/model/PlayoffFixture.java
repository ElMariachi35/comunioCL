package com.comunio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

@Entity
public class PlayoffFixture implements Serializable {
    private static final long serialVersionUID = 5882292838997072029L;

    private long playoffFixtureId;
    private Map<Integer, Team> teams;
    private Map<Integer, Team> promotedTeams;
    private List<KnockoutPairing> pairings;

    public PlayoffFixture() {
    }

    public long getPlayoffFixtureId() {
        return playoffFixtureId;
    }

    public void setPlayoffFixtureId(long playoffFixtureId) {
        this.playoffFixtureId = playoffFixtureId;
    }

    public Map<Integer, Team> getTeams() {
        return teams;
    }

    public void setTeams(Map<Integer, Team> teams) {
        this.teams = teams;
    }

    public Map<Integer, Team> getPromotedTeams() {
        return promotedTeams;
    }

    public void setPromotedTeams(Map<Integer, Team> promotedTeams) {
        this.promotedTeams = promotedTeams;
    }

    public List<KnockoutPairing> getPairings() {
        return pairings;
    }

    public void setPairings(List<KnockoutPairing> pairings) {
        this.pairings = pairings;
    }

    public void addPairing(KnockoutPairing pairing) {
        if (pairings == null) {
            pairings = new ArrayList<KnockoutPairing>();
        }
        pairings.add(pairing);
    }
}
