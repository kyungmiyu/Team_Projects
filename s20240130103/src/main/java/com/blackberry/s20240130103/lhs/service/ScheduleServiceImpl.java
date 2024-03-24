package com.blackberry.s20240130103.lhs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackberry.s20240130103.lhs.dao.ScheduleDao;
import com.blackberry.s20240130103.lhs.model.Schedule;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

	private final ScheduleDao scheduleDao;
	
	@Override
	public int scheduleAdd(Schedule schedule) {
		int result = scheduleDao.scheduleAdd(schedule);
		return result;
	}

	@Override
	public List<Schedule> scheduleList(Schedule schedule) {
		List<Schedule> scheduleList = scheduleDao.scheduleList(schedule);
		return scheduleList;
	}
	
	@Override
	public int scheduleDelete(Schedule schedule) {
		int result = scheduleDao.scheduleDelete(schedule);
		return result;
	}
	
	@Override
	public Schedule scheduleSelectOne(Schedule schedule) {
		Schedule scheduleOne = scheduleDao.scheduleSelectOne(schedule);
		return scheduleOne;
	}
	
	@Override
	public int scheduleUpdate(Schedule schedule) {
		int result = scheduleDao.scheduleUpdate(schedule);
		return result;
	}
}
