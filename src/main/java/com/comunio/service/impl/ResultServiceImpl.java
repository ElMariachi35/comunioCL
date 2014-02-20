package com.comunio.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.ResultDao;
import com.comunio.dao.TeamDao;
import com.comunio.model.Result;
import com.comunio.model.Team;
import com.comunio.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    ResultDao resultDao;
    @Autowired
    TeamDao teamDao;
    GoalCalculator goalCalculator = new GoalCalculator();

    @Transactional
    public void updateResult(List<?> collectiveResult, long comunioId) {
        for (Object singleResultObject : collectiveResult) {
            saveTeamResult(comunioId, singleResultObject);
        }
    }

    private void saveTeamResult(long comunioId, Object singleResultObject) {
        List<String> singleTeamResult = prepareTeamResult(singleResultObject);
        Team team = findTeam(comunioId, singleTeamResult);
        for (int matchday = 1; matchday < 18; matchday++) {
            if (isValidResult(singleTeamResult, matchday)) {
                Result result = new Result();
                result.setTeam(team);
                result.setMatchday(matchday);
                result.setPoints(getPointsFromString(singleTeamResult, matchday));
                result.setGoals(calculateGoals(result.getPoints()));
                resultDao.saveOrUpdate(result);
            }
        }
    }

    private boolean isValidResult(List<String> resultStringList, int matchday) {
        return !resultStringList.get(matchday).equals("-");
    }

    private int getPointsFromString(List<String> resultStringList, int matchday) {
        return Integer.parseInt(resultStringList.get(matchday));
    }

    private Team findTeam(long comunioId, List<String> resultStringList) {
        return teamDao.findTeamByTeamNameAndComunioId(resultStringList.get(0), comunioId);
    }

    private List<String> prepareTeamResult(Object singleTeamResult) {
        String resultString = singleTeamResult.toString();
        List<String> resultStringList = Arrays.asList(resultString.split(";"));
        return resultStringList;
    }

    private int calculateGoals(int points) {
        return goalCalculator.calculateGoalsFromPoints(points);
    }

}
