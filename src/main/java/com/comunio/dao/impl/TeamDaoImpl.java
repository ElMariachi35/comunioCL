package com.comunio.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.TeamDao;
import com.comunio.model.Team;

@Repository
public class TeamDaoImpl implements TeamDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addTeams(List<Team> teams) {
		Session session = sessionFactory.getCurrentSession();
		for(Team team : teams){
			session.save(team);
		}
	}

}
