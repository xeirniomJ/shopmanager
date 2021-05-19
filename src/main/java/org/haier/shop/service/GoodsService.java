package org.haier.shop.service;

import javax.servlet.http.HttpServletRequest;

import org.haier.shop.util.NoteResult;

public interface GoodsService {
	public NoteResult findGoods(String goodsmessage,String goods_kind_parunique,String goods_kind_unique,String shop_unique,String manager_unique);
	//查询单个商品的产品信息
	public NoteResult findGood(String goods_barcode,String shop_unique);
	/**
	 * 查询满足促销条件的商品
	 */
	public NoteResult findGoods_promotion(String goodsmessage,String goods_kind_parunique,String goods_kind_unique,String goods_promotion,String shop_unique,String manager_unique);
	/**
	 * 更新商品促销信息
	 */
	public NoteResult updateGoods_promotion(String goods_barcode,String goods_promotion,Double goods_discount,Double goods_sale_price,String shop_unique);
	/**
	 * 更新商品信息
	 */
	public NoteResult updateGoods(HttpServletRequest request,String goods_id,String goods_name,String goods_brand,String goods_barcode,String goods_alias,String goods_sale_price,String goods_in_price
			,String goods_life,String goods_points,String goods_address,String goods_contain,String goods_standard,String goods_kind_parunique,String goods_kind_unique,String shop_unique,Integer goods_count,String supplier_unique);
	/**
	 * 添加新商品
	 */
	public NoteResult newGoods(String goods_name,String goods_brand,String goods_barcode,String goods_alias,Double goods_sale_price,Double goods_in_price
			,Integer goods_life,Integer goods_points,String goods_address,Integer goods_contain,String goods_standard,String goods_kind_parunique,String goods_kind_unique,String shop_unique,String manager_unique);
	/**
	 * 新商品添加测试
	 */
	public NoteResult newGoods1(HttpServletRequest request,String goods_name,String goods_brand,String goods_barcode,String goods_alias,String goods_sale_price,String goods_in_price
			,String goods_life,String goods_points,String goods_address,String goods_contain,String goods_standard,String goods_kind_unique,String shop_unique,String manager_unique,Integer goods_count);
	/**
	 * 调货单页面，查询商品基本信息
	 * @param shop_unique，店铺编号
	 * @param goodsmessage，商品信息
	 * @return
	 */
	public NoteResult findGoods2(String shop_unique,String goods_message);
	/**
	 * 调货页面，根据页面查询基本信息
	 * @param shop_unique，商铺编号
	 * @param goods_message，商品信息
	 * @param page_num，页码数
	 * @return
	 */
	public NoteResult findGood_page(String shop_unique,String goods_message,Integer page_num);
	/**
	 * 删除商店中不销售的产品
	 * @param goods_barcode
	 * @param shop_unique
	 * @return
	 */
	public NoteResult deleteGoods(String goods_barcode,String shop_unique);
	/**
	 * 根据商品条码和店铺所选区域，查询供应商信息
	 * @param goods_barcode
	 * @param area_dict_num
	 * @return
	 */
	public NoteResult findGoodsSuppliers(String goods_barcode,String area_dict_num);
}
