package com.whx.action.register;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.whx.service.UserService;
//����ע���action
public class RegisterInformationTipAction extends ActionSupport{
	private static final int BUFFER_SIZE = 16 * 1024;
	private File myFile; //�ϴ��ļ�
	private String  myFileFileName;  //�ϴ��ļ���
	private String imageFileName;
	private  UserService userService;
	private String tip;
	private String passwordTip;  //��ʾ�����Ƿ���ȷ
	private String rePasswordTip;  //��ʾ���������Ƿ�һ�� 
	private String realNameTip; //��ʾ��ʵ������ʽ�Ƿ���ȷ
	private String idCardTip;  //��ʾ���֤��ʽ�Ƿ���ȷ
	private String addressTip; //��ʾ��ַ�Ƿ���ȷ
	private String telephoneTip; //��ʾ�ֻ������Ƿ���ȷ
	private String verifyCodeTip; //��ʾ��֤���Ƿ���ȷ
	private String myFileTip; //��ʾ�û��Ƿ���ѡ���ļ�
	public String getVerifyCodeTip() {
		return verifyCodeTip;
	}
	public String getMyFileTip() {
		return myFileTip;
	}
	public void setMyFileTip(String myFileTip) {
		this.myFileTip = myFileTip;
	}
	public void setVerifyCodeTip(String verifyCodeTip) {
		this.verifyCodeTip = verifyCodeTip;
	}
	public String getRealNameTip() {
		return realNameTip;
	}
	public String getTelephoneTip() {
		return telephoneTip;
	}
	public void setTelephoneTip(String telephoneTip) {
		this.telephoneTip = telephoneTip;
	}
	public String getAddressTip() {
		return addressTip;
	}
	public void setAddressTip(String addressTip) {
		this.addressTip = addressTip;
	}
	public String getIdCardTip() {
		return idCardTip;
	}
	public void setIdCardTip(String idCardTip) {
		this.idCardTip = idCardTip;
	}
	public void setRealNameTip(String realNameTip) {
		this.realNameTip = realNameTip;
	}
	public String getRePasswordTip() {
		return rePasswordTip;
	}
	public void setRePasswordTip(String rePasswordTip) {
		this.rePasswordTip = rePasswordTip;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getPasswordTip() {
		return passwordTip;
	}
	public void setPasswordTip(String passwordTip) {
		this.passwordTip = passwordTip;
	}
	public String getMyFileFileName() {
		return myFileFileName;
	}
	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	private  static void copy(File src,File dst){
		try {
			InputStream in = null ;
			OutputStream out = null ;
			try {
			in = new BufferedInputStream( new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream( new FileOutputStream(dst), BUFFER_SIZE);
			byte [] buffer = new byte [BUFFER_SIZE];
			while (in.read(buffer) > 0 ) {
			out.write(buffer);
			}
			} finally {
			if ( null != in) {
			in.close();
			}
			if ( null != out) {
			out.close();
			}
			}
			} catch (Exception e) {
			e.printStackTrace();
			}
			}
	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		System.out.println("pos****"+pos);
		 return fileName.substring(pos);
		}
	public String execute() {
		imageFileName = new Date().getTime() + getExtention(myFileFileName);
		File imageFile = new File(ServletActionContext.getServletContext().getRealPath("uploadImages" ) + "/" + imageFileName);
		copy(myFile, imageFile);	
		return SUCCESS;
	}
	public String isUserNameFit(){
		String userName=ServletActionContext.getRequest().getParameter("userName");
		System.out.println("�û���"+userName);
		if(userName==null||userName.trim().length()==0){
			setTip("�û�������Ϊ��");
		}
		else if(userService.isUserNameExit(userName)){
			setTip("���û��Ѵ���");
		}
		else if(!userName.matches("[a-zA-Z0-9]{3,11}")){
			setTip("�û���ֻ����3-12λ���ֻ���ĸ���");
		} 
		else{
			setTip("��");
		}
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(tip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	public String isPasswordFit(){
		String password=ServletActionContext.getRequest().getParameter("password");
		System.out.println("����"+password);
		if(password==null||password.trim().length()==0){
			this.setPasswordTip("���벻��Ϊ��");
		}
		else if(!password.matches("^.{6,20}$")){
			this.setPasswordTip("�û���ֻ����6-12λ���ֻ���ĸ���");
		}
		else{
			this.setPasswordTip("��");
		}
			try {
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().print(passwordTip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		return null;
	}
	public String isTheTwoPasswordsSame(){
		String password=ServletActionContext.getRequest().getParameter("password");
		String ackpassword=ServletActionContext.getRequest().getParameter("ackpassword");
		System.out.println("ȷ������"+ackpassword);
		if(ackpassword==null||ackpassword.trim().length()==0){
			this.setRePasswordTip("ȷ�����벻��Ϊ��");
		}
		else if(!ackpassword.equals(password)){
			this.setRePasswordTip("�������벻һ��");
		}
		else{
			this.setRePasswordTip("��");
		}
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(rePasswordTip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String isRealNameFormatRight(){
		String realName=ServletActionContext.getRequest().getParameter("realName");
		if(realName==null||realName.trim().length()==0){
			this.setRealNameTip("��ʵ��������Ϊ��");
		}
		else if(!realName.matches("[\u4e00-\u9fa5]{2,4}")){
			this.setRealNameTip("��ʵ����ֻ�����������ĸ����������");
		}
		else {
			this.setRealNameTip("��");
		}
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(realNameTip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String isIDCardFormatRight(){
		String IDCard=ServletActionContext.getRequest().getParameter("IDCard");
		if(IDCard==null||IDCard.trim().length()==0){
			this.setIdCardTip("���֤����Ϊ��");
		}
		else if(!IDCard.matches("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])")){
			this.setIdCardTip("������15��18λ���֤����");		
		}
		else {
			this.setIdCardTip("��");
		}
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(idCardTip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String isAddressFormatRight(){
		String address=ServletActionContext.getRequest().getParameter("address");
		if(address==null||address.trim().length()==0){
			this.setAddressTip("��ַ����Ϊ��");
		}
		else{
			this.setAddressTip("��");	
		}
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(addressTip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String isTelephoneFormatRight(){
		String telephone=ServletActionContext.getRequest().getParameter("telephone");
		if(telephone==null||telephone.trim().length()==0){
			this.setTelephoneTip("�ֻ����벻��Ϊ��");
		}
		else if(!telephone.matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")){
			this.setTelephoneTip("�ֻ������ʽ����ȷ");
		}
		else{
			this.setTelephoneTip("��");	
		}
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(telephoneTip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String isVerifyCodeRight(){
		String verifyCode=ServletActionContext.getRequest().getParameter("verifyCode");
		HttpSession session=ServletActionContext.getRequest().getSession();
		if(verifyCode==null||verifyCode.length()==0){
			this.setVerifyCodeTip("��֤�벻��Ϊ��");
		}
		else if(!session.getAttribute("securityCode").equals(verifyCode)){
			this.setVerifyCodeTip("��֤�벻��ȷ");
		}
		else{
			this.setVerifyCodeTip("��");
		}
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(verifyCodeTip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String isFileNotNull(){
		String myFileName=ServletActionContext.getRequest().getParameter("myFileName");
		System.out.print("�ļ���******"+myFileName);
		String suffixName="";
		if(!(myFileName==null||myFileName.length()==0)){
		 suffixName=myFileName.substring(myFileName.lastIndexOf("."));
		}	
		if(myFileName==null||myFileName.length()==0){
			this.setMyFileTip("���ϴ�ͷ��");
			
		}
		else if(!suffixName.matches(".(jpg|png|gif|PNG)")){
			
			this.setMyFileTip("ͼƬ��׺������");
		}
		else {
			System.out.print("�ļ���--------"+myFileName);
			this.setMyFileTip("��");
		}
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(myFileTip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
