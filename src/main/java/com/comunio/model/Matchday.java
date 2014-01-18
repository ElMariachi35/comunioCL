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
public class Matchday implements Serializable {

	private static final long serialVersionUID = 1421106637466976597L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long matchdayId;
	@ManyToOne
	@JoinColumn(name="scheduleId")
	private Schedule schedule;
	@Column
	private int comunioMatchdayNumber;
	@Column
	private int leagueMatchdayNumber;
	
	public Matchday() {
	}

	public long getMatchdayId() {
		return matchdayId;
	}

	public void setMatchdayId(long matchdayId) {
		this.matchdayId = matchdayId;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public int getComunioMatchdayNumber() {
		return comunioMatchdayNumber;
	}

	public void setComunioMatchdayNumber(int comunioMatchdayNumber) {
		this.comunioMatchdayNumber = comunioMatchdayNumber;
	}

	public int getLeagueMatchdayNumber() {
		return leagueMatchdayNumber;
	}

	public void setLeagueMatchdayNumber(int leagueMatchdayNumber) {
		this.leagueMatchdayNumber = leagueMatchdayNumber;
	}
	
	

}
