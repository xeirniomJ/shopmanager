package org.haier.shop.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.haier.shop.util.NoteResult;

public interface Return_listService {
	/**
	 * 查询满足条件的退货单
	 * @param ret_list_id
	 * @param startDate
	 * @param endDate
	 * @param ret_list_state
	 * @param ret_list_handlestate
	 * @param shop_unique
	 * @return
	 */
	public NoteResult return_lists_find(Integer ret_list_id,Timestamp startDate,Timestamp endDate,String ret_list_state,String ret_list_handlestate,String shop_unique,String manager_unique);
	public NoteResult return_list_find(String ret_list_unique);
	public NoteResult return_list_save(String ret_list_unique,String ret_list_state,String ret_list_handlestate);
	/**
	 * 退货订单excel表生成
	 */
	public NoteResult excel(Integer ret_list_id,Timestamp startDate,Timestamp endDate,String ret_list_state,String ret_list_handlestate,String shop_unique,HttpServletRequest request,String manager_unique);
	/**
	 * 主界面点击未处理订单信息，显示未处理退货订单
	 */
	public NoteResult undo_ret(String manager_unique,String shop_unique);
}
