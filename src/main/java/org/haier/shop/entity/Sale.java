package org.haier.shop.entity;

import java.sql.Timestamp;

/**
 * 此类用来记录所有销售订单的excel表生产
 * @author Administrator
 *
 */
public class Sale {
	//订单编号
	private Integer sale_list_id;
	//订单生成时间
	private Timestamp sale_list_datetime;
	//订单类型
	private String sale_type;
	//订单送货地址
	private String sale_list_address;
	//订单外送费
	private Double sale_list_delfee;
	//订单付款状态
	private Integer sale_list_state;
	//订单处理状态
	private Integer sale_list_handlestate;
	//订单支付方式
	private Integer sale_list_payment;
	//订单备注
	private String sale_list_remarks;
	//客户姓名
	private String cus_name;
	//客户联系方式
	private String cus_phone;
	//货物名称
	private String goods_name;
	//产品条码
	private String goods_barcode;
	//订单产品数量
	private Integer sale_list_detail_count;
	//订单商品销售单价
	private Double sale_list_detail_price;
	public Integer getSale_list_id() {
		return sale_list_id;
	}
	public void setSale_list_id(Integer sale_list_id) {
		this.sale_list_id = sale_list_id;
	}
	public Timestamp getSale_list_datetime() {
		return sale_list_datetime;
	}
	public void setSale_list_datetime(Timestamp sale_list_datetime) {
		this.sale_list_datetime = sale_list_datetime;
	}
	public String getSale_type() {
		return sale_type;
	}
	public void setSale_type(String sale_type) {
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
	public Integer getSale_list_state() {
		return sale_list_state;
	}
	public void setSale_list_state(Integer sale_list_state) {
		this.sale_list_state = sale_list_state;
	}
	public Integer getSale_list_handlestate() {
		return sale_list_handlestate;
	}
	public void setSale_list_handlestate(Integer sale_list_handlestate) {
		this.sale_list_handlestate = sale_list_handlestate;
	}
	public Integer getSale_list_payment() {
		return sale_list_payment;
	}
	public void setSale_list_payment(Integer sale_list_payment) {
		this.sale_list_payment = sale_list_payment;
	}
	public String getSale_list_remarks() {
		return sale_list_remarks;
	}
	public void setSale_list_remarks(String sale_list_remarks) {
		this.sale_list_remarks = sale_list_remarks;
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
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_barcode() {
		return goods_barcode;
	}
	public void setGoods_barcode(String goods_barcode) {
		this.goods_barcode = goods_barcode;
	}
	public Integer getSale_list_detail_count() {
		return sale_list_detail_count;
	}
	public void setSale_list_detail_count(Integer sale_list_detail_count) {
		this.sale_list_detail_count = sale_list_detail_count;
	}
	public Double getSale_list_detail_price() {
		return sale_list_detail_price;
	}
	public void setSale_list_detail_price(Double sale_list_detail_price) {
		this.sale_list_detail_price = sale_list_detail_price;
	}
	
}
