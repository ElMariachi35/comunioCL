package com.comunio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comunio {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	// for autonumber
	private int comId;
	@Column
	private String name;

	public Comunio() {
	}

	public Comunio(int comId, String name) {
		super();
		this.setComId(comId);
		this.setName(name);
	}

	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
