package org.haier.shop.dao;

import java.util.Map;

public interface Sup_exOrderDao {
	/**
	 * 插入新的供货商换货单
	 * @param map
	 * @return
	 */
	public int newExOrder(Map<String,Object> map);
}
