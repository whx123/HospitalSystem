package com.whx.dao;

import java.util.List;

import com.whx.bean.Hospital;

public interface HospitalDao {
	public List queryForPage(final String hql,final int offset,final int length);
	public List queryForPage(final String hql,final int offset,final int length,String hospitalName);
	public int getAllRowCount(String hql,String hospitalName);
	public int getAllRowCount(String hql);
	public Hospital queryForHospital(final Integer id);
	
}
