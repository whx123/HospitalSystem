package com.whx.bean;

import java.util.Date;

public class User implements java.io.Serializable {
	// Fields
	private Integer userId;
	//�û���
	private String userName;
	//�û�����
	private String userPwd;
	//�û���ʵ����
	private String userRealname;
	private String sex;
	private String address;
	private String tel;
	private String identificationId;
	private Date birthday;
	private int balance;
    private String imageUrl;
    private String activated; //�Ƿ񼤻�����:0��ʾû���1��ʾ���
    private String randomCode; // �����(�����ʻ�������������������ʱʹ��) 
    private String email; 
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRandomCode() {
		return randomCode;
	}

	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode;
	}

	public String getActivated() {
		return activated;
	}

	public void setActivated(String activated) {
		this.activated = activated;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	// Constructors
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String userName, String userPwd, String userRealname,
			String sex, String address, String tel, String identificationId,
			Date birthday) {
		this.userName = userName;
		this.userPwd = userPwd;
		this.userRealname = userRealname;
		this.sex = sex;
		this.address = address;
		this.tel = tel;
		this.identificationId = identificationId;
		this.birthday = birthday;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserRealname() {
		return this.userRealname;
	}

	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIdentificationId() {
		return this.identificationId;
	}

	public void setIdentificationId(String iDCard) {
		this.identificationId = iDCard;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birth) {
		this.birthday = birth;
	}

}