package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

import org.haier.shop.entity.Return;
import org.haier.shop.entity.Return_list;

public interface Return_listDao {
	public List<Return_list> findReturn_lists(Map<String,Object> map);
	public int updateReturn_list_detail(Map<String,Object> map);
	/**
	 * 根据输入的信息，查询满足条件的订单
	 */
	public List<Map<String,Object>> findUnDoRets(Map<String,Object> map);
	/**
	 * 根据订单编号查询订单详情
	 */
	public List<Map<String,Object>> finddetails(Map<String,Object> map);
	/**
	 * 生成excel表
	 */
	public List<Return> findReturns(Map<String,Object> map);
}
