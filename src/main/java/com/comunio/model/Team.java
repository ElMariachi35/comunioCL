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
public class Team implements Serializable {
	private static final long serialVersionUID = 4266516923336677208L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long teamId;
	@Column
	private String teamName;
	@ManyToOne
    @JoinColumn(name="groupId")
	private Groupe groupe;
	
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
}
