package org.haier.shop.dao;

import java.util.List;
import java.util.Map;


public interface Purchase_list_detailDao {
	/**
	 * 根据订单编号，查询订单详情
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findPurchase_list_details(Map<String,Object> map);
	/**
	 * 添加新的订单详情
	 * @param map
	 * @return
	 */
	public int newPurchase_list_detail(Map<String,Object> map);
	/**
	 * 更新订单详情
	 * @param map
	 * @return
	 */
	public int updateCartDetail(Map<String,Object> map);
	/**
	 * 创建新的店铺订单详情
	 * @param map
	 * @return
	 */
	public int newCartDetail(Map<String,Object> map);
}
