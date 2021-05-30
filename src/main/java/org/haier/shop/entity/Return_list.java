package org.haier.shop.entity;

import java.sql.Timestamp;

/**
 * 退货列表
 * @author Administrator
 *
 */
public class Return_list {
	//退货单号
	private Integer ret_list_id;
	//退货唯一标识符
	private String ret_list_unique;
	//退货日期
	private Timestamp ret_list_datetime;
	//退货总金额
	private Double ret_list_total;
	//客户唯一标识符
	private String cus_unique;
	//退款状态
	private String ret_list_state;
	//订单处理状态
	private String ret_list_handlestate;
	public Integer getRet_list_id() {
		return ret_list_id;
	}
	public void setRet_list_id(Integer ret_list_id) {
		this.ret_list_id = ret_list_id;
	}
	public String getRet_list_unique() {
		return ret_list_unique;
	}
	public void setRet_list_unique(String ret_list_unique) {
		this.ret_list_unique = ret_list_unique;
	}
	public Timestamp getRet_list_datetime() {
		return ret_list_datetime;
	}
	public void setRet_list_datetime(Timestamp ret_list_datetime) {
		this.ret_list_datetime = ret_list_datetime;
	}
	public Double getRet_list_total() {
		return ret_list_total;
	}
	public void setRet_list_total(Double ret_list_total) {
		this.ret_list_total = ret_list_total;
	}
	public String getCus_unique() {
		return cus_unique;
	}
	public void setCus_unique(String cus_unique) {
		this.cus_unique = cus_unique;
	}
	public String getRet_list_state() {
		return ret_list_state;
	}
	public void setRet_list_state(String ret_list_state) {
		this.ret_list_state = ret_list_state;
	}
	public String getRet_list_handlestate() {
		return ret_list_handlestate;
	}
	public void setRet_list_handlestate(String ret_list_handlestate) {
		this.ret_list_handlestate = ret_list_handlestate;
	}
	@Override
	public String toString() {
		return "Return_list [ret_list_id=" + ret_list_id + ", ret_list_unique=" + ret_list_unique
				+ ", ret_list_datetime=" + ret_list_datetime + ", ret_list_total=" + ret_list_total + ", cus_unique="
				+ cus_unique + ", ret_list_state=" + ret_list_state + ", ret_list_handlestate=" + ret_list_handlestate
				+ "]";
	}
	
}
