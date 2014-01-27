package com.comunio.service;

import java.util.List;

import com.comunio.model.Fixture;
import com.comunio.model.Team;

public interface RoundRobinService {
	public Fixture roundRobinCreateFixture(List<Team> teams);
}
