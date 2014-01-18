package com.comunio.dao;

import com.comunio.model.Schedule;

public interface ScheduleDao {
	void saveSchedule(Schedule schedule);
	void updateSchedule(Schedule schedule);
}
