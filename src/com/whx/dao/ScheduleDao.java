package com.whx.dao;

import java.util.List;

import com.whx.bean.Doctor;
import com.whx.bean.Period;
import com.whx.bean.Schedule;

public interface ScheduleDao {
	public List<Period> findPeriods(String doctorName);
	public Schedule find(String doctorId, String date, String firstPeriodBegin,
			String lastPeriodBegin);
	public boolean save(Doctor doctor, Period period);
	public boolean delete(String doctorId, String date, String beginTime,
			String endTime);

}
