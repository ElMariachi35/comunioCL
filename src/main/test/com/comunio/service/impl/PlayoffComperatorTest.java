package com.comunio.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.comunio.comparator.PlayoffTeamComparator;
import com.comunio.model.Team;

public class PlayoffComperatorTest {

	private PlayoffTeamComparator playoffComperator = new PlayoffTeamComparator();
	
	private Team team1;
	private Team team2;
	
	@Before
	public void setUp(){
		team1 = new Team();
		team1.setPoints(12);
		team1.setGamesPlayed(9);
		
		team2 = new Team();
		team2.setPoints(11);
		team2.setGamesPlayed(8);
		
	}
	
	@Test
	public void testName() throws Exception {
		assertEquals(-1,playoffComperator.compare(team2, team1));
	}
}
