package com.comunio.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Comunio {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long comunioId;
	@Column
	private String name;
	@Column
	private String password;
	@OneToMany(mappedBy="comunio", fetch = FetchType.EAGER)
	private List<Groupe> groups;
	
	public Comunio() {
	}

	public Comunio(long comunioId, String name, String password) {
		super();
		this.setComunioId(comunioId);
		this.setName(name);
		this.setPassword(password);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getComunioId() {
		return comunioId;
	}

	public void setComunioId(long comunioId) {
		this.comunioId = comunioId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Groupe> getGroups() {
		return groups;
	}

	public void setGroups(List<Groupe> groups) {
		this.groups = groups;
	}
}
