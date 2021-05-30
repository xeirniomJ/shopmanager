package org.haier.shop.service;

import org.haier.shop.util.NoteResult;

public interface ManagerService {
	/**
	 * 登录检查
	 * 登录账号@param manager_account
	 * 登录密码@param manager_pwd
	 * @return
	 */
	public	 NoteResult login(String manager_account,String manager_pwd);
	
	/**
	 * 新管理员注册
	 */
	public NoteResult register(String manager_account,String manager_pwd,String manager_phone,String manager_name);
	/**
	 * 根据管理员查询其管理的店铺
	 */
	public NoteResult shops_uniquefind(String manager_unique,String shop_unique);
	/**
	 * 更新店铺信息
	 * @param manager
	 * @param manager_account
	 * @param manager_pwd
	 * @return
	 */
	public NoteResult updatePwd(String old_pwd,String manager_account,String manager_pwd);
	/**
	 * 查询管理员所有店铺的前端管理信息
	 * @param manager_unique
	 * @return
	 */
	public NoteResult findShopManagers(String manager_unique);
	/**
	 * 更新店铺管理员响应信息
	 * @param manager_unique
	 * @param manager_name
	 * @param manager_phone
	 * @return
	 */
	public NoteResult updateManagerMessage(String manager_unique,String manager_name,String manager_phone);
	/**
	 * 根据管理员编号，查询管理员信息
	 * @param manager_unique
	 * @return
	 */
	public NoteResult findManagerMessage(String manager_unique,String manager_token);
	/**
	 * 超级管理员查询所有管理人员
	 * @param manager_unique
	 * @param manager_token
	 * @return
	 */
	public NoteResult findManagers(String manager_unique,String manager_token);
	/**
	 * 超级管理员更新管理员功能
	 * @param supmanager_unique
	 * @param manager_name
	 * @param manager_token
	 * @param manager_account
	 * @param manager_phone
	 * @param manager_unique
	 * @param manager_level
	 * @param power_createManager
	 * @param power_modifyFunction
	 * @param power_examShop
	 * @param power_examSup
	 * @param power_forbidCus
	 * @return
	 */
	public NoteResult updateManagerDetail(String manager_unique,String manager_name,String manager_token,String manager_account
			,String manager_phone,String submanager_unique,String manager_level,String power_createManager,String power_modifyFunction,String power_examShop
			,String power_examSup,String power_forbidCus);
	/**
	 * 根据管理员编号，查询管理员信息
	 * @param manager_unique
	 * @param manager_token
	 * @param submanager_unique
	 * @return
	 */
	public NoteResult queryManagerMessage(String manager_unique,String manager_token,String submanager_unique);
}
