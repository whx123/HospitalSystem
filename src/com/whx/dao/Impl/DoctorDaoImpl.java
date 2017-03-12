package com.whx.dao.Impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whx.bean.Doctor;
import com.whx.bean.Registration;
import com.whx.dao.DoctorDao;

public class DoctorDaoImpl extends HibernateDaoSupport implements DoctorDao {

	@Override
	public List<Doctor> queryDoctorByHospitalIdAndDepartName(
			Integer hospitalId, String departmentName,int startLine,int pageSize) {
		// TODO Auto-generated method stub
		String sql=null;
		sql="select doct.* from doctor doct left join hospital hos  on doct.hospital_id=hos.hospital_id left join department dpt on doct.department_id=dpt.department_id where doct.hospital_id=? and dpt.name=?"; 
		@SuppressWarnings("unchecked")
		List<Doctor> list = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity("doct", Doctor.class).
			   setLong(0,hospitalId).setString(1, departmentName).setFirstResult(startLine).setMaxResults(pageSize).list();
		System.out.print(list.size());
		return list;
	}

	@Override
	public Doctor findDoctorById(Integer doctorId) {
		String sql="select doct.* from doctor doct where doct.doctor_id=?";
		@SuppressWarnings("unchecked")
		List<Doctor> doctor = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity("doct", Doctor.class)
				.setLong(0, doctorId).list();
		return doctor.size() > 0 ? doctor.get(0) : null;
	}

	@Override
	public List<Doctor> queryDoctorByIllness(String illNess) {
		String sql = "select doct.* from doctor doct where doct.speciality like ? ";
		List<Doctor> list = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity("doct", Doctor.class)
				.setString(0, '%'+illNess+'%').list();
		return list;
	}
	@Override
	public List<Doctor> queryDoctorByDoctorName(String doctorName) {
		String sql = "select doct.* from doctor doct where doct.name like ? ";
		List<Doctor> list = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity("doct", Doctor.class)
				.setString(0, '%'+doctorName+'%').list();
		return list;
	}

}
