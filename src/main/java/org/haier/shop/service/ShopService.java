package org.haier.shop.service;

import javax.servlet.http.HttpServletRequest;

import org.haier.shop.util.NoteResult;

public interface ShopService {
	/**
	 * 查询店铺周边供货商所提供的所有商品
	 * @param shop_unique
	 * @return
	 */
	public NoteResult findPurchaseGoods(String shop_unique,String sup_goods_kind_unique,String sup_goods_kind_pariuniqe,String goods_message,Integer pageNum);	
	/**
	 * 创建新的商店
	 * 
	 */
	public NoteResult newShop(String manager_unique,String account,String pwd,String shop_name,String shop_address_detail,String shop_phone);
	/**
	 * 更新店铺信息
	 * @param shop_unique
	 * @param shop_name
	 * @param shop_phone
	 * @param shop_address_detail
	 * @param manager_pwd
	 * @param request
	 * @param file
	 * @return
	 */
	public NoteResult updateShopDetail(String shop_unique,String shop_name,String shop_phone,
			String shop_address_detail,String manager_pwd,HttpServletRequest request,String shop_remark,String power_supplier);
	/**
	 * 删除店铺管理员信息
	 * @param manager_account
	 * @return
	 */
	public NoteResult deleteManager(String manager_account);
	/**
	 * 查询待审核的店铺列表
	 * @param manager_unique
	 * @param manage_token
	 * @param shop_unique
	 * @return
	 */
	public NoteResult findShopApplys(String manager_unique,String manager_token);
	/**
	 * 根据店铺编号，查询店铺及其管理员信息
	 * @param manager_unique
	 * @param manager_token
	 * @param shop_unqieu
	 * @return
	 */
	public NoteResult findShopDetail(String manager_unique,String manager_token,String shop_unique);
	/**
	 * 管理员更新店铺审核状态
	 * @param manager_unique
	 * @param manager_token
	 * @param shop_unique
	 * @param examinestatus
	 * @param examinestatus_reason
	 * @return
	 */
	public NoteResult updateExamineStatus(String manager_unique,String manager_token,String shop_unique,Integer examinestatus,String examinestatus_reason);
	/**
	 * 查询店铺信息
	 * @param manager_unique
	 * @param manager_token
	 * @param examinestatus
	 * @param message
	 * @param area_dict_num
	 * @return
	 */
	public NoteResult findShopDetails(String manager_unique,String manager_token,Integer examinestatus,String message,Integer pageNum,String parea_dict_num,String carea_dict_num,String area_dict_num);
	/**
	 * 提交审核不通过原因，并修改其审核状态
	 * @param manager_unique
	 * @param manager_token
	 * @param examinestatus
	 * @param shop_unique
	 * @param examinestatus_reason
	 * @return
	 */
	public NoteResult notPassExamine(String manager_unique,String manager_token,String shop_unique,String examinestatus_reason);
}
