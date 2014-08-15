package com.comunio.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.GameDao;
import com.comunio.model.Game;
import com.comunio.model.Groupe;
import com.comunio.model.Matchday;
import com.comunio.model.SessionData;
import com.comunio.model.Team;
import com.comunio.service.GameService;
import com.comunio.service.GroupService;

@Service
public class GameServiceImpl implements GameService, Serializable {
    private static final long serialVersionUID = 1517716083015072582L;

    @Autowired
    GameDao gameDao;
    @Autowired
    SessionData sessionData;
    @Autowired
    GroupService groupService;

    @Transactional
    public void saveGame(Game game) {
        gameDao.saveMatch(game);
    }

    @Transactional
    public void updateGame(Game game) {
        gameDao.updateGame(game);
    }

    public List<Game> getGamesByTeam(Team team, long comunioId) {
        List<Game> games = new ArrayList<Game>();
        for (Game game : getGames(comunioId)) {
            if (isTeamMemberOfGame(team, game)) {
                games.add(game);
            }
        }
        return games;
    }

    private boolean isTeamMemberOfGame(Team team, Game game) {
        return team.getTeamId() == game.getHomeTeam().getTeamId() || team.getTeamId() == game.getAwayTeam().getTeamId();
    }

    @Override
    public List<Game> getGames(long comunioId) {
        List<Game> games = new ArrayList<Game>();
        for (Groupe group : groupService.findGroupsByComunioId(comunioId)) {
            for (Matchday matchday : group.getFixture().getMatchdays()) {
                for (Game game : matchday.getMatches()) {
                    games.add(game);
                }
            }
        }
        return games;
    }

    @Override
    public List<Game> getGames() {
        List<Game> games = new ArrayList<Game>();
        for (Groupe group : sessionData.getComunio().getGroups()) {
            for (Matchday matchday : group.getFixture().getMatchdays()) {
                for (Game game : matchday.getMatches()) {
                    games.add(game);
                }
            }
        }
        return games;
    }
}
