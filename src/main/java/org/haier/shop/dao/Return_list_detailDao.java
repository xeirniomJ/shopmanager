package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

import org.haier.shop.entity.Return_list_detail;

public interface Return_list_detailDao {
	/**
	 * 查询退货订单详情
	 * @param map
	 * @return
	 */
	public List<Return_list_detail> findReturn_list_details(Map<String,Object> map);
}
