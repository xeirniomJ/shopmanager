package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

import org.haier.shop.entity.Sale;

public interface Sale_listDao {
	/**
	 *	根据订单编号，查询订单详情
	 */
	public List<Map<String,Object>> findSale_lists(Map<String,Object> map);
	public int updateSale_list(Map<String,Object> map);
	/**
	 * 根据用户输入信息，查询销售订单
	 */
	public List<Map<String,Object>> findSales(Map<String,Object> map);
	/**
	 * 查询某段时间内所有产品销售的业绩
	 */
	public List<Map<String,Object>> findSales_details(Map<String,Object> map);
	/**
	 * 查询某段时间内该商铺的营业额
	 * 
	 */
	public List<Map<String,Object>> findturnover(Map<String,Object> map);
	/**
	 * 根据订单编号，查询订单详情改进
	 */
	public List<Map<String,Object>> findSale_listss(Map<String,Object> map);
	/**
	 * 生成excel表
	 */
	public List<Sale> findSaless(Map<String,Object> map);
	/**
	 * 查询所有产品一段时间内销售量
	 */
	public List<Map<String,Object>> findTotal(Map<String,Object> map);
	/**
	 * 根据员工号，查询其所管理的所有店铺的销售清单（如何排序）
	 */
	public List<Map<String,Object>> findAllSales(Map<String,Object> map);
	/**
	 * 根据员工号，查询其所管理的所有店铺的销售清单并生成excel表
	 * 
	 */
	public List<Sale> findAllSaless(Map<String,Object> map);
	/**
	 * 查询订单支付状态
	 * @return
	 */
	public List<Map<String,Object>> loadSaleListState();
	/**
	 * 查询订单处理状态
	 * @return
	 */
	public List<Map<String,Object>> loadSaleListHandleState();
}
