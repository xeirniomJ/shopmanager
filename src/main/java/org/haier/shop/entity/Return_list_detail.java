package org.haier.shop.entity;
/**
 * 退货订单详情
 * @author Administrator
 *
 */
public class Return_list_detail {
	//退货清单编号
	private Integer ret_list_detail_id;
	//退货单唯一标识符
	private String ret_list_unique;
	//商品编号
	private String goods_unique;
	//商品名称
	private String goods_name;
	//退货商品数量
	private Integer ret_list_detail_count;
	//退货商品价格
	private Double ret_list_detail_price;
	public Integer getRet_list_detail_id() {
		return ret_list_detail_id;
	}
	public void setRet_list_detail_id(Integer ret_list_detail_id) {
		this.ret_list_detail_id = ret_list_detail_id;
	}
	public String getRet_list_unique() {
		return ret_list_unique;
	}
	public void setRet_list_unique(String ret_list_unique) {
		this.ret_list_unique = ret_list_unique;
	}
	public String getGoods_unique() {
		return goods_unique;
	}
	public void setGoods_unique(String goods_unique) {
		this.goods_unique = goods_unique;
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
	@Override
	public String toString() {
		return "Return_list_detail [ret_list_detail_id=" + ret_list_detail_id + ", ret_list_unique=" + ret_list_unique
				+ ", goods_unique=" + goods_unique + ", goods_name=" + goods_name + ", ret_list_detail_count="
				+ ret_list_detail_count + ", ret_list_detail_price=" + ret_list_detail_price + "]";
	}
	
}
