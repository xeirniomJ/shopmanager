package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

public interface KindsDao {
	/**
	 * 查询所有一级商品分类
	 * @return
	 */
	public List<Map<String,Object>> addKindsLevel(Map<String,Object> map);
	/**
	 * 查询所有二级商品分类
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> addSLevels();
	/**
	 * 查询所有三级商品分类
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> addTLevels();
	/**
	 * 根据输入的分类名称或编号，查询下一级的商品分类信息
	 * @return
	 */
	public List<Map<String,Object>> chSLevels(Map<String,Object> map);
}
