package com.comunio.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunio.dao.MatchdayDao;
import com.comunio.model.Groupe;
import com.comunio.model.Matchday;
import com.comunio.model.Schedule;
import com.comunio.service.MatchdayService;

@Service
public class MatchdayServiceImpl implements MatchdayService{

	@Autowired
	private MatchdayDao matchdayDao;
	
	@Override
	public Set<Matchday> createMatchdays(Groupe group, Schedule schedule) {
		Set<Matchday> matchdays = new HashSet<Matchday>();
		Matchday matchday = new Matchday();
		matchday.setSchedule(schedule);
		//DUMMY IMPLEMENTATION
		//TODO create matchdays with actual matches
		matchday.setComunioMatchdayNumber(1);
		matchday.setLeagueMatchdayNumber(1);
		matchdayDao.saveMatchday(matchday);
		
		matchdays.add(matchday);
		return matchdays;
	}	
}
