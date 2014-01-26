package com.comunio.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Matchday implements Serializable, Comparable<Matchday> {

	private static final long serialVersionUID = 1421106637466976597L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long matchdayId;
	@ManyToOne
	@JoinColumn(name = "scheduleId")
	private Schedule schedule;
	@Column
	private int comunioMatchdayNumber;
	@Column
	private int leagueMatchdayNumber;
	@Column
	@OneToMany(mappedBy = "matchday", fetch = FetchType.EAGER)
	private Set<Game> matches;
	@OneToOne
	@JoinColumn(name = "byeTeam", referencedColumnName = "teamId")
	private Team byeTeam;

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

	public Set<Game> getMatches() {
		return matches;
	}

	public void setMatches(Set<Game> matches) {
		this.matches = matches;
	}

	public Team getByeTeam() {
		return byeTeam;
	}

	public void setByeTeam(Team byeTeam) {
		this.byeTeam = byeTeam;
	}

	@Override
	public int compareTo(Matchday matchday) {
		if (this.comunioMatchdayNumber > matchday.comunioMatchdayNumber) {
			return 1;
		} else if (this.comunioMatchdayNumber < matchday.comunioMatchdayNumber) {
			return -1;
		}
		return 0;
	}
}
