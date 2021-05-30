package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

public interface SupplierDao {
	/**
	 * 查询店铺所选区域供应商信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findGoodsSuppliers(Map<String,Object> map);
	/**
	 * 查询满足条件的供货商
	 * 查询新提交申请的供货商
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findSups(Map<String,Object> map);
	/**
	 * 加载供应商详细信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> loadSupplierDetail(Map<String,Object> map);
	/**
	 * 更新供应商审核状态
	 * @param map
	 * @return
	 */
	public int updateSupplier(Map<String,Object> map);
	/**
	 * 查询满足条件的供应商信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findSuppliers(Map<String,Object> map);
}
