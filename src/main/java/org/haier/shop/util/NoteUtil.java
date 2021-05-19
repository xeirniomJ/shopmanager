package org.haier.shop.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class NoteUtil {
	/**
	 * ����һ�����ƺ�
	 * @return
	 */
	public static String createToken(){
		String str = createId();
		return str.replaceAll("-", "");
	}
	
	/**
	 * ����UUID�㷨����һ��Ψһ�Ե��ַ���
	 * @return ����ֵid
	 */
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();
		return id;
	}
	
	/**
	 * ��msg����MD5�㷨����,����һ��String���?
	 * @param msg ����
	 * @return ����
	 */
	public static String md5(String msg){
		try{
			MessageDigest md = 
				MessageDigest.getInstance("MD5");
			//ԭʼ��Ϣinput
			byte[] input = msg.getBytes();
			//������Ϣoutput
			byte[] output = md.digest(input);//���ܴ���
			//����Base64����������outputת��String�ַ���
			String s = Base64.encodeBase64String(output);
			return s;
		}catch(Exception ex){
			System.out.println("md5����ʧ��");
			return null;
		}
	}
	
	public static void main(String[] args){
//		System.out.println(createId());
//		System.out.println(createId());
		System.out.println(md5("222"));
//		System.out.println(md5("123456789ffasd"));
	}
	
}


