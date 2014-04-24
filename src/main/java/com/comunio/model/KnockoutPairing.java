package com.comunio.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

public class KnockoutPairing implements Serializable {
    private static final long serialVersionUID = 4392931109345422072L;

    private long knockoutPairingId;
    @JsonIgnore
    private PlayoffFixture playoffFixture;
    private PlayoffGame firstLeg;
    private PlayoffGame secondLeg;
    private Team promotedTeam;

    public KnockoutPairing() {
    }

    public Team getPromotedTeam() {
        return promotedTeam;
    }

    public void setPromotedTeam(Team promotedTeam) {
        this.promotedTeam = promotedTeam;
    }

    public long getKnockoutPairingId() {
        return knockoutPairingId;
    }

    public void setKnockoutPairingId(long knockoutPairingId) {
        this.knockoutPairingId = knockoutPairingId;
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
}
