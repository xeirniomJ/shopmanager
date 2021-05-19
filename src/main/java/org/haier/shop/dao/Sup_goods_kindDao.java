package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

public interface Sup_goods_kindDao {
	/**
	 * 查询所有供货商商品大类
	 * @return
	 */
	public List<Map<String,Object>> findParNames();
	/**
	 * 根据选中的商品大类，查询对应的商品小类
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findNames(Map<String,Object> map);
}
