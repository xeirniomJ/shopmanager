package org.haier.shop.service;

import org.haier.shop.util.NoteResult;

public interface ExchangeService {
	/**
	 * 将产品添加到购物车
	 * @param shop_unique
	 * @param goods_barcode
	 * @param exchange_list_detail_count
	 * @param supplier_unique
	 * @return
	 */
	public NoteResult addToExCart(String shop_unique,String goods_barcode,Double exchange_list_detail_count,String supplier_unique);
	/**
	 * 查询店铺换货清单中的商品；
	 * @param shop_unique
	 * @return
	 */
	public NoteResult FindExchangeGoods(String shop_unique);
	/**
	 * 更新商品数量
	 * @param shop_unique
	 * @param goods_barcode
	 * @param goods_count
	 * @return
	 */
	public NoteResult changeExCartGoods(String shop_unique,String goods_barcode,Double goods_count);
	/**
	 * 移除退货单里的商品
	 * @param shop_unique
	 * @param goods_barcode
	 * @return
	 */
	public NoteResult rmExCartGoods(String shop_unique,String goods_barcode);
	/**
	 * 提交退货单，生成店铺退货单
	 * @param shop_unique
	 * @param uniques
	 * @return
	 */
	public NoteResult submitExCart(String shop_unique,String unique);
}
