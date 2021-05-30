package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

public interface InstockDao {
	/**
	 * 入库查询界面，根据输入日期，查询该时间段内入库情况
	 */
	public List<Map<String,Object>> findInstocks(Map<String,Object> map);
	/**
	 * 根据入库单编号
	 * 查询入库单详情
	 */
	public List<Map<String,Object>> findInstock(Map<String,Object> map);
}
