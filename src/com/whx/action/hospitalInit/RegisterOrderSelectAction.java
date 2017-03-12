package com.whx.action.hospitalInit;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.whx.dao.BookingDao;
import com.whx.dao.RegisterOrderDao;
import com.whx.service.UserService;
import com.whx.bean.Registration;
import com.whx.bean.User;

public class RegisterOrderSelectAction implements Action {
	private BookingDao bookingDao;
	private List<Registration> re;
	private RegisterOrderDao registerOrderDao;
	private User user;
	private UserService userService;
	private String hospitalName;
	private String date;
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	private String tip;
	public BookingDao getBookingDao() {
		return bookingDao;
	}
	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}
	public List<Registration> getRe() {
		return re;
	}
	public void setRe(List<Registration> re) {
		this.re = re;
	}
	public RegisterOrderDao getRegisterOrderDao() {
		return registerOrderDao;
	}
	public void setRegisterOrderDao(RegisterOrderDao registerOrderDao) {
		this.registerOrderDao = registerOrderDao;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		re=new ArrayList<Registration>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String userName=(String) request.getSession().getAttribute("userName");
		//��ѯ���û�������ԤԼ�Һŵ�
		re=bookingDao.findRegistrationByUserName(userName);
		return SUCCESS;
	}
	public String deleteRegistrtion()throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String RId=request.getParameter("RId");
		registerOrderDao.deleteRegisterById(Integer.parseInt(RId));
		tip="����"+RId+"ɾ���ɹ�";
		return SUCCESS;
	}
	//�ɷѹ����ɾ������
	public String deleteRegistrtionPay()throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String RId=request.getParameter("RId");
		registerOrderDao.deleteRegisterById(Integer.parseInt(RId));
		String userName=(String) request.getSession().getAttribute("userName");
		user=userService.findUserByUserName(userName);
		tip="����"+RId+"ɾ���ɹ�";
		String state=request.getParameter("state");
		state=new String(state.getBytes("iso-8859-1"),"utf-8");
		re=registerOrderDao.selectRegistrationOrder(userName, state);
		return SUCCESS;
	}
	
	public String selectRegistrationOrder() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String userName=(String) request.getSession().getAttribute("userName");
		user=userService.findUserByUserName(userName);
		hospitalName=request.getParameter("hospitalName");
		String state=request.getParameter("state");
		hospitalName =new String(hospitalName.getBytes("iso-8859-1"),"utf-8");
		state=new String(state.getBytes("iso-8859-1"),"utf-8");
		if(state==null){
			state="δ����";
		}
	    date=request.getParameter("date");
		System.out.println("����"+date);
		re=registerOrderDao.selectRegistrationOrderByParam(userName, hospitalName, date, state);
		System.out.println("���"+re);
		return SUCCESS;
	}
	public String selectRegistrationOrderByState() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		//�û���Ϣ
		String userName=(String) request.getSession().getAttribute("userName");
		user=userService.findUserByUserName(userName);
		
		String state=request.getParameter("state");
		state=new String(state.getBytes("iso-8859-1"),"utf-8");
		System.out.println("״̬"+state);
		re=registerOrderDao.selectRegistrationOrder(userName, state);
		
		return SUCCESS;
	}

}
