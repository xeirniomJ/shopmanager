package org.haier.shop.service;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.haier.shop.util.NoteResult;

public interface Purchase_listService {
	/**
	 * 根据用户选择的时间段和店铺编号，查询该店铺某段时间内的进货订单
	 * @param purchase_list_id
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public NoteResult purchase_list_find(String purchase_list_id,Date startDate,Date endDate,String shop_unique,String manager_unique);
	/**
	 * 更新进货单信息
	 * @param purchase_list_unique
	 * @param purchase_list_statue
	 * @return
	 */
	public NoteResult purchase_list_update(String purchase_list_unique,String purchase_list_statue);
	/**
	 * 查询周期内进货金额
	 */
	public NoteResult findPurchases(String shop_unique);
	/**
	 * 生成excel表
	 */
	public NoteResult findPurs(String purchase_list_id,Date startDate,Date endDate,String shop_unique,HttpServletRequest request,String manager_unique);
	/**
	 * 添加新商品订单
	 * @param shop_unique
	 * @param detailss
	 * @param purchase_list_total
	 * @return
	 */
	public NoteResult newPurchase_list(String purchase_list_unique,String details);
	/**
	 * 将商品添加到购物车
	 * @param shop_unique
	 * @param goods_barcode
	 * @param purchase_list_detail_count
	 * @param supplier_unique
	 * @param purchase_list_detail_total
	 * @return
	 */
	public NoteResult addToCart(String shop_unique,String goods_barcode,Integer purchase_list_detail_count,String supplier_unique,Double purchase_list_detail_total);
	/**
	 * 查询商品供应商及其供货价
	 * @param goods_barcode
	 * @param shop_unique
	 * @return
	 */
	public NoteResult findGoodsSuppliers(String goods_barcode,String shop_unique);
	/**
	 * 查询购物车里商品的数量
	 * @param shop_unique
	 * @return
	 */
	public NoteResult findPurCartGoods(String shop_unique);
	/**
	 * 移除购物车里的商品
	 * @param shop_unique
	 * @param goods_barcode
	 * @return
	 */
	public NoteResult rmCartGoods(String shop_unique,String goods_barcode);
	/**
	 * 购物车商品数量栏失去光标时，更新购物车产品数量
	 * @param shop_unique
	 * @param goods_barcode
	 * @param purchase_list_detail_count
	 * @param supplier_unique
	 * @return
	 */
	public NoteResult updateCart(String goods_barcode,Integer purchase_list_detail_count,String supplier_unique,String purchase_list_unique);
}
