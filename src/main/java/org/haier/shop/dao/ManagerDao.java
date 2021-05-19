package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

public interface ManagerDao {
	/**
	 * 管理员登录查询
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findManagers(Map<String,Object> map);
	/**
	 * 管理员注册
	 */
	public int newManager(Map<String,Object> map);
	/**
	 * 查询员工管理的店铺信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> shops_uniquefind(Map<String,Object> map);
	/**
	 * 更新店铺账号密码
	 * @param map
	 * @return
	 */
	public int updateManagerMessage(Map<String,Object> map);
	/**
	 * 查询管理员所有的店铺的前端管理员信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findShopManagers(Map<String,Object> map);
	/**
	 * 更新管理员密钥
	 * @param map
	 * @return
	 */
	public int updateToken(Map<String,Object> map);
	/**
	 * 根据管理员唯一标识符，查询管理员信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryManagerDetail(Map<String,Object> map);
}
