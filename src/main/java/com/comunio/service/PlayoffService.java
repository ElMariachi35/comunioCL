package com.comunio.service;

import java.util.Map;

import com.comunio.model.Team;

public interface PlayoffService {
    void initializePlayoffs(Map<Integer, Team> teams);
}
