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
public class KnockoutPairing implements Serializable {
    private static final long serialVersionUID = 4392931109345422072L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long knockoutPairingId;
    @OneToOne
    @JoinColumn(name = "firstLeg", referencedColumnName = "playoffGameId")
    private PlayoffGame firstLeg;
    @OneToOne
    @JoinColumn(name = "secondLeg", referencedColumnName = "playoffGameId")
    private PlayoffGame secondLeg;
    @OneToOne
    @JoinColumn(name = "promotedTeam", referencedColumnName = "teamId")
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
