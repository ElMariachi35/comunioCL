package com.comunio.service;

import java.util.Map;

import com.comunio.model.PlayoffFixture;
import com.comunio.model.Team;

public interface PlayoffFixtureService {
    PlayoffFixture createFixture(Map<Integer, Team> teams);

}
