package com.whx.dao.Impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whx.bean.Doctor;
import com.whx.bean.Period;
import com.whx.bean.Schedule;
import com.whx.dao.ScheduleDao;

public class ScheduleDaoImpl extends HibernateDaoSupport implements ScheduleDao {

	@Override
	public List<Period> findPeriods(String doctorName) {
		String sql = null;
		sql = "select p.* from doctor d join schedule s join period p "
				+ "where  s.doctor_id=d.doctor_id and s.period_id=p.period_id and d.name= :doctorName";
		@SuppressWarnings("unchecked")
		List<Period> list = getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity("p", Period.class)
				.setString("doctorName", doctorName).list();
		System.out.println("list size: " + list.size());
		return list;
	}

	@Override
	public Schedule find(String doctorId, String date, String firstPeriodBegin,
			String lastPeriodBegin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Doctor doctor, Period period) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String doctorId, String date, String beginTime,
			String endTime) {
		// TODO Auto-generated method stub
		return false;
	}

}
