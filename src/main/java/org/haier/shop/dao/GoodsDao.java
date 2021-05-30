package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

import org.haier.shop.entity.Goods;

public interface GoodsDao {
	public List<Goods> findGoods(Map<String,Object> map);
	/**
	 * 添加新商品
	 * @param map
	 * @return
	 */
	public int newGoods(Map<String, Object> map);
	public List<Map<String,Object>> findGoods1(Map<String,Object> map);
	public List<Map<String,Object>> findGood(Map<String,Object> map);
	/**
	 * 更新商品促销状态
	 */
	public Integer updateGoods_promotion(Map<String,Object> map);
//	/**
//	 * 模糊查询测试
//	* 测试通过 
//	 */
//	public List<Map<String,Object>> find(Map<String,Object> map);
	public List<Map<String,Object>> test(Map<String,Object> map);
	/**\
	 * 库存管理界面，根据输入产品信息，查询该产品的库存信息
	 */
	public List<Map<String,Object>> findGoodss(Map<String,Object> map);
	/**
	 * 更新商品
	 */
	public int updateGoods(Map<String,Object> map);
	/**
	 * 查询商品id,临时
	 */
	public List<Integer> slid(Map<String,Object> map);
	/**
	 * 查询某一范围内的产品
	 */
	public List<Map<String,Object>> findGoods2(Map<String,Object> map);
	/**
	 * 删除不销售的产品
	 * @param map
	 * @return
	 */
	public int deleteGoods(Map<String,Object> map);
	/**
	 * 木湖查询测试
	 */
	public  List<Map<String,Object>> likeQuery(Map<String,Object> map);
	/**
	 * 模糊查询联合
	 */
	public List<Map<String,Object>> likeUnion(Map<String,Object> map);
}
