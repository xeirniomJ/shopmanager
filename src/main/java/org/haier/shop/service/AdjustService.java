package org.haier.shop.service;

import java.sql.Date;

import org.haier.shop.util.NoteResult;

public interface AdjustService {
	/**
	 *    查看满足条件的调货单
	 * @param shop_unique，店铺编号
	 * @param adjust_statue，响应状态
	 * @param adjust_handlestatue，发货状态
	 * @return
	 */
	public NoteResult adjust_find(String shop_unique,String adjust_statue,String adjust_handlestatue,String adjust_type,Integer page_num,String manager_unique);
	/**
	 * 取消未被响应的调货申请
	 * @param adjust_id，调货单编号
	 * @return
	 */
	public NoteResult adjust_cancel(Integer adjust_id);
	/**
	 * 自动添加调货单类型
	 * @return
	 */
	public NoteResult adjust_statue_find();
	/**
	 * 自动添加调货单处理装
	 * @return
	 */
	public NoteResult adjust_handlestatue_find();
	/**
	 * 将新调货单添加到数据库
	 * @param shop_unique，申请方店铺号
	 * @param goods_barcode，商品条形码
	 * @param adjust_count，调货数量
	 * @param adjust_price，调货价格
	 * @param adjust_type，调货类型
	 * @param adjust_validity，调货有效期
	 * @return
	 */
	public NoteResult adjust_new_save(String shop_unique,String goods_barcode,Double adjust_count,Double adjust_price,String adjust_type,Date adjust_validity,String goods_name,String adjust_remark);
	/**
	 *	自动添加所有调货单申请类型
	 */
	public NoteResult adjust_type_find();
	/**
	 * 查询其他店面的调货申请
	 * @return
	 */
	public NoteResult oadjust_find(String goods_message,String adjust_type,Integer page_num,String shop_unique,String manager_unique);
	/**
	 * 
	 * @param shop_unique,响应店铺的标识符
	 * @param adjust_id，响应的单号
	 * @return
	 */
	public NoteResult adjust_update(String shop_unique,String adjust_id);
	/**
	 * 根据输入条件，查询店铺满足条件的响应调货单
	 * @param shop_unique，响应店铺唯一标识符
	 * @param goods_message，商品信息
	 * @param adjust_type，调货类型
	 * @return
	 */
	public NoteResult adjust_rfind(String shop_unique,String goods_message,String adjust_type);
}
