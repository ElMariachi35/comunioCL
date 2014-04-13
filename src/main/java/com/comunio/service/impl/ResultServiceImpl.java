package com.comunio.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.ResultDao;
import com.comunio.dao.TeamDao;
import com.comunio.model.Game;
import com.comunio.model.GameResult;
import com.comunio.model.Result;
import com.comunio.model.Team;
import com.comunio.service.ComunioService;
import com.comunio.service.FixtureService;
import com.comunio.service.GameService;
import com.comunio.service.ResultService;
import com.comunio.service.TeamService;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    ResultDao resultDao;
    @Autowired
    TeamDao teamDao;
    @Autowired
    FixtureService fixtureService;
    @Autowired
    GameService gameService;
    @Autowired
    ComunioService comunioService;
    @Autowired
    TeamService teamService;

    GoalCalculator goalCalculator = new GoalCalculator();

    @Transactional
    public void updateResult(List<?> collectiveResult, long comunioId) {
        saveResults(collectiveResult, comunioId);

        List<Result> results = resultDao.getResults(comunioId);
        List<Game> games = gameService.getGames();
        updateGames(games, results);

        for (Team team : comunioService.getAllTeams()) {
            updateTeams(team);
        }
    }

    private void saveResults(List<?> collectiveResult, long comunioId) {
        for (Object singleResultObject : collectiveResult) {
            saveTeamResult(comunioId, singleResultObject);
        }
    }

    private void updateGames(List<Game> games, List<Result> results) {
        for (Game game : games) {
            Team homeTeam = game.getHomeTeam();
            Team awayTeam = game.getAwayTeam();
            int matchdayNumber = game.getMatchday().getComunioMatchdayNumber();
            game.setHomeGoals(findGoalsFromResult(homeTeam, matchdayNumber, results));
            game.setAwayGoals(findGoalsFromResult(awayTeam, matchdayNumber, results));
            gameService.updateGame(game);
        }
    }

    private int findGoalsFromResult(Team team, int matchdayNumber, List<Result> results) {
        for (Result result : results) {
            if (result.getMatchday() == matchdayNumber && result.getTeam().getTeamId() == team.getTeamId()) {
                return result.getGoals();
            }
        }
        return 0;
    }

    private void updateTeams(Team team) {
        List<Game> games = gameService.getGamesByTeam(team);
        List<Result> results = getResultsByTeam(team);
        Team teamResult = results.get(0).getTeam();
        if (teamResult != null) {
            teamResult.resetAttributes();
        }
        for (Result result : results) {
            for (Game game : games) {
                if (result.getMatchday() == game.getMatchday().getComunioMatchdayNumber()) {
                    updateTeamResult(teamResult, game);
                }
            }
        }
    }

    public List<Result> getResultsByTeam(Team team) {
        return resultDao.getResultsByTeam(team);
    }

    private void updateTeamResult(Team team, Game game) {
        if (hasWon(team, game)) {
            updateTeamAttributes(GameResult.WON, team, game);
            return;
        }
        if (hasLost(team, game)) {
            updateTeamAttributes(GameResult.LOST, team, game);
            return;
        }
        if (hasDrawn(team, game)) {
            updateTeamAttributes(GameResult.DRAW, team, game);
        }
    }

    private void updateTeamAttributes(GameResult result, Team team, Game game) {
        team.setGamesPlayed(team.getGamesPlayed() + 1);
        team.setGoalsFor(team.getGoalsFor() + getGoalsFor(team, game));
        team.setGoalsAgainst(team.getGoalsAgainst() + getGoalsAgainst(team, game));
        team.setGoalDifference(determineGoalDifference(team));

        if (result == GameResult.WON) {
            team.setGamesWon(team.getGamesWon() + 1);
            team.setPoints(team.getPoints() + 3);
        } else if (result == GameResult.LOST) {
            team.setGamesLost(team.getGamesLost() + 1);
        } else {
            team.setGamesDrawn(team.getGamesDrawn() + 1);
            team.setPoints(team.getPoints() + 1);
        }
        teamService.updateTeam(team);
    }

    private int determineGoalDifference(Team team) {
        return team.getGoalsFor() - team.getGoalsAgainst();
    }

    private int getGoalsAgainst(Team team, Game game) {
        if (isHomeTeam(team, game)) {
            return game.getAwayGoals();
        } else {
            return game.getHomeGoals();
        }
    }

    private int getGoalsFor(Team team, Game game) {
        if (isHomeTeam(team, game)) {
            return game.getHomeGoals();
        } else {
            return game.getAwayGoals();
        }
    }

    private boolean hasDrawn(Team team, Game game) {
        if ((isHomeTeam(team, game) || isAwayTeam(team, game)) && isDraw(game)) {
            return true;
        }
        return false;
    }

    private boolean isDraw(Game game) {
        return game.getHomeGoals() == game.getAwayGoals();
    }

    private boolean hasLost(Team team, Game game) {
        if (isHomeTeam(team, game) && homeTeamLost(game)) {
            return true;
        }
        if (isAwayTeam(team, game) && awayTeamLost(game)) {
            return true;
        }
        return false;
    }

    private boolean awayTeamLost(Game game) {
        return game.getAwayGoals() < game.getHomeGoals();
    }

    private boolean homeTeamLost(Game game) {
        return game.getHomeGoals() < game.getAwayGoals();
    }

    private boolean hasWon(Team team, Game game) {
        if (isHomeTeam(team, game) && homeTeamWon(game)) {
            return true;
        }
        if (isAwayTeam(team, game) && awayTeamWon(game)) {
            return true;
        }
        return false;
    }

    private boolean awayTeamWon(Game game) {
        return game.getAwayGoals() > game.getHomeGoals();
    }

    private boolean homeTeamWon(Game game) {
        return game.getHomeGoals() > game.getAwayGoals();
    }

    private boolean isAwayTeam(Team team, Game game) {
        return team.getTeamId() == game.getAwayTeam().getTeamId();
    }

    private boolean isHomeTeam(Team team, Game game) {
        return team.getTeamId() == game.getHomeTeam().getTeamId();
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
        // return
        // teamDao.findTeamByTeamNameAndComunioId(resultStringList.get(0),
        // comunioId);
        return teamService.findTeamByTeamNameAndComunioId(resultStringList.get(0), comunioId);

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
