package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

public interface Std_goodsDao {
	/**
	 * 查询所有产品信息
	 */
	public List<Map<String,Object>>  findgoods(Map<String,Object> map);
	/**
	 * 更新产品信息
	 */
	public int update(Map<String,Object> map);
	/**
	 * 更新产品条码
	 */
	public  int up(Map<String,Object> map);
}
