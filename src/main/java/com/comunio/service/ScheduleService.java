package com.comunio.service;

import com.comunio.model.Groupe;
import com.comunio.model.Schedule;

public interface ScheduleService {
	public void addSchedule(Schedule schedule, Groupe group);
}
