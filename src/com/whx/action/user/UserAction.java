package com.whx.action.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.whx.bean.User;
import com.whx.service.UserService;
/*
 * ����ע�ᣬ�û���Ϣ��ѯ��
 */
public class UserAction extends ActionSupport {
	UserService userService;
	User user;
	private String result;
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String execute(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userName=(String) session.getAttribute("userName");
		user =userService.findUserByUserName(userName);
		if(user==null)
			return ERROR;
		else 
			return SUCCESS;
	}
	public String forwardToReg(){
		return SUCCESS;
	}
	public String executeUserLogin(){
		 HttpServletRequest request=
				ServletActionContext.getRequest();
		 String userName=request.getParameter("userName");
		 String password=request.getParameter("password");
		 User u= userService.findUser(userName, password);
		 Map<String, Object> map = new HashMap<String,Object>();
		 if(u!=null){
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("userName", u.getUserName());
				map.put("userName", u.getUserName());
				map.put("loginTip","��¼�ɹ�");
			// return SUCCESS;
		 }
		 else 
		 {
			map.put("loginTip", "�Ñ������������") ;
		 }
		 JSONObject json = JSONObject.fromObject(map);
		 result = json.toString();
		 return SUCCESS;
	}

}
