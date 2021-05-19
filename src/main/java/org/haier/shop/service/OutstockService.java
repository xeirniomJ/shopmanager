package org.haier.shop.service;

import java.sql.Timestamp;

import org.haier.shop.util.NoteResult;

public interface OutstockService {
	/***
	 *	根据输入条件，查询满足条件的出库单
	 */
	public NoteResult findOutstocks(String shop_unique,Timestamp startDate,Timestamp endDate);
	/**
	 * 根据点击的出库单号，查询该出库单对应的详情
	 */
	public NoteResult  findOutstock(String outstock_unique);
}
