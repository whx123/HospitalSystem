package com.whx.dao.Impl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.whx.dao.HospitalDao;
import com.whx.bean.Hospital;

public class HospitalDaoImpl extends HibernateDaoSupport implements HospitalDao {
	
	public List queryForPage(final String hql,final int offset,final int length){
		List list = getHibernateTemplate().executeFind(new HibernateCallback(){
		public Object doInHibernate(Session session) throws HibernateException,SQLException{
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(length);
	    List list = query.list();
		return list;}
		});
		return list;
	}

	public List queryForPage(final String hql,final int offset,final int length,final String hospitalName){
		List list = getHibernateTemplate().executeFind(new HibernateCallback(){
		public Object doInHibernate(Session session) throws HibernateException,SQLException{
		Query query = session.createQuery(hql);
		query.setString(0, hospitalName);
		query.setFirstResult(offset);
		query.setMaxResults(length);
	    List list = query.list();
		return list;}
		});
		return list;
	}

	public int getAllRowCount(String hql,String hospitalName){
		return getHibernateTemplate().find(hql,hospitalName).size();
	}
	

	public int getAllRowCount(String hql) {
		return getHibernateTemplate().find(hql).size();
	}
	
	@Override
	public Hospital queryForHospital(Integer id) {
		String hql="from Hospital as h where h.hospitalId=?";
		List<Hospital> list = getHibernateTemplate().find(hql, id);
		return list.get(0);
	}

}
