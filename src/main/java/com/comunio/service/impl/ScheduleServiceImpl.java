package com.comunio.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.ScheduleDao;
import com.comunio.model.Groupe;
import com.comunio.model.Schedule;
import com.comunio.service.MatchdayService;
import com.comunio.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleDao scheduleDao;
	@Autowired
	private MatchdayService matchdayService;
	private RoundRobinServiceImpl roundRobinService = new RoundRobinServiceImpl();
	
	@Transactional
	public void createSchedule(Groupe group) {
		Schedule schedule = roundRobinService.roundRobinCreateSchedule(new ArrayList<>(group.getTeams()));
		schedule.setGroupe(group);
		persistSchedule(schedule);
	}

	private void persistSchedule(Schedule schedule) {
		//scheduleDao.saveSchedule(schedule);
	}
}
