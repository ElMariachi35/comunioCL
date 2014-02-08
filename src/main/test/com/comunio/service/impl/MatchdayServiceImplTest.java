package com.comunio.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.comunio.dao.MatchdayDao;
import com.comunio.model.Fixture;
import com.comunio.model.Game;
import com.comunio.model.Groupe;
import com.comunio.model.Matchday;
import com.comunio.service.FixtureService;
import com.comunio.service.GameService;

@RunWith(MockitoJUnitRunner.class)
public class MatchdayServiceImplTest {
    
    private static final int COMUNIO_MATCHDAY_NUMBER_THREE = 3;
    private static final int COMUNIO_MATCHDAY_NUMBER_TWO = 2;
    private static final int COMUNIO_MATCHDAY_NUMBER_ONE = 1;
    @Mock
    private MatchdayDao matchdayDao;
    @Mock
    private GameService gameService;
    @Mock
    private FixtureService fixtureService;
    @Mock
    private Matchday matchday;
    @Mock
    private Fixture fixture;
    
    private MatchdayServiceImpl matchdayService = new MatchdayServiceImpl();
    
    @Before
    public void setUp(){
        matchdayService.matchdayDao = matchdayDao;
        matchdayService.gameService = gameService;
        matchdayService.fixtureService = fixtureService;
    }
    
    @Test
    public void saveMatchdaysSavesAllMatchdays() {
        Set<Matchday> matchdays = mockMatchdays();
        Set<Game> games = mockGames();
        when(matchday.getMatches()).thenReturn(games);
        matchdayService.saveMatchdays(matchdays);
        verify(matchdayDao).saveMatchday(any(Matchday.class));
        verify(gameService, times(2)).saveGame(any(Game.class));
    }
    
    @Test
    public void getSortedMatchdaysReturnsAListOfMatchdaysSortedByComunioMatchdayNumber() {
        Set<Matchday> unsortedMatchdays = mockUnsortedMatchdays();
        when(fixtureService.getFixture(any(Groupe.class))).thenReturn(fixture);
        when(fixture.getMatchdays()).thenReturn(unsortedMatchdays);
        
        List<Matchday> sortedMatchdays = matchdayService.getSortedMatchdays(new Groupe());
        
        assertEquals(COMUNIO_MATCHDAY_NUMBER_ONE, sortedMatchdays.get(0).getComunioMatchdayNumber());
        assertEquals(COMUNIO_MATCHDAY_NUMBER_TWO, sortedMatchdays.get(1).getComunioMatchdayNumber());
        assertEquals(COMUNIO_MATCHDAY_NUMBER_THREE, sortedMatchdays.get(2).getComunioMatchdayNumber());
    }

    private Set<Matchday> mockUnsortedMatchdays() {
        Set<Matchday> matchdays =  new HashSet<Matchday>();
        Matchday matchday1 = new Matchday();
        matchday1.setComunioMatchdayNumber(COMUNIO_MATCHDAY_NUMBER_ONE);
        Matchday matchday2 = new Matchday();
        matchday2.setComunioMatchdayNumber(COMUNIO_MATCHDAY_NUMBER_TWO);
        Matchday matchday3 = new Matchday();
        matchday3.setComunioMatchdayNumber(COMUNIO_MATCHDAY_NUMBER_THREE);
        matchdays.add(matchday2);
        matchdays.add(matchday3);
        matchdays.add(matchday1);
        return matchdays;
    }

    private Set<Game> mockGames() {
        Set<Game> games = new HashSet<>();
        games.add(new Game());
        games.add(new Game());
        return games;
    }

    private Set<Matchday> mockMatchdays() {
        Set<Matchday> matchdays = new HashSet<>();
        matchdays.add(matchday);
        return matchdays;
    }
}
