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
public class Playoff implements Serializable {
    private static final long serialVersionUID = 8354340646218407497L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quaterFinal", referencedColumnName = "playoffFixtureId")
    private PlayoffFixture quaterFinal;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "semiFinal", referencedColumnName = "playoffFixtureId")
    private PlayoffFixture semiFinal;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "playoffFinal", referencedColumnName = "playoffFinalId")
    private PlayoffFinale playoffFinal;

    public Playoff() {
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

    public PlayoffFinale getPlayoffFinal() {
        return playoffFinal;
    }

    public void setPlayoffFinal(PlayoffFinale playoffFinal) {
        this.playoffFinal = playoffFinal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
