package com.comunio.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.FixtureDao;
import com.comunio.model.Groupe;
import com.comunio.model.Matchday;
import com.comunio.model.Fixture;
import com.comunio.service.MatchdayService;
import com.comunio.service.FixtureService;

@Service
public class FixtureServiceImpl implements FixtureService {

	@Autowired
	private FixtureDao fixtureDao;
	@Autowired
	private MatchdayService matchdayService;
	private RoundRobinServiceImpl roundRobinService = new RoundRobinServiceImpl();
	
	@Transactional
	public void createFixture(Groupe group) {
		Fixture fixture = roundRobinService.roundRobinCreateFixture(new ArrayList<>(group.getTeams()));
		fixture.setGroupe(group);
		persistFixture(fixture);
	}

	private void persistFixture(Fixture fixture) {
		fixtureDao.saveFixture(fixture);
		matchdayService.saveMatchdays(fixture.getMatchdays());
	}

	@Transactional
	public Fixture getFixture(Groupe group) {
		long groupId = group.getGroupId();
		return fixtureDao.getFixtureByGroupId(groupId);
	}
}
