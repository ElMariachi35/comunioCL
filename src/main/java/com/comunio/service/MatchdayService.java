package com.comunio.service;

import java.util.Set;

import com.comunio.model.Groupe;
import com.comunio.model.Matchday;
import com.comunio.model.Schedule;

public interface MatchdayService {
	public Set<Matchday> createMatchdays(Groupe group, Schedule schedule);
}
