package com.comunio.service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.comunio.dao.GameDao;
import com.comunio.model.Game;
@RunWith(MockitoJUnitRunner.class)
public class GameServiceImplTest {
    @Mock
    private GameDao gameDao;
    
    private GameServiceImpl gameService = new GameServiceImpl();
    
    @Before
    public void setUp(){
        gameService.gameDao = gameDao;
    }
    
    @Test
    public void saveGameCallsCorrectMethod() {
        Game game = new Game();
        gameService.saveGame(game);
        verify(gameDao).saveMatch(game);
    }
}
