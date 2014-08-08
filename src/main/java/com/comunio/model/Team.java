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

import com.google.common.collect.ComparisonChain;

@Entity
public class Team implements Serializable, Comparable<Team> {
    private static final long serialVersionUID = 4266516923336677208L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long teamId;
    @Column
    private String teamName;
    @ManyToOne(fetch = FetchType.LAZY)
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
    @JsonIgnore
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
        return ComparisonChain.start().compare(otherTeam.getPoints(), getPoints())
                .compare(otherTeam.getGoalDifference(), getGoalDifference())
                .compare(otherTeam.getGoalsFor(), getGoalsFor())
                .compare(otherTeam.getGoalsAgainst(), getGoalsAgainst())
                .compare(otherTeam.getGamesWon(), getGamesWon())
                .compare(otherTeam.getTeamName().hashCode(), getTeamName().hashCode()).result();
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Team)) {
            return false;
        }
        Team team = (Team) obj;
        if (team.getTeamId() == teamId) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        Long id = teamId;
        return id.hashCode();
    }
}
