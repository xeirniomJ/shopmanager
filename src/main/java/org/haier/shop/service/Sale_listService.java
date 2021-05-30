package org.haier.shop.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.haier.shop.util.NoteResult;

public interface Sale_listService {
	/**
	 * 客户销售订单查询
	 * @param shop_unique
	 * @param sale_list_id
	 * @param startDate
	 * @param endDate
	 * @param sale_state
	 * @param sale_handlestate
	 * @return
	 */
	public NoteResult sale_lists_find(String shop_unique,Integer sale_list_id,Timestamp startDate, Timestamp endDate,String sale_state,String sale_handlestate,String manager_unique);
	/**
	 * 客户订单查询
	 * @param sale_list_id
	 * @return
	 */
	public NoteResult sale_list_find(Integer sale_list_id);
	/**
	 * 客户订单更新
	 * @param sale_list_id
	 * @param sale_list_delfee
	 * @param sale_list_total
	 * @param sale_list_remarks
	 * @param sale_list_state
	 * @param sale_list_handlestate
	 * @return
	 */
	public NoteResult sale_list_update(Integer sale_list_id,Double sale_list_delfee,Double sale_list_total,String sale_list_remarks,String sale_list_state,String sale_list_handlestate);
	/**
	 * 首页未处理订单提醒
	 */
	public NoteResult index_alert(String shop_unique,String manager_unique);
	/**
	 * 查询当日营业额
	 */
	public NoteResult conditions_alert(String shop_unique,Timestamp startDate,Timestamp endDate,String manager_unique);
	/**
	 * 查询某段时间内各种产品的销售情况
	 */
	public NoteResult findSales_details(String shop_unique,Timestamp startDate,Timestamp endDate,String manager_unique);
	/**
	 * 查询某段时间内营业额
	 */
	public NoteResult  findturnover(String shop_unique,String manager_unique);
	/**
	 * 查询订单详情，并生产excel表
	 */
	public NoteResult excel(String shop_unique,Integer sale_list_id, Timestamp startDate,
			Timestamp endDate, String sale_list_state, String sale_list_handlestate,HttpServletRequest request,String manager_unique);
	/**
	 * 首页显示未处理订单
	 * @param manager_unique:管理员帐号
	 */
	public NoteResult undo_sale(String manager_unique,String shop_unique);
	/**
	 * 进货及其滞销提醒
	 * @param shop_unique
	 * @return
	 */
	public NoteResult warning(String shop_unique,String manager_unique);
	/**
	 * 查询5分钟之内的新订单
	 * @param shop_unique
	 * @param manager_unique
	 * @param startTime
	 * @return
	 */
	public NoteResult checkNewOrder(String shop_unique,String manager_unique,Timestamp startTime);
	/**
	 * 查询所有订单支付状态
	 * @return
	 */
	public NoteResult loadSaleListState();
	/**
	 * 查询所有订单处理状态
	 * @return
	 */
	public NoteResult loadSaleListHandleState();
}
