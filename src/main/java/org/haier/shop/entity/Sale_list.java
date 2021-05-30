package org.haier.shop.entity;

import java.sql.Timestamp;

/**
 * 销售订单
 */
public class Sale_list {
	//销售订单编号
	private Integer sale_list_id;
	//销售订单唯一标识符
	private String sale_list_unique;
	//销售订单生成时间
	private Timestamp sale_list_datetime;
	//销售订单总金额
	private Double sale_list_total;
	//顾客唯一标识符
	private String cus_unique;
	//订单类型
	private Integer sale_type;
	//订单送货地址
	private String sale_list_address;
	//订单外送费
	private Double sale_list_delfee;
	//订单折扣
	private Double sale_list_discount;
	//订单付款状态
	private Integer sale_list_state;
	//订单处理状态
	private Integer sale_list_handlestate;
	//订单支付方式
	private String sale_list_payment;
	//订单备注
	private String sale_list_remarks;
	public Integer getSale_list_id() {
		return sale_list_id;
	}
	public void setSale_list_id(Integer sale_list_id) {
		this.sale_list_id = sale_list_id;
	}
	public String getSale_list_unique() {
		return sale_list_unique;
	}
	public void setSale_list_unique(String sale_list_unique) {
		this.sale_list_unique = sale_list_unique;
	}
	public Timestamp getSale_list_datetime() {
		return sale_list_datetime;
	}
	public void setSale_list_datetime(Timestamp sale_list_datetime) {
		this.sale_list_datetime = sale_list_datetime;
	}
	public Double getSale_list_total() {
		return sale_list_total;
	}
	public void setSale_list_total(Double sale_list_total) {
		this.sale_list_total = sale_list_total;
	}
	public String getCus_unique() {
		return cus_unique;
	}
	public void setCus_unique(String cus_unique) {
		this.cus_unique = cus_unique;
	}
	public Integer getSale_type() {
		return sale_type;
	}
	public void setSale_type(Integer sale_type) {
		this.sale_type = sale_type;
	}
	public String getSale_list_address() {
		return sale_list_address;
	}
	public void setSale_list_address(String sale_list_address) {
		this.sale_list_address = sale_list_address;
	}
	public Double getSale_list_delfee() {
		return sale_list_delfee;
	}
	public void setSale_list_delfee(Double sale_list_delfee) {
		this.sale_list_delfee = sale_list_delfee;
	}
	public Double getSale_list_discount() {
		return sale_list_discount;
	}
	public void setSale_list_discount(Double sale_list_discount) {
		this.sale_list_discount = sale_list_discount;
	}
	
	public Integer getSale_list_state() {
		return sale_list_state;
	}
	public void setSale_list_state(Integer sale_list_state) {
		this.sale_list_state = sale_list_state;
	}
	public String getSale_list_payment() {
		return sale_list_payment;
	}
	public Integer getSale_list_handlestate() {
		return sale_list_handlestate;
	}
	public void setSale_list_handlestate(Integer sale_list_handlestate) {
		this.sale_list_handlestate = sale_list_handlestate;
	}
	public void setSale_list_payment(String sale_list_payment) {
		this.sale_list_payment = sale_list_payment;
	}
	public String getSale_list_remarks() {
		return sale_list_remarks;
	}
	public void setSale_list_remarks(String sale_list_remarks) {
		this.sale_list_remarks = sale_list_remarks;
	}
	@Override
	public String toString() {
		return "Sale_list [sale_list_id=" + sale_list_id + ", sale_list_unique=" + sale_list_unique
				+ ", sale_list_datetime=" + sale_list_datetime + ", sale_list_total=" + sale_list_total
				+ ", cus_unique=" + cus_unique + ", sale_type=" + sale_type + ", sale_list_address=" + sale_list_address
				+ ", sale_list_delfee=" + sale_list_delfee + ", sale_list_discount=" + sale_list_discount
				+ ", sale_list_state=" + sale_list_state + ", sale_list_handlestate=" + sale_list_handlestate
				+ ", sale_list_payment=" + sale_list_payment + "]";
	}
	
}
