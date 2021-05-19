package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

public interface Area_dictDao {
	/**
	 * 自动加载省份
	 */
	public List<Map<String,Object>> find_provinces();
	/**
	 * 自动加载城市
	 */
	public List<Map<String,Object>> find_cities(Map<String,Object> map);
	/**
	 * 根据县级编号，查询其省市编号及名称
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findPC(Map<String,Object> map);
}
