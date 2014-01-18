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
public class Schedule implements Serializable{
	
	private static final long serialVersionUID = -6153784244872375195L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long scheduleId;
	@OneToOne
	@JoinColumn(name="groupId")
	private Groupe groupe;

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
}
