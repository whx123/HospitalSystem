package com.whx.action.hospitalInit;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.whx.dao.RegisterOrderDao;
import com.whx.bean.Registration;

public class RegisterAction implements Action {
	private Registration registeration;
	private RegisterOrderDao registerOrderDao;
	private String tip3;
	private String doctorName;
	private Integer doctorId;
	private String time;
	private String date;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		registeration=new Registration();
		HttpServletRequest request=ServletActionContext.getRequest();
		//接受参数
		String regfee = request.getParameter("regfee");
		String departName =  new String(request.getParameter("departmentName").getBytes("iso-8859-1"),"utf-8");
		String hospitalName =  new String(request.getParameter("hospitalName").getBytes("iso-8859-1"),"utf-8");
		String name = (String) request.getSession().getAttribute("userName");
		//转换编码格式
		date = new String(request.getParameter("date").getBytes("iso-8859-1"),"utf-8");
		doctorName = new String(request.getParameter("doctorName").getBytes("iso-8859-1"),"utf-8");
		time = request.getParameter("time");
		doctorId=Integer.parseInt(request.getParameter("doctorId"));
		//更新挂号单信息
		registeration.setDate(date+time);
		registeration.setDoctorId(doctorId);
		registeration.setDoctorName(doctorName);
		registeration.setHospitalName(hospitalName);
		registeration.setRegfee(Integer.parseInt(regfee));
		registeration.setUserName(name);
		registeration.setDepartmentName(departName);
		registeration.setState("未付款");
		registeration.setWay("网站");
		//判断是否已经提交了该时间段的挂号单
		if (registerOrderDao.selectFromRegister(registeration.getUserName(),registeration.getDate())) {
			tip3="你已经提交了该时间段的订单！！";
			return INPUT;
		} else {
			registerOrderDao.saveRegisterInDb(registeration);
			return SUCCESS;
		}
	}
	public Registration getRegisteration() {
		return registeration;
	}
	public void setRegisteration(Registration registeration) {
		this.registeration = registeration;
	}
	public String getTip3() {
		return tip3;
	}
	public void setTip3(String tip3) {
		this.tip3 = tip3;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public RegisterOrderDao getRegisterOrderDao() {
		return registerOrderDao;
	}
	public void setRegisterOrderDao(RegisterOrderDao registerOrderDao) {
		this.registerOrderDao = registerOrderDao;
	}

}
