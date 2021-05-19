package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

public interface AdjustDao {
	/**
	 * 查询所有满足条件的调货申请单
	 * @param map 参数列表
	 * @return
	 */
	public List<Map<String,Object>> adjust_find(Map<String,Object> map);
	/**
	 * 取消未被响应的调货申请
	 * @param map
	 * @return
	 */
	public int adjust_cancel(Map<String,Object> map);
	/**
	 * 添加新申请
	 * @param map
	 * @return
	 */
	public int adjust_new_save(Map<String,Object> map);
	/**
	 * 查询所有调货单状态
	 * @return
	 */
	public List<Map<String,Object>> adjust_statue_find();
	/**
	 * 查询所有调货单处理状态
	 * @return
	 */
	public List<Map<String,Object>> adjust_handlestatue_find();
	/**
	 * 查询所有订单申请类型
	 */
	public List<Map<String,Object>> adjust_type_find();
	/**
	 * 查询其他店铺调货申请
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> oadjust_find(Map<String,Object> map);
	/**
	 * 更新调货单响应状态
	 * @param map
	 * @return
	 */
	public int adjust_update(Map<String,Object> map);
	/**
	 * 查询店铺响应的调货单
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> adjust_rfind(Map<String,Object> map);
	/**
	 * 测试查询两个店铺见的距离
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> dis(Map<String,Object> map);
}
