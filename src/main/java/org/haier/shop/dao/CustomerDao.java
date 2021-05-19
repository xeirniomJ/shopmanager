package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

import org.haier.shop.entity.Customer;

public interface CustomerDao {
	/**
	 * 模糊查询，查询所有满足客户信息
	 * @param map
	 * @return
	 */
	public List<Customer> findCustomers(Map<String,Object> map);
	/**
	 * 查询每个客户对应每个店铺的基本信息及积分
	 */
	public List<Map<String,Object>> findCuss(Map<String,Object> map);
	/**
	 * 更新会员信息
	 */
	public int updatecus(Map<String,Object> map);
	/**
	 * 添加新会员
	 */
	public int newcus(Map<String,Object> map);
	/**
	 * 根据输入条件，查询单个客户信息
	 * 
	 */
	public List<Map<String,Object>> findCus(Map<String,Object> map);
	/**
	 * 依据输入条件，模糊查询所有满足条件客户
	 */
	public List<Map<String,Object>> findCusts(Map<String,Object> map);
	/**
	 * 根据输入的店面唯一标识符和客户编号，查询会员信息
	 */
	public List<Map<String,Object>> findC(Map<String,Object> map);
	
}
