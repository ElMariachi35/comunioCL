package com.comunio.service;

import java.util.List;

import com.comunio.model.Matchday;

public interface MatchdayService {
	public void saveMatchdays(List<Matchday> matchdays);
	public void saveMatchday(Matchday matchday);
}
