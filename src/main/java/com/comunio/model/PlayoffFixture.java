package com.comunio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class PlayoffFixture implements Serializable {
    private static final long serialVersionUID = 5882292838997072029L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long playoffFixtureId;
    @OneToMany(mappedBy="teamId")
    private Set<Team> teams;
    @Transient
    private Map<Integer, Team> promotedTeams;
    @OneToMany(mappedBy="knockoutPairingId")
    private List<KnockoutPairing> pairings;

    public PlayoffFixture() {
    }

    public long getPlayoffFixtureId() {
	return playoffFixtureId;
    }

    public void setPlayoffFixtureId(long playoffFixtureId) {
	this.playoffFixtureId = playoffFixtureId;
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

    public Set<Team> getTeams() {
	return teams;
    }

    public void setTeams(Set<Team> teams) {
	this.teams = teams;
    }
}
