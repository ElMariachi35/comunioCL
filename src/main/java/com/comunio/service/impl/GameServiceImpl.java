package com.comunio.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.GameDao;
import com.comunio.model.Game;
import com.comunio.model.Team;
import com.comunio.service.GameService;

@Service
public class GameServiceImpl implements GameService, Serializable {
    @Autowired
    GameDao gameDao;

    @Transactional
    public void saveGame(Game game) {
        gameDao.saveMatch(game);
    }

    public List<Game> getGamesByTeam(Team team) {
        return gameDao.getGamesByTeam(team);
    }

    @Transactional
    public void updateGame(Game game) {
        gameDao.updateGame(game);
    }

}
