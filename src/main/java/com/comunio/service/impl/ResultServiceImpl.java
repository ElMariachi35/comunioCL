package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.ResultDao;
import com.comunio.dao.TeamDao;
import com.comunio.model.Game;
import com.comunio.model.GameResult;
import com.comunio.model.JsonResult;
import com.comunio.model.Result;
import com.comunio.model.SessionData;
import com.comunio.model.Team;
import com.comunio.service.ComunioService;
import com.comunio.service.FixtureService;
import com.comunio.service.GameService;
import com.comunio.service.GroupService;
import com.comunio.service.PlayoffResultService;
import com.comunio.service.ResultService;
import com.comunio.service.TeamService;

@Service
public class ResultServiceImpl implements ResultService {

    private static final int LAST_MATCHDAY = 17;
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
    @Autowired
    SessionData sessionData;
    @Autowired
    GroupService groupService;
    @Autowired
    PlayoffResultService playoffResultService;
    @Autowired
    ResultGoalFinder resultGoalFinder;

    GoalCalculator goalCalculator = new GoalCalculator();

    @Override
    @Transactional
    public List<Result> findResultsBy(long comunioId, int matchdayNumber) {
        return resultDao.findResultsBy(comunioId, matchdayNumber);
    }

    @Override
    @Transactional
    public int findNextMatchday(long comunioId) {
        int latestMatchday = resultDao.findLatestMatchday(comunioId);
        if (latestMatchday < LAST_MATCHDAY) {
            return ++latestMatchday;
        }
        return LAST_MATCHDAY;
    }
    

    @Override
    @Transactional
    public long findNumberOfResults(long comunioId) {
	return resultDao.findNumberOfResults(comunioId);
    }

    @Override
    @Transactional
    public void updateResult(List<JsonResult> jsonResults, long comunioId) {
        List<Result> results = saveResults(jsonResults);
        List<Result> allResults = resultDao.getResults(comunioId);
        List<Game> games = gameService.getGames(comunioId);
        updateGames(games, allResults);
        for (Team team : comunioService.getAllTeams(comunioId)) {
            updateTeams(team, comunioId);
        }
        
        playoffResultService.handlePlayoff(results, comunioId);
    }

    private List<Result> saveResults(List<JsonResult> results) {
        List<Result> storedResults = new ArrayList<Result>();
        for (JsonResult jsonResult : results) {
            Team team = findTeam(jsonResult.getTeamId());
            Result result = new Result();
            result.setTeam(team);
            result.setMatchday(jsonResult.getMatchdayNumber());
            result.setPoints(jsonResult.getPoints());
            result.setGoals(calculateGoals(jsonResult.getPoints()));
            storedResults.add(resultDao.saveOrUpdate(result));
        }
        return storedResults;
    }

    private Team findTeam(long teamId) {
        return teamService.findBy(teamId);
    }

    private void updateGames(List<Game> games, List<Result> results) {
        for (Game game : games) {
            Team homeTeam = game.getHomeTeam();
            Team awayTeam = game.getAwayTeam();
            int matchdayNumber = game.getMatchday().getComunioMatchdayNumber();
            game.setHomeGoals(resultGoalFinder.findGoalsFromResult(homeTeam, matchdayNumber, results));
            game.setAwayGoals(resultGoalFinder.findGoalsFromResult(awayTeam, matchdayNumber, results));
            gameService.updateGame(game);
        }
    }

    private void updateTeams(Team team, long comunioId) {
        List<Game> games = gameService.getGamesByTeam(team, comunioId);
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

    private int calculateGoals(int points) {
        return goalCalculator.calculateGoalsFromPoints(points);
    }
}
