package org.haier.shop.util;

import java.io.IOException;
import java.util.Properties;

public class Load {
	public static String userName;
	public static String userPassword;
	public static String shopName;
	public static String shopTel;
	public static Integer page_size;
	public static Integer apage_size;
	public static Double distance;
	public static Integer shopManagerLevel;
	static{
		try {
			Properties p=new Properties();
			p.load(Load.class.getClassLoader().getResourceAsStream("config.properties"));
			userName=p.getProperty("userName");
			userPassword=p.getProperty("userPassword");
			shopName=p.getProperty("shopName");
			shopTel=p.getProperty("shopTel");
			page_size=Integer.parseInt(p.getProperty("pagesize"));
			apage_size=Integer.parseInt(p.getProperty("apagesize"));
			distance=Double.parseDouble(p.getProperty("distance"));
			shopManagerLevel=Integer.parseInt(p.getProperty("shopManagerLevel"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		System.out.println(Load.shopManagerLevel);
	}
}
