package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.comunio.dao.ResultDao;
import com.comunio.dao.TeamDao;
import com.comunio.model.Result;
import com.comunio.service.ComunioService;
import com.comunio.service.GameService;
import com.comunio.service.TeamService;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceImplTest {
    private static final String TEAM2 = "team2";
    private static final int GOALS = 2;
    private static final int POINTS = 15;
    private static final String TEAM = "team1";
    private static final long COMUNIO_ID = 1l;
    @Mock
    private TeamDao teamDao;
    @Mock
    private ResultDao resultDao;
    @Mock
    private GoalCalculator goalCalculator;
    @Mock
    private Result result;
    @Mock
    private ComunioService comunioService;
    @Mock
    private TeamService teamService;
    @Mock
    private GameService gameService;

    private ResultServiceImpl resultService = new ResultServiceImpl();

    @Before
    public void setUp() {
        resultService.teamDao = teamDao;
        resultService.resultDao = resultDao;
        resultService.comunioService = comunioService;
        resultService.goalCalculator = goalCalculator;
        resultService.teamService = teamService;
        resultService.gameService = gameService;
    }

    @Test
    public void whenUpdatingResultEveryTeamInResultIsLookedFor() {
        // when(teamService.findTeamByTeamNameAndComunioId(TEAM,
        // COMUNIO_ID)).thenReturn(new Team());
        // when(goalCalculator.calculateGoalsFromPoints(POINTS)).thenReturn(GOALS);
        // when(gameService.getGames()).thenReturn(new ArrayList<Game>());
        //
        // resultService.updateResult(mockCollectiveResult(), COMUNIO_ID);
        //
        // verify(teamService).findTeamByTeamNameAndComunioId(TEAM, COMUNIO_ID);
        // verify(teamService).findTeamByTeamNameAndComunioId(TEAM2,
        // COMUNIO_ID);
        // verify(resultDao, times(4)).saveOrUpdate(any(Result.class));
    }

    private List<?> mockCollectiveResult() {
        List<String> collectiveResult = new ArrayList<>();
        collectiveResult.add("team1;12;23;-;-;-;-;-;-;-;-;-;-;-;-;-;-;-;");
        collectiveResult.add("team2;12;23;-;-;-;-;-;-;-;-;-;-;-;-;-;-;-;");

        return collectiveResult;
    }
}
