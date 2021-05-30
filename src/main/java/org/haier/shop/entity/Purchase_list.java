package org.haier.shop.entity;

import java.sql.Date;

/**
 * 进货订单
 * @author Administrator
 *
 */
public class Purchase_list {
	//进货订单号
	private Integer purchase_list_id;
	//进货单唯一编号
	private String purchase_list_unique;
	//进货日期
	private Date purchase_list_date;
	//进货总金额
	private Double purchase_list_total;
	//进货状态
	private String purchase_list_statue;
	public Integer getPurchase_list_id() {
		return purchase_list_id;
	}
	public void setPurchase_list_id(Integer purchase_list_id) {
		this.purchase_list_id = purchase_list_id;
	}
	
	public String getPurchase_list_unique() {
		return purchase_list_unique;
	}
	public void setPurchase_list_unique(String purchase_list_unique) {
		this.purchase_list_unique = purchase_list_unique;
	}
	public Date getPurchase_list_date() {
		return purchase_list_date;
	}
	public void setPurchase_list_date(Date purchase_list_date) {
		this.purchase_list_date = purchase_list_date;
	}
	public Double getPurchase_list_total() {
		return purchase_list_total;
	}
	public void setPurchase_list_total(Double purchase_list_total) {
		this.purchase_list_total = purchase_list_total;
	}
	public String getPurchase_list_statue() {
		return purchase_list_statue;
	}
	public void setPurchase_list_statue(String purchase_list_statue) {
		this.purchase_list_statue = purchase_list_statue;
	}
	@Override
	public String toString() {
		return "Purchase_list [purchase_list_id=" + purchase_list_id + ", purchase_unique=" + purchase_list_unique
				+ ", purchase_list_date=" + purchase_list_date + ", purchase_list_total=" + purchase_list_total
				+ ", purchase_list_statue=" + purchase_list_statue + "]";
	}
}
