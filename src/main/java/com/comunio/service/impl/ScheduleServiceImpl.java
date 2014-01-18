package com.comunio.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.ScheduleDao;
import com.comunio.model.Groupe;
import com.comunio.model.Matchday;
import com.comunio.model.Schedule;
import com.comunio.service.MatchdayService;
import com.comunio.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleDao scheduleDao;
	@Autowired
	private MatchdayService matchdayService;
	
	@Transactional
	public void createSchedule(Groupe group) {
		Schedule schedule = new Schedule();
		schedule.setGroupe(group);
		scheduleDao.saveSchedule(schedule);
		Set<Matchday> matchdays = matchdayService.createMatchdays(group, schedule);		
		schedule.setMatchdays(matchdays);
		scheduleDao.updateSchedule(schedule);
	}
}
