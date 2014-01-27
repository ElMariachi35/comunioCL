package com.comunio.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.FixtureDao;
import com.comunio.model.Fixture;
import com.comunio.model.Matchday;

@Repository
public class FixtureDaoImpl implements FixtureDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveFixture(Fixture fixture) {
		sessionFactory.getCurrentSession().save(fixture);
	}

	@Override
	public void updateFixture(Fixture fixture) {
		sessionFactory.getCurrentSession().update(fixture);
	}

	@Override
	public Fixture getFixtureByGroupId(long groupId) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("Select * from fixture where groupId= :groupId").addEntity(Fixture.class).setParameter("groupId", groupId);
		return (Fixture) query.list().get(0);
//		List<Matchday> matchdays = fixture.getMatchdays();
//		LinkedHashSet<Matchday> set = new LinkedHashSet<>(matchdays);
//		matchdays = new ArrayList<Matchday>(set);
//		fixture.setMatchdays(matchdays);
//		return fixture;
	}
}
