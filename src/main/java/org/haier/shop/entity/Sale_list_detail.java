package org.haier.shop.entity;
/**
 * 订单详情
 * @author Administrator
 *
 */
public class Sale_list_detail {
	//订单详情编号
	private Integer sale_list_detail_id;
	//订单详情唯一标识符
	private String sale_list_unique;
	//商品条形码
	private String goods_barcode;
	//商品名称
	private String goods_name;
	//商品数量
	private Integer sale_list_detail_count;
	//商品售价
	private Double sale_list_detail_price;
	//商品金额小计
	private Double sale_list_detail_subtotal;
	public Integer getSale_list_detail_id() {
		return sale_list_detail_id;
	}
	public void setSale_list_detail_id(Integer sale_list_detail_id) {
		this.sale_list_detail_id = sale_list_detail_id;
	}
	public String getSale_list_unique() {
		return sale_list_unique;
	}
	public void setSale_list_unique(String sale_list_unique) {
		this.sale_list_unique = sale_list_unique;
	}
	public String getGoods_barcode() {
		return goods_barcode;
	}
	public void setGoods_barcode(String goods_barcode) {
		this.goods_barcode = goods_barcode;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
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
	public Double getSale_list_detail_subtotal() {
		return sale_list_detail_subtotal;
	}
	public void setSale_list_detail_subtotal(Double sale_list_detail_subtotal) {
		this.sale_list_detail_subtotal = sale_list_detail_subtotal;
	}
	@Override
	public String toString() {
		return "Sale_list_detail [sale_list_detail_id=" + sale_list_detail_id + ", sale_list_unique=" + sale_list_unique
				+ ", goods_barcode=" + goods_barcode + ", goods_name=" + goods_name + ", sale_list_detail_count="
				+ sale_list_detail_count + ", sale_list_detail_price=" + sale_list_detail_price
				+ ", sale_list_detail_subtotal=" + sale_list_detail_subtotal + "]";
	}
	
}
