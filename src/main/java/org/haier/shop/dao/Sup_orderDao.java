package org.haier.shop.dao;

import java.util.Map;

public interface Sup_orderDao {
	/**
	 * 创建新的供货商订单
	 * @param map
	 * @return
	 */
	public int newSupOrder(Map<String,Object> map);
}
