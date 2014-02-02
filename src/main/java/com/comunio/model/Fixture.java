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
public class Fixture implements Serializable{
	
	private static final long serialVersionUID = -6153784244872375195L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long fixtureId;
	@OneToOne
	@JoinColumn(name="groupId")
	private Groupe groupe;
	@OneToMany(mappedBy="fixture", fetch = FetchType.EAGER)
	private Set<Matchday> matchdays;

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public long getFixtureId() {
		return fixtureId;
	}

	public void setFixtureId(long fixtureId) {
		this.fixtureId = fixtureId;
	}

	public Set<Matchday> getMatchdays() {
		return matchdays;
	}

	public void setMatchdays(Set<Matchday> matchdays) {
		this.matchdays = matchdays;
	}
}
