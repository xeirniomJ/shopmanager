package org.haier.shop.dao;

import java.util.List;
import java.util.Map;


public interface Goods_kindDao {
	/**
	 * 查询商品大类
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findGoods_kinds(Map<String,Object> map);
	/**
	 * 插入新商品类
	 */
	public int newGoods_kind(Map<String,Object> map);
	/**
	 * 更新已有商品类
	 */
	public int modifyGoods_kind(Map<String,Object> map);
	/**
	 * 根据商品大类查询所有产品分类
	 */
	public List<Map<String,Object>> findKinds(Map<String,Object> map);
	/**
	 * 根据商品小类，查询具体商品型号
	 */
	public List<Map<String,Object>> findKs(Map<String,Object> map);
	/**
	 * 查询所有小类
	 */
	public List<Map<String,Object>> findAllNames(Map<String,Object> map);
	/**
	 * 根据商品类型编号，查询商品类型明细
	 */
	public List<Map<String,Object>> findk(Map<String,Object> map);
	/**
	 * 查询满足条件的商品分类
	 */
	public List<Map<String,Object>> finds(Map<String,Object> map);
	/**
	 * 查询管理员版商品分类
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryKinds(Map<String,Object> map);
	
	/**
	 * 添加店铺商品分类信息
	 * @param map
	 * @return
	 */
	public int deleteShopKinds(Map<String,Object> map);
	/**
	 * 
	 * @param map
	 * @return
	 */
	public int  addShopKinds(List<Map<String,Object>> list);
}
