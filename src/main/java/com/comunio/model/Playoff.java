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
public class Playoff implements Serializable {
    private static final long serialVersionUID = 8354340646218407497L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long playoffId;
    @OneToOne
    @JoinColumn(name = "quaterFinal", referencedColumnName = "playoffFixtureId")
    private PlayoffFixture quaterFinal;
    @OneToOne
    @JoinColumn(name = "semiFinal", referencedColumnName = "playoffFixtureId")
    private PlayoffFixture semiFinal;
    @OneToOne
    @JoinColumn(name = "playoffFinal", referencedColumnName = "playoffFinalId")
    private PlayoffFinal playoffFinal;

    public Playoff() {
    }

    public long getPlayoffId() {
	return playoffId;
    }

    public void setPlayoffId(long playoffId) {
	this.playoffId = playoffId;
    }

    public PlayoffFixture getQuaterFinal() {
	return quaterFinal;
    }

    public void setQuaterFinal(PlayoffFixture quaterFinal) {
	this.quaterFinal = quaterFinal;
    }

    public PlayoffFixture getSemiFinal() {
	return semiFinal;
    }

    public void setSemiFinal(PlayoffFixture semiFinal) {
	this.semiFinal = semiFinal;
    }

    public PlayoffFinal getPlayoffFinal() {
	return playoffFinal;
    }

    public void setPlayoffFinal(PlayoffFinal playoffFinal) {
	this.playoffFinal = playoffFinal;
    }
}
