package org.haier.shop.service;

import org.haier.shop.util.NoteResult;

public interface Goods_kindService {
	/**
	 * 此方法用于查询所有商品大类
	 * 自动添加商品大类
	 * @return
	 */
	public NoteResult add_goods_kind_group(String shop_unique,String manager_unique);
	/**
	 * 此方法用于查询时，当商品大类修改后，自动修改商品小类内容
	 * @param goods_kind_group
	 * @return
	 */
	public NoteResult inadd_goods_kind_name(String goods_kind_group,String shop_unique);
	/**
	 * 插入新商品种类
	 */
	public NoteResult newGoods_kind(String goods_kind_group,String goods_kind_name,String shop_unique,String manager_unique);
	/**
	 * 查询所有满足条件的商品分类信息
	 * @param goods_kind_parunique
	 * @param goods_kind_unique
	 * @param shop_unique
	 * @param manager_unique
	 * @return
	 */
	public NoteResult queryGoods_kind(String goods_kind_parunique,String goods_kind_unique,String shop_unique,String manager_unique);
	/**
	 * 根据商品类型编号，查询商品分类信息
	 * @param goods_kind_id
	 * @return
	 */
	public NoteResult findGoods_kind(String goods_kind_id);
	/**
	 * 更新商品信息
	 * @param goods_kind_id
	 * @param goods_kind_group
	 * @param goods_kind_name
	 * @param shop_unique
	 * @return
	 */
	public NoteResult modifyGoods_kind(Integer goods_kind_id,String goods_kind_group,String goods_kind_name,String shop_unique);
	/**
	 * 查询店铺所有的商品小类
	 * @param shop_unique
	 * @param manager_unique
	 * @return
	 */
	public NoteResult findAllNames(String shop_unique,String manager_unique);
	/**
	 * 查询所有基本类中的一级分类
	 * @return
	 */
	public NoteResult addStairKind();
	/**
	 * 根据选择的一级商品分类编号，查询二级商品分类
	 * @param goodsKindPid
	 * @return
	 */
	public NoteResult addSecLevel(Integer goodsKindPid);
	/**
	 * 自动添加所有二级商品分类信息  
	 * @return
	 */
	public NoteResult addSLevels();
	/**
	 * 自动添加三级商品分类信息
	 * @return
	 */
	public NoteResult addTLevels();
	/**
	 * 商品一级分类填写后，自动修改二级分类信息
	 * @return
	 */
	public NoteResult chSLevels(String goodsKindName);
	/**
	 * 查询店铺商品分类(全版，管理员版)
	 * @param shop_unique
	 * @return
	 */
	public NoteResult queryKinds(String shop_unique);
	/**
	 * 保存店铺所选择的商品分类
	 * @param shop_unique
	 * @param messages
	 * @return
	 */
	public NoteResult submitShopKinds(String shop_unique,String message);
}

