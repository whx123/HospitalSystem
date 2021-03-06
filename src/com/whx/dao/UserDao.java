package com.whx.dao;
import com.whx.bean.User;

public interface UserDao {
	/*
	 * 根据传入用户名和对应密码，查询到对应的用户
	 */
	public User findUser(String name,String password);
	/*
	 * 根据传入用户名，查询到对应的用户
	 */
	public User findUserByName(String Username);
	/**
	 * 判断用户名是否存在
	 * @param userName
	 * @return
	 */
	public boolean isUserNameExit(String userName); 
	/**
	 * 注册用户添加到数据库
	 * @param user
	 */
	public void addRegisterUser(User user);
	/**
	 * 根据ID查询用户
	 * @param userId
	 * @return
	 */
	public User findUserById(Integer userId);
	/**
	 * 更新用户信息
	 * @param user
	 */
	public void updateUser(User user);
}
