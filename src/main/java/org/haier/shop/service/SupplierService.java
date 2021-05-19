package org.haier.shop.service;

import org.haier.shop.util.NoteResult;

public interface SupplierService {
	/**
	 * 查询刚申请的店铺
	 * @param manager_unique
	 * @param examinestatus
	 * @return
	 */
	public NoteResult findApplys(String manager_unique,String manager_token);
	/**
	 * 查询新申请的店铺
	 * @param manager_unique
	 * @param manager_token
	 * @return
	 */
	public NoteResult findSupplierApplys(String manager_unique,String manager_token);
	/**
	 * 加载供应商信息
	 * @param manager_unique
	 * @param manager_token
	 * @param supplier_unique
	 * @return
	 */
	public NoteResult loadSupplierMessage(String manager_unique,String manager_token,String supplier_unique);
	/**
	 * 将店铺的审核状态更改为通过状态
	 * @param manager_unique
	 * @param manager_token
	 * @param supplier_unique
	 * @return
	 */
	public NoteResult passExamine(String manager_unique,String manager_token,String supplier_unique);
	/**
	 * 将供货商更新为审核未通过状态，并提交审核未通过原因
	 * @param manager_unique
	 * @param manager_token
	 * @param supplier_unique
	 * @param examinestatus_reason
	 * @return
	 */
	public NoteResult submitReason(String manager_unique,String manager_token,String supplier_unique,String examinestatus_reason);
	/**
	 * 查询满足条件的供应商信息
	 * @param manager_unique
	 * @param manager_token
	 * @param parea_dict_num
	 * @param carea_dict_num
	 * @param area_dict_num
	 * @param examinestatus
	 * @param message
	 * @return
	 */
	public NoteResult findSuppliers(String manager_unique,String manager_token,String parea_dict_num,String carea_dict_num,String area_dict_num,String examinestatus,String message);
}
