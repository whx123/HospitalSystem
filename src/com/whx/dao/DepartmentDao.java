package com.whx.dao;

import java.util.List;

import com.whx.bean.Department;

public interface DepartmentDao {
	public List<Department> queryDepartmentByHospitalId(Integer hospitalId);

}
