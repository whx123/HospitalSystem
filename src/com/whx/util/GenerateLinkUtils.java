package com.whx.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletRequest;
import com.whx.bean.User;

/** 
 * �����ʻ��������������������� 
 */  
public class GenerateLinkUtils {
	
	private static final String CHECK_CODE = "checkCode";
	/**
	 *  �����˻���������
	 */
	public static String generateActivateLink(User user){
		return "http://localhost:8080/Hospital_Guahao/activateAccount.action?userName="
				+ user.getUserName() + "&" + CHECK_CODE + "=" + generateCheckcode(user);
	}
	/** 
     * ����������������� 
     */  
	public static String generateResetPwdLink(User user){
		return "http://localhost:8080/Hospital_Guahao/resetPasswordApplyUI.action?userName="
				+user.getUserName() + "&" + CHECK_CODE + "=" + generateCheckcode(user); 
	}
	/** 
     * ������֤�ʻ���MD5У���� 
     * @param user  Ҫ������ʻ� 
     * @return ���û�����������Ϻ�ͨ��md5���ܺ��16���Ƹ�ʽ���ַ��� 
     */  
	public  static String generateCheckcode(User user) {
		// TODO Auto-generated method stub
		String userName = user.getUserName();
		String randomCode = user.getRandomCode();
		return md5(userName + ":" +randomCode);
	}
	private static String md5(String s){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("md5");
			md.update(s.getBytes());
			byte[] md5Bytes = md.digest();
			return bytes2Hex(md5Bytes);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	/** 
     * ��֤У�����Ƿ��ע��ʱ���͵���֤��һ�� 
     * @param user Ҫ������ʻ� 
     * @param checkcode ע��ʱ���͵�У���� 
     * @return ���һ�·���true�����򷵻�false 
     */  
    public static boolean verifyCheckcode(User user,ServletRequest request) {  
        String checkCode = request.getParameter(CHECK_CODE);  
        return generateCheckcode(user).equals(checkCode);  
    }  
	private static String bytes2Hex(byte[] byteArray) {
		// TODO Auto-generated method stub
		StringBuffer strBuf = new StringBuffer();
		for(int i=0;i<byteArray.length;i++){
			if(byteArray[i]>=0 && byteArray[i] < 16){
				strBuf.append("0");
			}
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}

}
