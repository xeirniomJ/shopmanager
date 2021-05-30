package org.haier.shop.service;

import java.sql.Timestamp;

import org.haier.shop.util.NoteResult;

public interface InstockService {
	/**
	 * 根据输入时间段，查询该时间段内入库单
	 */
	public NoteResult findInstocks(Timestamp startDate,Timestamp endDate,String shop_unique,String manager_unique);
	/**
	 * 根据点击的入库单编号，查询该入库单的明细
	 */
	public NoteResult findInstock(String shop_unique,Integer instock_id);
	
}
