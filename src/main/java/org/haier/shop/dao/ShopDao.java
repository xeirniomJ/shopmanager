package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

public interface ShopDao {
	/**
	 * 注册新管理员,查询是否已有
	 */
	public List<Map<String,Object>> findShop(Map<String,Object> map);
	/**
	 * 登录检查
	 */
	public List<Map<String,Object>> login(Map<String,Object> map);
	/**
	 * 根据店铺所在位置，查询其周边30KM范围内所有供货商
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findPurchases(Map<String,Object> map);
	/**
	 * 添加新的商铺
	 * @param map
	 * @return
	 */
	public int newShop(Map<String,Object> map);
	/**
	 * 更新店铺信息
	 * @param map
	 * @return
	 */
	public int updateShopDetail(Map<String,Object> map);
	/**
	 * 
	 * 删除前端商铺管理员
	 * @param map
	 * @return
	 */
	public int deleteManager(Map<String,Object> map);
	/**
	 * 查询店铺申请列表
	 * 查询申请店铺详情，用于审核和更新
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findShopApplys(Map<String,Object> map);
	/**
	 * 查询店铺及其管理员信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findShopDetails(Map<String,Object> map);
}
