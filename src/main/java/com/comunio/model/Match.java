package com.comunio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Match implements Serializable {

	private static final long serialVersionUID = -2480404203482944140L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long matchId;
	@ManyToOne
	@JoinColumn(name="matchdayId")
	private Matchday matchday;
	@Column
	private Team homeTeam;
	@Column
	private Team awayTeam;
	@Column
	private int goalsHome;
	@Column
	private int goalsAway;
	
	public Match() {
	}

	public long getMatchId() {
		return matchId;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}

	public Matchday getMatchday() {
		return matchday;
	}

	public void setMatchday(Matchday matchday) {
		this.matchday = matchday;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getGoalsHome() {
		return goalsHome;
	}

	public void setGoalsHome(int goalsHome) {
		this.goalsHome = goalsHome;
	}

	public int getGoalsAway() {
		return goalsAway;
	}

	public void setGoalsAway(int goalsAway) {
		this.goalsAway = goalsAway;
	}
	
}
