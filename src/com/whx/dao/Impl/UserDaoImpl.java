package com.whx.dao.Impl;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whx.bean.User;
import com.whx.dao.UserDao;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	@Override
	public User findUser(String name, String password) {
		//用户名，密码做参数
		String []param=new String[]{name,password};
		String sql="from User as u where u.userName=? and u.userPwd=?";
		List<User>list=getHibernateTemplate().find(sql, param);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		else return null;
	}

	@Override
	public User findUserByName(String userName) {
		String sql="from User as u where u.userName=?";
		List<User> list = getHibernateTemplate().find(sql, userName);
		if (list != null && list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public boolean isUserNameExit(String userName) {
		String sql="from User as u where u.userName=?";
		List<User> list = getHibernateTemplate().find(sql, userName);
		if(list.size()>0){
			return true;
		}
		else return false;
	}

	@Override
	public void addRegisterUser(User user) {
		getHibernateTemplate().save(user);	
	}

	@Override
	public User findUserById(Integer userId) {
		// TODO Auto-generated method stub
		String sql="from User as u where u.userId=?";
		List<User>list=getHibernateTemplate().find(sql,userId);
		if(list.size()>0){
			return list.get(0);
		}else{
		  return null;
		}
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(user);
	}

}
