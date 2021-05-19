package org.haier.shop.service;

import org.haier.shop.util.NoteResult;

public interface Addr_managerService {
	/**
	 * 送货地址关键界面
	 * 根据客户编号，查询相应的送货地址信息
	 */
	public NoteResult findAddr_details(Integer cus_id);
	/**
	 * 送货地址编辑界面
	 * 根据送货地址编号，查询送货单地址详情
	 */
	public NoteResult findAddr_detail(Integer addr_id);
	/**
	 * 更新送货地址页面，更新送货地址详情
	 */
	public NoteResult updateAddr_detail(Integer addr_id,String addr_receiver_name,String addr_phone,String addr_addr,String addr_province,String addr_city,String addr_county,String addr_default,String cus_unique);
}

