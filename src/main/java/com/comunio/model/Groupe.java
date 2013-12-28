package com.comunio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Groupe {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long groupId;
	@Column
	private String groupName;
    @ManyToOne
    @JoinColumn(name="comunioId")
	private Comunio comunio;

	public Groupe() {
	}

	public Groupe(long groupId, Comunio comunio, String groupName) {
		super();
		this.setGroupId(groupId);
		this.setComunio(comunio);
		this.setGroupName(groupName);
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public Comunio getComunio() {
		return comunio;
	}

	public void setComunio(Comunio comunio) {
		this.comunio = comunio;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
