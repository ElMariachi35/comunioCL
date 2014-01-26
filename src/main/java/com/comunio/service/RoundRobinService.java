package com.comunio.service;

import java.util.List;

import com.comunio.model.Schedule;
import com.comunio.model.Team;

public interface RoundRobinService {
	public Schedule roundRobinCreateSchedule(List<Team> teams);
}
