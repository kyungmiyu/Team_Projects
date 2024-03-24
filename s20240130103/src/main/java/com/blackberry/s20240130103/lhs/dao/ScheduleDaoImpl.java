package com.blackberry.s20240130103.lhs.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.lhs.model.Schedule;

import lombok.RequiredArgsConstructor;
@Repository
@RequiredArgsConstructor
public class ScheduleDaoImpl implements ScheduleDao {
	
	private final SqlSession session;
	
	@Override
	public int scheduleAdd(Schedule schedule) {
		int result = session.insert("LhsScheduleInsert",schedule);
		return result;
	}
	
	@Override
	public List<Schedule> scheduleList(Schedule schedule) {
		List<Schedule> scheduleList = session.selectList("LhsScheduleList", schedule);
		return scheduleList;
	}
	@Override
	public int scheduleDelete(Schedule schedule) {
		int result = session.delete("LhsScheduleDelete", schedule);
		return result;
	}
	
	@Override
	public Schedule scheduleSelectOne(Schedule schedule) {
		Schedule scheduleOne = session.selectOne("LhsScheduleSelectOne", schedule);
		return scheduleOne;
	}
	
	@Override
	public int scheduleUpdate(Schedule schedule) {
		int result = session.update("LhsScheduleUpdate", schedule);
		return result;
	}
}
