package org.haier.shop.entity;
/**
 * 导出退货订单详情
 * @author Administrator
 *
 */

import java.sql.Timestamp;
public class Return{
//退货单号
	private Integer ret_list_id;
	//退货人姓名
	private String cus_name;
	//退货人联系方式
	private String cus_phone;
	//退货时间
	private Timestamp ret_list_datetime;
	//退货总金额
	private Double ret_list_total;
	// 退货产品名称
	private String goods_name;
	//退货产品数量
	private Integer ret_list_detail_count;
	//退货产品价格
	private Double ret_list_detail_price;
	//退款状态
	private String ret_list_state;
	//受理状态
	private String ret_list_handlestate;
	public Integer getRet_list_id() {
		return ret_list_id;
	}
	public void setRet_list_id(Integer ret_list_id) {
		this.ret_list_id = ret_list_id;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getCus_phone() {
		return cus_phone;
	}
	public void setCus_phone(String cus_phone) {
		this.cus_phone = cus_phone;
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
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public Integer getRet_list_detail_count() {
		return ret_list_detail_count;
	}
	public void setRet_list_detail_count(Integer ret_list_detail_count) {
		this.ret_list_detail_count = ret_list_detail_count;
	}
	public Double getRet_list_detail_price() {
		return ret_list_detail_price;
	}
	public void setRet_list_detail_price(Double ret_list_detail_price) {
		this.ret_list_detail_price = ret_list_detail_price;
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
		return "Retrun [ret_list_id=" + ret_list_id + ", cus_name=" + cus_name + ", cus_phone=" + cus_phone
				+ ", ret_list_datetime=" + ret_list_datetime + ", ret_total=" + ret_list_total + ", goods_name=" + goods_name
				+ ", ret_list_detail_count=" + ret_list_detail_count + ", ret_list_detail_price="
				+ ret_list_detail_price + ", ret_list_state=" + ret_list_state + ", ret_list_handlestate="
				+ ret_list_handlestate + "]";
	}
	
}
