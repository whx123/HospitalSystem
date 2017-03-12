package com.whx.dao.Impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whx.bean.Department;
import com.whx.bean.Hospital;
import com.whx.dao.DepartmentDao;

public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao {

	@Override
	public List<Department> queryDepartmentByHospitalId(Integer hospitalId) {
		String sql=null;
		sql="select dep.* from department dep where dep.hospital_id=?";
		@SuppressWarnings("unchecked")
		List<Department> departmentList=this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity("dep", Department.class)
				.setInteger(0, hospitalId).list();
		return departmentList;
	}

}
