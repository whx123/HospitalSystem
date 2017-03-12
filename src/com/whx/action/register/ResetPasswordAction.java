package com.whx.action.register;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Action;
import com.whx.bean.User;
import com.whx.service.UserService;
import com.whx.util.EmailUtils;

public class ResetPasswordAction implements Action {
	
	private String userName;
	private String email;
	private UserService userService;
    private String sendEmailResetTip;
    private String newPassword;
    private String newRePassword;
	public String getNewRePassword() {
		return newRePassword;
	}
	public void setNewRePassword(String newRePassword) {
		this.newRePassword = newRePassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getSendEmailResetTip() {
		return sendEmailResetTip;
	}
	public void setSendEmailResetTip(String sendEmailResetTip) {
		this.sendEmailResetTip = sendEmailResetTip;
	}
	public UserService getUserService() {
		return userService;
	}
   public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String execute() throws Exception {
		
		User user = userService.findUserByUserName(userName);
		if(user==null){
			sendEmailResetTip="���û���������";
			return INPUT;
		}
		else if(user!=null && !user.getEmail().equals(email)){
			sendEmailResetTip="���䲻��ע��ʱ����";
			return INPUT;
		}
		else {
			EmailUtils.sendResetPasswordEmail(user);
			this.sendEmailResetTip="����������ύ����鿴�������"+user.getEmail();
			return SUCCESS;
			
		}
	}
	
	public String resetPasswordApplyUI(){
		User user = userService.findUserByUserName(userName);
		System.out.print("��������"+user);
		HttpServletRequest request = ServletActionContext.getRequest();
	    return SUCCESS;
		
	}
	public String resetPassword(){
		Map<String,String> errors = new HashMap<String,String>();
		if(newPassword==null|| "".equals(newPassword)){
			errors.put("newPassword", "�����벻��Ϊ��");
		}
		if(newRePassword == null || "".equals(newRePassword)){
			errors.put("newRePassword", "ȷ�����벻��Ϊ��");
		}
		if (!newPassword.equals(newRePassword)) {
			errors.put("passwordError", "������������벻һ�£�");
		}
		if(!errors.isEmpty()){
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("errors", errors);
				return INPUT;	
		}
		User user = userService.findUserByUserName(userName);
		user.setUserPwd(newPassword);
		userService.updateUser(user);
		return SUCCESS;
	}
	

}
