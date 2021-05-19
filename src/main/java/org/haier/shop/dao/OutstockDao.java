package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

public interface OutstockDao {
	/**
	 * 查询某个店铺满足条件的出库单
	 */
	public List<Map<String,Object>>  findOutstocks(Map<String,Object> map);
	/**
	 * 根据出库单编号
	 */
	public List<Map<String,Object>>  findOutstock(Map<String,Object> map);
}
