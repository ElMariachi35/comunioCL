package com.comunio.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.FixtureDao;
import com.comunio.model.Fixture;
import com.comunio.model.Groupe;
import com.comunio.service.FixtureService;
import com.comunio.service.MatchdayService;

@Service
public class FixtureServiceImpl implements FixtureService {

	@Autowired
	FixtureDao fixtureDao;
	@Autowired
	MatchdayService matchdayService;
	RoundRobinServiceImpl roundRobinService = new RoundRobinServiceImpl();
	
	@Transactional
	public void createFixture(Groupe group) {
		Fixture fixture = roundRobinService.roundRobinCreateFixture(new ArrayList<>(group.getTeams()));
		fixture.setGroupe(group);
		persistFixture(fixture);
	}

	private void persistFixture(Fixture fixture) {
		fixtureDao.addFixture(fixture);
		matchdayService.saveMatchdays(fixture.getMatchdays());
	}

	@Transactional
	public Fixture getFixture(Groupe group) {
		return fixtureDao.getFixtureByGroupId(group.getGroupId());
	}
}
