package com.comunio.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.comunio.model.Groupe;
import com.comunio.model.Team;

public class PlayoffRankingServiceTest {

	private Groupe groupA;
	private Groupe groupB;
	private Groupe groupC;
	private Groupe groupD;

	private PlayoffRankingService playoffRankingService = new PlayoffRankingService();

	@Before
	public void setUp() {
	}

	@Test
	public void correctPlayoffTeamsForFourTeamsAreReturned() {
		groupA = createGroup(Arrays.asList(createTeam(1, "Team1", 15, 9),
				createTeam(2, "Team2", 12, 9), createTeam(3, "Team3", 11, 9),
				createTeam(4, "Team4", 6, 9)));

		Map<Integer, Team> playoffTeams = playoffRankingService
				.determinePlayoffTeam(Arrays.asList(groupA), 4);
		assertEquals(4, playoffTeams.size());
		assertEquals("Team1", playoffTeams.get(1).getTeamName());
		assertEquals("Team2", playoffTeams.get(2).getTeamName());
		assertEquals("Team3", playoffTeams.get(3).getTeamName());
		assertEquals("Team4", playoffTeams.get(4).getTeamName());
	}

	@Test
	public void correctPlayoffTeamsForFiveTeamsAreReturned() {
		groupA = createGroup(Arrays.asList(createTeam(5, "Team5", 18, 9),
				createTeam(1, "Team1", 15, 9), createTeam(2, "Team2", 12, 9),
				createTeam(3, "Team3", 11, 9), createTeam(4, "Team4", 6, 9)));

		Map<Integer, Team> playoffTeams = playoffRankingService
				.determinePlayoffTeam(Arrays.asList(groupA), 5);
		assertEquals(4, playoffTeams.size());
		assertEquals("Team5", playoffTeams.get(1).getTeamName());
		assertEquals("Team1", playoffTeams.get(2).getTeamName());
		assertEquals("Team2", playoffTeams.get(3).getTeamName());
		assertEquals("Team3", playoffTeams.get(4).getTeamName());
	}

	@Test
	public void correctPlayoffTeamsForSixTeamsAreReturned() {
		groupA = createGroup(Arrays.asList(createTeam(6, "Team6", 20, 9),
				createTeam(5, "Team5", 18, 9), createTeam(1, "Team1", 15, 9),
				createTeam(2, "Team2", 12, 9), createTeam(3, "Team3", 11, 9),
				createTeam(4, "Team4", 6, 9)));

		Map<Integer, Team> playoffTeams = playoffRankingService
				.determinePlayoffTeam(Arrays.asList(groupA), 6);
		assertEquals(4, playoffTeams.size());
		assertEquals("Team6", playoffTeams.get(1).getTeamName());
		assertEquals("Team5", playoffTeams.get(2).getTeamName());
		assertEquals("Team1", playoffTeams.get(3).getTeamName());
		assertEquals("Team2", playoffTeams.get(4).getTeamName());
	}

	@Test
	public void correctPlayoffTeamsForSevenTeamsAreReturned() {
		groupA = createGroup(Arrays.asList(createTeam(7, "Team7", 21, 9),
				createTeam(6, "Team6", 20, 9), createTeam(5, "Team5", 18, 9),
				createTeam(1, "Team1", 15, 9), createTeam(2, "Team2", 12, 9),
				createTeam(3, "Team3", 11, 9), createTeam(4, "Team4", 6, 9)));

		Map<Integer, Team> playoffTeams = playoffRankingService
				.determinePlayoffTeam(Arrays.asList(groupA), 7);
		assertEquals(4, playoffTeams.size());
		assertEquals("Team7", playoffTeams.get(1).getTeamName());
		assertEquals("Team6", playoffTeams.get(2).getTeamName());
		assertEquals("Team5", playoffTeams.get(3).getTeamName());
		assertEquals("Team1", playoffTeams.get(4).getTeamName());
	}

	@Test
	public void correctPlayoffTeamsForEightTeamsAreReturned() {
		groupA = createGroup(Arrays.asList(createTeam(1, "Team1", 15, 9),
				createTeam(2, "Team2", 12, 9), createTeam(3, "Team3", 11, 9),
				createTeam(4, "Team4", 6, 9)));

		groupB = createGroup(Arrays.asList(createTeam(8, "Team8", 22, 9),
				createTeam(7, "Team7", 21, 9), createTeam(6, "Team6", 20, 9),
				createTeam(5, "Team5", 18, 9)));

		Map<Integer, Team> playoffTeams = playoffRankingService
				.determinePlayoffTeam(Arrays.asList(groupA, groupB), 8);
		assertEquals(4, playoffTeams.size());
		assertEquals("Team8", playoffTeams.get(1).getTeamName());
		assertEquals("Team7", playoffTeams.get(2).getTeamName());
		assertEquals("Team1", playoffTeams.get(3).getTeamName());
		assertEquals("Team2", playoffTeams.get(4).getTeamName());
	}

	@Test
	public void correctPlayoffTeamsForNineTeamsAreReturned() {
		groupA = createGroup(Arrays.asList(createTeam(9, "Team9", 17, 9),
				createTeam(1, "Team1", 15, 9), createTeam(2, "Team2", 12, 9),
				createTeam(3, "Team3", 11, 9), createTeam(4, "Team4", 6, 9)));

		groupB = createGroup(Arrays.asList(createTeam(8, "Team8", 22, 9),
				createTeam(7, "Team7", 21, 9), createTeam(6, "Team6", 20, 9),
				createTeam(5, "Team5", 18, 9)));

		Map<Integer, Team> playoffTeams = playoffRankingService
				.determinePlayoffTeam(Arrays.asList(groupA, groupB), 9);
		assertEquals(4, playoffTeams.size());
		assertEquals("Team8", playoffTeams.get(1).getTeamName());
		assertEquals("Team7", playoffTeams.get(2).getTeamName());
		assertEquals("Team9", playoffTeams.get(3).getTeamName());
		assertEquals("Team1", playoffTeams.get(4).getTeamName());
	}

	@Test
	public void correctPlayoffTeamsForTenTeamsAreReturned() {
		groupA = createGroup(Arrays.asList(createTeam(9, "Team9", 17, 9),
				createTeam(1, "Team1", 15, 9), createTeam(2, "Team2", 12, 9),
				createTeam(3, "Team3", 11, 9), createTeam(4, "Team4", 6, 9)));

		groupB = createGroup(Arrays.asList(createTeam(10, "Team10", 25, 9),
				createTeam(8, "Team8", 22, 9), createTeam(7, "Team7", 21, 9),
				createTeam(6, "Team6", 20, 9), createTeam(5, "Team5", 18, 9)));

		Map<Integer, Team> playoffTeams = playoffRankingService
				.determinePlayoffTeam(Arrays.asList(groupA, groupB), 10);
		assertEquals(4, playoffTeams.size());
		assertEquals("Team10", playoffTeams.get(1).getTeamName());
		assertEquals("Team8", playoffTeams.get(2).getTeamName());
		assertEquals("Team9", playoffTeams.get(3).getTeamName());
		assertEquals("Team1", playoffTeams.get(4).getTeamName());
	}

	@Test
	public void correctPlayoffTeamsForElevenTeamsAreReturned() {
		groupA = createGroup(Arrays.asList(createTeam(11, "Team11", 18, 9),
				createTeam(9, "Team9", 17, 9), createTeam(1, "Team1", 15, 9),
				createTeam(2, "Team2", 12, 9), createTeam(3, "Team3", 11, 9),
				createTeam(4, "Team4", 6, 9)));

		groupB = createGroup(Arrays.asList(createTeam(10, "Team10", 25, 9),
				createTeam(8, "Team8", 22, 9), createTeam(7, "Team7", 21, 9),
				createTeam(6, "Team6", 20, 9), createTeam(5, "Team5", 18, 9)));

		Map<Integer, Team> playoffTeams = playoffRankingService
				.determinePlayoffTeam(Arrays.asList(groupA, groupB), 11);
		assertEquals(8, playoffTeams.size());
		assertEquals("Team10", playoffTeams.get(1).getTeamName());
		assertEquals("Team8", playoffTeams.get(2).getTeamName());
		assertEquals("Team7", playoffTeams.get(3).getTeamName());
		assertEquals("Team6", playoffTeams.get(4).getTeamName());
		assertEquals("Team11", playoffTeams.get(5).getTeamName());
		assertEquals("Team9", playoffTeams.get(6).getTeamName());
		assertEquals("Team1", playoffTeams.get(7).getTeamName());
		assertEquals("Team2", playoffTeams.get(8).getTeamName());
	}

	@Test
	public void correctPlayoffTeamsForTwelveTeamsAreReturned() {
		groupA = createGroup(Arrays.asList(createTeam(11, "Team11", 18, 9),
				createTeam(9, "Team9", 17, 9), createTeam(1, "Team1", 15, 9),
				createTeam(2, "Team2", 12, 9)));

		groupB = createGroup(Arrays.asList(createTeam(10, "Team10", 25, 9),
				createTeam(8, "Team8", 22, 9), createTeam(7, "Team7", 21, 9),
				createTeam(6, "Team6", 20, 9)));

		groupC = createGroup(Arrays
				.asList(createTeam(12, "Team12", 26, 9),
						createTeam(13, "Team13", 23, 9),
						createTeam(14, "Team14", 14, 9),
						createTeam(15, "Team15", 1, 9)));

		Map<Integer, Team> playoffTeams = playoffRankingService
				.determinePlayoffTeam(Arrays.asList(groupA, groupB, groupC), 12);
		assertEquals(8, playoffTeams.size());
		assertEquals("Team12", playoffTeams.get(1).getTeamName());
		assertEquals("Team10", playoffTeams.get(2).getTeamName());
		assertEquals("Team13", playoffTeams.get(3).getTeamName());
		assertEquals("Team8", playoffTeams.get(4).getTeamName());
		assertEquals("Team7", playoffTeams.get(5).getTeamName());
		assertEquals("Team11", playoffTeams.get(6).getTeamName());
		assertEquals("Team9", playoffTeams.get(7).getTeamName());
		assertEquals("Team1", playoffTeams.get(8).getTeamName());
	}

	@Test
	public void correctPlayoffTeamsForThirteenTeamsAreReturned() {
		groupA = createGroup(Arrays.asList(createTeam(11, "Team11", 18, 9),
				createTeam(9, "Team9", 17, 9), createTeam(1, "Team1", 15, 9),
				createTeam(2, "Team2", 12, 9), createTeam(16, "Team16", 8, 9)));

		groupB = createGroup(Arrays.asList(createTeam(10, "Team10", 25, 9),
				createTeam(8, "Team8", 22, 9), createTeam(7, "Team7", 21, 9),
				createTeam(6, "Team6", 20, 9)));

		groupC = createGroup(Arrays
				.asList(createTeam(12, "Team12", 26, 9),
						createTeam(13, "Team13", 23, 9),
						createTeam(14, "Team14", 14, 9),
						createTeam(15, "Team15", 1, 9)));

		Map<Integer, Team> playoffTeams = playoffRankingService
				.determinePlayoffTeam(Arrays.asList(groupA, groupB, groupC), 13);
		assertEquals(8, playoffTeams.size());
		assertEquals("Team12", playoffTeams.get(1).getTeamName());
		assertEquals("Team10", playoffTeams.get(2).getTeamName());
		assertEquals("Team13", playoffTeams.get(3).getTeamName());
		assertEquals("Team8", playoffTeams.get(4).getTeamName());
		assertEquals("Team7", playoffTeams.get(5).getTeamName());
		assertEquals("Team11", playoffTeams.get(6).getTeamName());
		assertEquals("Team9", playoffTeams.get(7).getTeamName());
		assertEquals("Team1", playoffTeams.get(8).getTeamName());
	}

	@Test
	public void correctPlayoffTeamsForFourteenTeamsAreReturned() {
		groupA = createGroup(Arrays.asList(createTeam(11, "Team11", 18, 9),
				createTeam(9, "Team9", 17, 9), createTeam(1, "Team1", 15, 9),
				createTeam(2, "Team2", 12, 9), createTeam(16, "Team16", 8, 9)));

		groupB = createGroup(Arrays.asList(createTeam(10, "Team10", 25, 9),
				createTeam(8, "Team8", 22, 9), createTeam(7, "Team7", 21, 9),
				createTeam(6, "Team6", 20, 9), createTeam(17, "Team17", 11, 9)));

		groupC = createGroup(Arrays
				.asList(createTeam(12, "Team12", 26, 9),
						createTeam(13, "Team13", 23, 9),
						createTeam(14, "Team14", 14, 9),
						createTeam(15, "Team15", 1, 9)));

		Map<Integer, Team> playoffTeams = playoffRankingService
				.determinePlayoffTeam(Arrays.asList(groupA, groupB, groupC), 14);
		assertEquals(8, playoffTeams.size());
		assertEquals("Team12", playoffTeams.get(1).getTeamName());
		assertEquals("Team10", playoffTeams.get(2).getTeamName());
		assertEquals("Team13", playoffTeams.get(3).getTeamName());
		assertEquals("Team8", playoffTeams.get(4).getTeamName());
		assertEquals("Team7", playoffTeams.get(5).getTeamName());
		assertEquals("Team11", playoffTeams.get(6).getTeamName());
		assertEquals("Team9", playoffTeams.get(7).getTeamName());
		assertEquals("Team1", playoffTeams.get(8).getTeamName());
	}

	@Test
	public void correctPlayoffTeamsForFifteenTeamsAreReturned() {
		groupA = createGroup(Arrays.asList(createTeam(11, "Team11", 18, 9),
				createTeam(9, "Team9", 17, 9), createTeam(1, "Team1", 15, 9),
				createTeam(2, "Team2", 12, 9), createTeam(16, "Team16", 8, 9)));

		groupB = createGroup(Arrays.asList(createTeam(10, "Team10", 25, 9),
				createTeam(8, "Team8", 22, 9), createTeam(7, "Team7", 21, 9),
				createTeam(6, "Team6", 20, 9), createTeam(17, "Team17", 11, 9)));

		groupC = createGroup(Arrays.asList(createTeam(12, "Team12", 26, 9),
				createTeam(13, "Team13", 23, 9),
				createTeam(14, "Team14", 14, 9),
				createTeam(15, "Team15", 2, 9), createTeam(18, "Team18", 1, 9)));

		Map<Integer, Team> playoffTeams = playoffRankingService
				.determinePlayoffTeam(Arrays.asList(groupA, groupB, groupC), 15);
		assertEquals(8, playoffTeams.size());
		assertEquals("Team12", playoffTeams.get(1).getTeamName());
		assertEquals("Team10", playoffTeams.get(2).getTeamName());
		assertEquals("Team13", playoffTeams.get(3).getTeamName());
		assertEquals("Team8", playoffTeams.get(4).getTeamName());
		assertEquals("Team7", playoffTeams.get(5).getTeamName());
		assertEquals("Team11", playoffTeams.get(6).getTeamName());
		assertEquals("Team9", playoffTeams.get(7).getTeamName());
		assertEquals("Team1", playoffTeams.get(8).getTeamName());
	}

	@Test
	public void correctPlayoffTeamsForSixteenTeamsAreReturned() {
		groupA = createGroup(Arrays.asList(createTeam(1, "Team1", 15, 9),
				createTeam(2, "Team2", 12, 9), createTeam(3, "Team3", 11, 9),
				createTeam(4, "Team4", 6, 9)));

		groupB = createGroup(Arrays.asList(createTeam(8, "Team8", 22, 9),
				createTeam(7, "Team7", 21, 9), createTeam(6, "Team6", 20, 9),
				createTeam(5, "Team5", 18, 9)));

		groupC = createGroup(Arrays.asList(createTeam(9, "Team9", 25, 9),
				createTeam(10, "Team10", 24, 9), createTeam(11, "Team11", 11, 9),
				createTeam(12, "Team12", 6, 9)));

		groupD = createGroup(Arrays.asList(createTeam(13, "Team13", 31, 9),
				createTeam(14, "Team14", 20, 9), createTeam(15, "Team15", 19, 9),
				createTeam(16, "Team16", 18, 9)));

		Map<Integer, Team> playoffTeams = playoffRankingService
				.determinePlayoffTeam(Arrays.asList(groupA, groupB, groupC,groupD), 16);
		assertEquals(8, playoffTeams.size());
		assertEquals("Team13", playoffTeams.get(1).getTeamName());
		assertEquals("Team9", playoffTeams.get(2).getTeamName());
		assertEquals("Team10", playoffTeams.get(3).getTeamName());
		assertEquals("Team8", playoffTeams.get(4).getTeamName());
		assertEquals("Team7", playoffTeams.get(5).getTeamName());
		assertEquals("Team14", playoffTeams.get(6).getTeamName());
		assertEquals("Team1", playoffTeams.get(7).getTeamName());
		assertEquals("Team2", playoffTeams.get(8).getTeamName());
	}

	public Groupe createGroup(List<Team> teams) {
		Groupe groupe = new Groupe();
		groupe.setTeams(new HashSet<Team>(teams));
		return groupe;
	}

	private Team createTeam(long id, String name, int points, int games) {
		Team team = new Team();
		team.setTeamId(id);
		team.setTeamName(name);
		team.setPoints(points);
		team.setGamesPlayed(games);
		return team;
	}
}
