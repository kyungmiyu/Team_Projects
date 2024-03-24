package com.blackberry.s20240130103.lhs.dao;

import java.util.List;

import com.blackberry.s20240130103.lhs.model.Schedule;

public interface ScheduleDao {

	int scheduleAdd(Schedule schedule);

	List<Schedule> scheduleList(Schedule schedule);

	int scheduleDelete(Schedule schedule);

	Schedule scheduleSelectOne(Schedule schedule);

	int scheduleUpdate(Schedule schedule);

}
