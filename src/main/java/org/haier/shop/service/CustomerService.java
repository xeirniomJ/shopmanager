package org.haier.shop.service;

import java.sql.Timestamp;

import org.haier.shop.util.NoteResult;

public interface CustomerService {
	/**
	 * 查询会员在某个店面的信息
	 */
	
	public NoteResult findCuss(String shop_unique,String customermessage,String manager_unique);
	/**
	 * 根据客户编号，查询详细信息用作用户信息更新
	 */
	public NoteResult findCus(String shop_unique,Integer cus_id);
	/**
	 * 根据客户编号，更新客户信息
	 * 
	 */
	public NoteResult updatecus(Integer cus_id,String cus_name,String cus_sex,String cus_alias,String cus_phone,Timestamp cus_regedit_date,Timestamp cus_birthday,String cus_email);
	/**
	 * 添加新会员
	 */
	public NoteResult newcus(String cus_account, String cus_pwd, String cus_name, String cus_sex, String cus_alias,
			String cus_phone, Timestamp cus_birthday, String cus_email);
	/**
	 * 根据客户信息，模糊查询所有满足条件客户
	 */
	public NoteResult  findCusts(String customermessage);
}
