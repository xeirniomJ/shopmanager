package org.haier.shop.util;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendMsg_webchineseUtil {
	public static String sendMessage(String userName,String userPassword,String MobNums,String msg){
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");// ��ͷ�ļ�������ת��
		NameValuePair[] data = { new NameValuePair("Uid", userName), 
				new NameValuePair("Key", userPassword),
				new NameValuePair("smsMob", MobNums), 
				new NameValuePair("smsText", msg+"����������") };
		post.setRequestBody(data);

		try {
			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			System.out.println("statusCode:" + statusCode);
			for (Header h : headers) {
				System.out.println(h.toString());
			}
			return new String(post.getResponseBodyAsString().getBytes("utf-8"));
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			post.releaseConnection();
		}
		return "0";
	}
	public static String sendNum(String userName,String userPassword){
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/SMS/?Action=SMS_Num");
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");// ��ͷ�ļ�������ת��
		NameValuePair[] data = { new NameValuePair("Uid", userName), 
				new NameValuePair("Key", userPassword)};
		post.setRequestBody(data);

		try {
			client.executeMethod(post);
			int statusCode = post.getStatusCode();
			System.out.println("statusCode:" + statusCode);
			return new String(post.getResponseBodyAsString().getBytes("utf-8"));
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			post.releaseConnection();
		}
		return "ͨѶ�쳣";
	}			
	public static void main(String[] args) {
//		System.out.println(sendMessage("qdwel1986","733ae21a6516e42dbd57","15192885173","�������"));
		System.out.println("����ʣ������Ϊ��"+sendNum("qdwel1986","733ae21a6516e42dbd57"));
	}
}
