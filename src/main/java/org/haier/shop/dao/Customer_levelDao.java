package org.haier.shop.dao;

import java.util.List;
import java.util.Map;


public interface Customer_levelDao {
	public List<Map<String,Object>> findCustomer_levels(Map<String,Object> map);
	/**
	 * 测试
	 */
	public List<Map<String,Object>> finds(Map<String,Object> map);
}
