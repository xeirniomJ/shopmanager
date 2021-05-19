package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

public interface Exchange_listDao {
	/**
	 * 创建新的换货订单
	 * @param map
	 * @return
	 */
	public int newExchange(Map<String,Object> map);
	/**
	 * 更新退货订单
	 * @param map
	 * @return
	 */
	public int updateExchange(Map<String,Object> map);
	/**
	 * 创建新的订单详情
	 * @param map
	 * @return
	 */
	public int newExchangeDetail(Map<String,Object> map);
	/**
	 * 更新退货订单详情
	 * @param map
	 * @return
	 */
	public int updateExchangeDetails(Map<String,Object> map);
	/**
	 * 查询退货订单详情
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findExchangeDetails(Map<String,Object> map);
	/**
	 * 更新退货单商品数量
	 * @param map
	 * @return
	 */
	public int changeExCartCount(Map<String,Object> map);
	/**
	 * 测试bug用
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> tests(Map<String,Object> map);
	/**
	 * 移除购物车里的商品
	 * @param map
	 * @return
	 */
	public int rmExCartGoods(Map<String,Object> map);
}
