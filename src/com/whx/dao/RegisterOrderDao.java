package com.whx.dao;

import java.util.List;

import com.whx.bean.Registration;

public interface RegisterOrderDao {
	 public void saveRegisterInDb(Registration reg);
	   public boolean selectFromRegister(String userName,String date);
	   public void deleteRegisterById(Integer RId);
	   public List <Registration> selectRegistrationOrderByParam(
			String userName,String hospitalName,String date,String state);
	   public List<Registration> selectRegistrationOrder(String userName,String state);
	   public int selectDoctorTimeByDoctorId(Integer doctorId,String dateTime);
}
