package com.comunio.service;

import java.util.List;

import com.comunio.model.Game;
import com.comunio.model.Team;

public interface GameService {
    public void saveGame(Game game);

    List<Game> getGamesByTeam(Team team);

    public void updateGame(Game game);
}
