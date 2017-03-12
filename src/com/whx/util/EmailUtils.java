package com.whx.util;

import java.io.IOException;  
import java.io.InputStream;  
import java.util.Date;  
import java.util.Properties;  
import javax.mail.Authenticator;  
import javax.mail.Message.RecipientType;  
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
import com.whx.bean.User;
public class EmailUtils {
	
	private static final String FROM = "2059657480@qq.com";
	
	/** 
     * ע��ɹ���,���û������ʻ��������ӵ��ʼ� 
     * @param user δ������û� 
     */ 
	public static void sendAccountActivateEmail(User user){
		Session session = getSession();
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		  try {  
	            message.setSubject("�Һ�ϵͳ�ʻ������ʼ�");  
	            message.setSentDate(new Date());  
	            message.setFrom(new InternetAddress(FROM));  
	            message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));  
	            message.setContent("<a href='" + GenerateLinkUtils.generateActivateLink(user)+"'>�������Һ�ϵͳ�ʻ�</a>","text/html;charset=utf-8");  
	            // �����ʼ�  
	            Transport.send(message);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    } 
	 /** 
     * ���������������ӵ��ʼ� 
     */  
    public static void sendResetPasswordEmail(User user) {  
        Session session = getSession();  
        MimeMessage message = new MimeMessage(session);  
        try {  
            message.setSubject("�һ������ʻ�������");  
            message.setSentDate(new Date());  
            message.setFrom(new InternetAddress(FROM));  
            message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));  
            message.setContent("Ҫʹ���µ�����, ��ʹ������������������:<br/><a href='" + GenerateLinkUtils.generateResetPwdLink(user) +"'>���������������</a>","text/html;charset=utf-8");  
            // �����ʼ�  
            Transport.send(message);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    public static Session getSession() {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", "smtp.qq.com");
		props.setProperty("mail.smtp.port", "25");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(FROM, "yxmrlwukrszdbcia");
			}
			
		});
		return session;
	}
	
}
