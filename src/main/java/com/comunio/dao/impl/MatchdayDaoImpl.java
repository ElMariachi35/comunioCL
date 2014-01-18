package com.comunio.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.MatchdayDao;
import com.comunio.model.Matchday;

@Repository
public class MatchdayDaoImpl implements MatchdayDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveMatchday(Matchday matchday) {
		sessionFactory.getCurrentSession().save(matchday);
	}
}
