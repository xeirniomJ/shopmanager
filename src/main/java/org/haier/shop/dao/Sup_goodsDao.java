package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

public interface Sup_goodsDao {
	/**
	 * 根据传入的信息，查询满足条件的供货商提供的商品
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findSupGoods(Map<String,Object> map);
	/**
	 * 提交购物车订单时，查询供应商提供的产品价格
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findSupGoodsPrice(Map<String,Object> map);
}
