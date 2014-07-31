package com.comunio.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonResult implements Serializable {
    private long teamId;
    private int matchdayNumber;
    private int points;
    
    public JsonResult() {
    }
    
    public int getMatchdayNumber() {
	return matchdayNumber;
    }
    public void setMatchdayNumber(int matchdayNumber) {
	this.matchdayNumber = matchdayNumber;
    }
    public int getPoints() {
	return points;
    }
    public void setPoints(int points) {
	this.points = points;
    }

    public long getTeamId() {
	return teamId;
    }

    public void setTeamId(long teamId) {
	this.teamId = teamId;
    }
    
}
