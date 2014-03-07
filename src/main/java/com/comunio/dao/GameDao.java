package com.comunio.dao;

import java.util.List;

import com.comunio.model.Game;
import com.comunio.model.Team;

public interface GameDao {
    void saveMatch(Game game);

    List<Game> getGamesByTeam(Team team);

    void updateGame(Game game);
}
