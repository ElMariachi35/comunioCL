package com.comunio.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Team implements Serializable, Comparable<Team> {
    private static final long serialVersionUID = 4266516923336677208L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long teamId;
    @Column
    private String teamName;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "groupId")
    private Groupe groupe;
    @Column
    private int gamesPlayed;
    @Column
    private int gamesWon;
    @Column
    private int gamesDrawn;
    @Column
    private int gamesLost;
    @Column
    private int goalsFor;
    @Column
    private int goalsAgainst;
    @Column
    private int goalDifference;
    @Column
    private int points;
    @Column
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private List<Result> result;

    public Team() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesDrawn() {
        return gamesDrawn;
    }

    public void setGamesDrawn(int gamesDrawn) {
        this.gamesDrawn = gamesDrawn;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    @Override
    public int compareTo(Team otherTeam) {
        return teamName.compareTo(otherTeam.teamName);
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public void resetAttributes() {
        setGamesPlayed(0);
        setGamesLost(0);
        setGamesDrawn(0);
        setGamesWon(0);
        setGoalDifference(0);
        setGoalsAgainst(0);
        setGoalsFor(0);
        setPoints(0);
    }
}
