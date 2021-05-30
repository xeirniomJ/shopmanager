package org.haier.shop.service;

import org.haier.shop.util.NoteResult;

public interface StockService {
	/**
	 * 库存管理界面
	 * 根据输入条件，查询满足条件的商品
	 */
	public NoteResult findGoodss(String shop_unique,String goodsmessage,String manager_unique);
}
