package org.haier.shop.service;

import org.haier.shop.util.NoteResult;

public interface Sup_goods_kindService {
	/**
	 * 查询所有供货商商品大类
	 * @return
	 */
	public NoteResult findParNames();
	/**
	 * 根据选中的商品大类，查询对应的小类
	 * @param sup_goods_kind_parunique
	 * @return
	 */
	public NoteResult findNames(String sup_goods_kind_parunique);
}
