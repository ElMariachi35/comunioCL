package com.comunio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
public class Groupe implements Serializable, Comparable<Groupe> {

    private static final long serialVersionUID = -1788815127657833676L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long groupId;
    @Column
    private String groupName;
    @ManyToOne
    @JoinColumn(name = "comunioId")
    private Comunio comunio;
    @OneToMany(mappedBy = "groupe", fetch = FetchType.EAGER)
    private Set<Team> teams;
    @OneToOne(mappedBy = "groupe", fetch = FetchType.EAGER)
    private Fixture fixture;

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

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Override
    public int compareTo(Groupe group) {
        return getGroupName().compareTo(group.getGroupName());
    }

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }

    public List<Team> getSortedTeams() {
        List<Team> teams = new ArrayList<>(this.teams);
        Collections.sort(teams);
        return teams;
    }
}
