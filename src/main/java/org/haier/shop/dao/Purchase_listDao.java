package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

import org.haier.shop.entity.Purchase;

public interface Purchase_listDao {
	/**
	 * 查询满足条件的进货单
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findPurchase_lists(Map<String,Object> map);
	/**
	 * 更新进货订单
	 * @param map
	 * @return
	 */
	public int updatepurchase_list(Map<String,Object> map);
	/**
	 * 查询周期内店铺进货情况
	 */
	public List<Map<String,Object>> findPurchases(Map<String,Object> map);
	/**
	 * 查询并生产execel表单
	 */
	public List<Purchase> findPurs(Map<String,Object> map);
	/**
	 * 添加新的订单
	 * @param map
	 * @return
	 */
	public int newPurchase_list(Map<String,Object> map);
	/**
	 * 查询店铺购物车详情
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findPurchaseDetails(Map<String,Object> map);
	/**
	 * 移除购物车里的商品
	 * @param map
	 * @return
	 */
	public int rmCartGoods(Map<String,Object> map);
	/**
	 * 将购物车订单转换成普通订单
	 * @param map
	 * @return
	 */
	public int newPurchaselist(Map<String,Object> map);
}
