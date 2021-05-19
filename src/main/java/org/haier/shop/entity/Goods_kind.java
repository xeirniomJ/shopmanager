package org.haier.shop.entity;

public class Goods_kind {
	//商品类型编号
	private Integer goods_kind_id;
	//商品类型唯一标识
	private String goods_kind_unique;
	//商品小类
	private String goods_kind_name;
	//商品大类
	private String goods_kind_preunique;
	public Integer getGoods_kind_id() {
		return goods_kind_id;
	}
	public void setGoods_kind_id(Integer goods_kind_id) {
		this.goods_kind_id = goods_kind_id;
	}
	public String getGoods_kind_unique() {
		return goods_kind_unique;
	}
	public String getGoods_kind_preunique() {
		return goods_kind_preunique;
	}
	public void setGoods_kind_preunique(String goods_kind_preunique) {
		this.goods_kind_preunique = goods_kind_preunique;
	}
	public void setGoods_kind_unique(String goods_kind_unique) {
		this.goods_kind_unique = goods_kind_unique;
	}
	public String getGoods_kind_name() {
		return goods_kind_name;
	}
	public void setGoods_kind_name(String goods_kind_name) {
		this.goods_kind_name = goods_kind_name;
	}
	@Override
	public String toString() {
		return "Goods_kind [goods_kind_id=" + goods_kind_id + ", goods_kind_unique=" + goods_kind_unique
				+ ", goods_kind_name=" + goods_kind_name + ", goods_kind_preunique=" + goods_kind_preunique + "]";
	}
	
}
