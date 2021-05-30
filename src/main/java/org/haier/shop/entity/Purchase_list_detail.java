package org.haier.shop.entity;
/**
 * 进货订单详情
 * @author Administrator
 *
 */
public class Purchase_list_detail {
	//进货订单编号
	private Integer purchase_list_id;
	//进货订单唯一标识符
	private String purchase_list_unique;
	//产品唯一编号
	private String goods_name;
	//进货产品数量
	private Integer purchase_list_count;
	//进货产品价格
	private Double purchase_list_price;
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
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public Integer getPurchase_list_count() {
		return purchase_list_count;
	}
	public void setPurchase_list_count(Integer purchase_list_count) {
		this.purchase_list_count = purchase_list_count;
	}
	public Double getPurchase_list_price() {
		return purchase_list_price;
	}
	public void setPurchase_list_price(Double purchase_list_price) {
		this.purchase_list_price = purchase_list_price;
	}
	@Override
	public String toString() {
		return "Purchase_list_detail [purchase_list_id=" + purchase_list_id + ", purchase_list_unique="
				+ purchase_list_unique + ", goods_name=" + goods_name + ", purchase_list_count=" + purchase_list_count
				+ ", purchase_list_price=" + purchase_list_price + "]";
	}
	
}
