package com.comunio.service;

import java.util.Set;

import com.comunio.model.Groupe;
import com.comunio.model.Matchday;
import com.comunio.model.Schedule;

public interface MatchdayService {
	public void saveMatchdays(Set<Matchday> matchdays);
	public void saveMatchday(Matchday matchday);
}
