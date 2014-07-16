package com.comunio.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.comunio.model.Result;
import com.comunio.model.Team;

@Service
public class ResultGoalFinder implements Serializable {

    public int findGoalsFromResult(Team team, int matchdayNumber, List<Result> results) {
        for (Result result : results) {
            if (result.getMatchday() == matchdayNumber && result.getTeam().getTeamId() == team.getTeamId()) {
                return result.getGoals();
            }
        }
        return 0;
    }
}
