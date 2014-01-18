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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Schedule implements Serializable{
	
	private static final long serialVersionUID = -6153784244872375195L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long scheduleId;
	@OneToOne
	@JoinColumn(name="groupId")
	private Groupe groupe;
	@OneToMany(mappedBy="schedule", fetch = FetchType.EAGER)
	private Set<Matchday> matchdays;
	
	public long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public Set<Matchday> getMatchdays() {
		return matchdays;
	}

	public void setMatchdays(Set<Matchday> matchdays) {
		this.matchdays = matchdays;
	}
}
