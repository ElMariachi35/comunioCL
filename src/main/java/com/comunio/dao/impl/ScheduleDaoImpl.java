package com.comunio.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.ScheduleDao;
import com.comunio.model.Schedule;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveSchedule(Schedule schedule) {
		sessionFactory.getCurrentSession().save(schedule);
	}

	@Override
	public void updateSchedule(Schedule schedule) {
		sessionFactory.getCurrentSession().update(schedule);
	}
}
