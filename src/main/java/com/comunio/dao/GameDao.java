package com.comunio.dao;

import com.comunio.model.Game;

public interface GameDao {
    void saveMatch(Game game);

    void updateGame(Game game);
}
