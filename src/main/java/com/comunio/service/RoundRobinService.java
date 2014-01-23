package com.comunio.service;

import java.util.List;
import java.util.Set;

import com.comunio.model.Comunio;
import com.comunio.model.Groupe;
import com.comunio.model.Schedule;
import com.comunio.model.Team;

public interface RoundRobinService {
	public Schedule roundRobinCreateSchedule(List<Team> teams);
}
