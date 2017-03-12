package com.whx.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whx.bean.Doctor;
import com.whx.bean.Registration;
import com.whx.dao.RegisterOrderDao;

public class RegisterOrderDaoImpl extends HibernateDaoSupport implements
		RegisterOrderDao {

	@Override
	public void saveRegisterInDb(Registration reg) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(reg);
	}

	@Override
	public boolean selectFromRegister(String userName, String date) {
		List<Registration> list=new ArrayList<Registration>();
		list= getHibernateTemplate().find("from Registration re where re.userName=? and re.date=?",new String[]{userName,date});
		return list.size()>0?true:false;
	}

	@Override
	public void deleteRegisterById(Integer RId) {
		Registration re=(Registration) getHibernateTemplate().find("from Registration re where re.registrationId=?",RId).get(0);
		getHibernateTemplate().delete(re);	
	}

	@Override
	public List<Registration> selectRegistrationOrderByParam(
			String userName,String hospitalName, String date, String state) {
		String sql=null;
		sql="select reg.* from registration reg where reg.username=? and reg.hospital_Name like ? and reg.date like ?  and reg.state=?;"; 
		@SuppressWarnings("unchecked")
		List<Registration> list = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity("reg", Registration.class).setString(0,userName).
			   setString(1,'%'+hospitalName+'%').setString(2, '%'+date+'%').setString(3, state).list();
		return list;
	}

	@Override
	public List<Registration> selectRegistrationOrder(String userName,
			String state) {
		String sql=null;
		sql="select reg.* from registration reg where reg.username=? and reg.state=?;";
		@SuppressWarnings("unchecked")
		List<Registration> list = this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity("reg", Registration.class).setString(0,userName).
			   setString(1,state).list();
		return list;
	}

	@Override
	public int selectDoctorTimeByDoctorId(Integer doctorId, String dateTime) {
		// TODO Auto-generated method stub
		String sql= null;
		sql="select reg.* from registration reg where reg.doctor_id =? and reg.date like ?; ";
		@SuppressWarnings("unchecked")
		List<Registration> registrationCount =this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql).addEntity("reg",Registration.class).setLong(0, doctorId)
				.setString(1, '%'+dateTime+'%').list();
		if(registrationCount!=null){
			return registrationCount.size();
		}
		else{
		return 0;
		}
	}


}
