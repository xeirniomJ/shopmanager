package org.haier.shop.entity;

import java.sql.Date;

/**
 * 本类用于输出打印
 * @author Administrator
 *
 */
public class Purchase {
	//订单编号
	private Integer purchase_list_id;
	//订单总金额
	private Double purchase_list_total;
	//订单生成日期
	private Date purchase_list_date;
	//订单处理状态
	private String purchase_list_statue;
	//商品名称
	private String goods_name;
	//商品具体数量
	private Integer purchase_list_detail_count;
	//商品销售价格
	private Double purchase_list_detail_price;
	public Integer getPurchase_list_id() {
		return purchase_list_id;
	}
	public void setPurchase_list_id(Integer purchase_list_id) {
		this.purchase_list_id = purchase_list_id;
	}
	public Double getPurchase_list_total() {
		return purchase_list_total;
	}
	public void setPurchase_list_total(Double purchase_list_total) {
		this.purchase_list_total = purchase_list_total;
	}
	public Date getPurchase_list_date() {
		return purchase_list_date;
	}
	public void setPurchase_list_date(Date purchase_list_date) {
		this.purchase_list_date = purchase_list_date;
	}
	public String getPurchase_list_statue() {
		return purchase_list_statue;
	}
	public void setPurchase_list_statue(String purchase_list_statue) {
		this.purchase_list_statue = purchase_list_statue;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public Integer getPurchase_list_detail_count() {
		return purchase_list_detail_count;
	}
	public void setPurchase_list_detail_count(Integer purchase_list_detail_count) {
		this.purchase_list_detail_count = purchase_list_detail_count;
	}
	public Double getPurchase_list_detail_price() {
		return purchase_list_detail_price;
	}
	public void setPurchase_list_detail_price(Double purchase_list_detail_price) {
		this.purchase_list_detail_price = purchase_list_detail_price;
	}
	@Override
	public String toString() {
		return "Purchase [purchase_list_id=" + purchase_list_id + ", purchase_list_total=" + purchase_list_total
				+ ", purchase_list_date=" + purchase_list_date + ", purchase_list_statue=" + purchase_list_statue
				+ ", goods_name=" + goods_name + ", purchase_list_detail_count=" + purchase_list_detail_count
				+ ", purchase_list_detail_price=" + purchase_list_detail_price + "]";
	}
	
}
