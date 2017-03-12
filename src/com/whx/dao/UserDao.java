package com.whx.dao;
import com.whx.bean.User;

public interface UserDao {
	/*
	 * ���ݴ����û����Ͷ�Ӧ���룬��ѯ����Ӧ���û�
	 */
	public User findUser(String name,String password);
	/*
	 * ���ݴ����û�������ѯ����Ӧ���û�
	 */
	public User findUserByName(String Username);
	/**
	 * �ж��û����Ƿ����
	 * @param userName
	 * @return
	 */
	public boolean isUserNameExit(String userName); 
	/**
	 * ע���û���ӵ����ݿ�
	 * @param user
	 */
	public void addRegisterUser(User user);
	/**
	 * ����ID��ѯ�û�
	 * @param userId
	 * @return
	 */
	public User findUserById(Integer userId);
	/**
	 * �����û���Ϣ
	 * @param user
	 */
	public void updateUser(User user);
}
