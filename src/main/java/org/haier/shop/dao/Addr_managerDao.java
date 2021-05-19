package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

/**
 * 送货地址详情管理Dao接口
 * @author Administrator
 *
 */
public interface Addr_managerDao {
	/**
	 * 查询某位会员的送货地址详情
	 */
	public List<Map<String,Object>> findAddr_details(Map<String,Object> map);
	/**
	 * 查询单个送货地址详细信息，以便修改
	 */
	public  List<Map<String,Object>> findAddr_detail(Map<String,Object> map);
	/**
	 * 更新会员送回地址信息
	 */
	public int updateAddr_detail(Map<String,Object> map);
	/**
	 * 更新某会员的所有地址未非默认地址
	 * @param map
	 * @return 更新数量
	 */
	public int updateAddr(Map<String,Object> map);
}
