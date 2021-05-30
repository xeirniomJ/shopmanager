package org.haier.shop.entity;

import java.util.Date;

/**
 * 商品类
 * @author Administrator
 *
 */
public class Goods {
	//商品编号
	private Integer goods_id;
	//商铺唯一标识符
	private String shop_unique;
	//商品条形码
	private String goods_barcode;
	//商品品牌
	private String goods_brand;
	//商品促销状态
	private String goods_promotion;
	//商品名称
	private String goods_name;
	//商品别名
	private String goods_alias;
	//商品进价
	private Double goods_in_price;
	//商品售价
	private Double goods_sale_price;
	//商品生产日期
	private Date goods_date;
	//商品保质天数
	private Integer goods_life;
	//商品积分数量
	private Integer goods_points;
	//商品生产地址
	private String goods_address;
	//商品折扣率
	private Double goods_discount;
	//商品包含子商品数量
	private Integer goods_contain;
	//商品图片保存路径
	private String goods_picturepath;
	//商品分类唯一标识符
	private String goods_kind_unique;
	//商品供应商唯一标识符
	private String supplier_unique;
	//商品简单规格描述
	private String goods_spec;
	//商品说明
	private String goods_remarks;
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public String getShop_unique() {
		return shop_unique;
	}
	public void setShop_unique(String shop_unique) {
		this.shop_unique = shop_unique;
	}
	public String getGoods_barcode() {
		return goods_barcode;
	}
	public void setGoods_barcode(String goods_barcode) {
		this.goods_barcode = goods_barcode;
	}
	public String getGoods_brand() {
		return goods_brand;
	}
	public void setGoods_brand(String goods_brand) {
		this.goods_brand = goods_brand;
	}
	public String getGoods_promotion() {
		return goods_promotion;
	}
	public void setGoods_promotion(String goods_promotion) {
		this.goods_promotion = goods_promotion;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_alias() {
		return goods_alias;
	}
	public void setGoods_alias(String goods_alias) {
		this.goods_alias = goods_alias;
	}
	public Double getGoods_in_price() {
		return goods_in_price;
	}
	public void setGoods_in_price(Double goods_in_price) {
		this.goods_in_price = goods_in_price;
	}
	public Double getGoods_sale_price() {
		return goods_sale_price;
	}
	public void setGoods_sale_price(Double goods_sale_price) {
		this.goods_sale_price = goods_sale_price;
	}
	
	public Date getGoods_date() {
		return goods_date;
	}
	public void setGoods_date(Date goods_date) {
		this.goods_date = goods_date;
	}
	public Integer getGoods_life() {
		return goods_life;
	}
	public void setGoods_life(Integer goods_life) {
		this.goods_life = goods_life;
	}
	public Integer getGoods_points() {
		return goods_points;
	}
	public void setGoods_points(Integer goods_points) {
		this.goods_points = goods_points;
	}
	public String getGoods_address() {
		return goods_address;
	}
	public void setGoods_address(String goods_address) {
		this.goods_address = goods_address;
	}
	public Double getGoods_discount() {
		return goods_discount;
	}
	public void setGoods_discount(Double goods_discount) {
		this.goods_discount = goods_discount;
	}
	public Integer getGoods_contain() {
		return goods_contain;
	}
	public void setGoods_contain(Integer goods_contain) {
		this.goods_contain = goods_contain;
	}
	public String getGoods_picturepath() {
		return goods_picturepath;
	}
	public void setGoods_picturepath(String goods_picturepath) {
		this.goods_picturepath = goods_picturepath;
	}
	public String getGoods_kind_unique() {
		return goods_kind_unique;
	}
	public void setGoods_kind_unique(String goods_kind_unique) {
		this.goods_kind_unique = goods_kind_unique;
	}
	public String getSupplier_unique() {
		return supplier_unique;
	}
	public void setSupplier_unique(String supplier_unique) {
		this.supplier_unique = supplier_unique;
	}
	public String getGoods_spec() {
		return goods_spec;
	}
	public void setGoods_spec(String goods_spec) {
		this.goods_spec = goods_spec;
	}
	
	public String getGoods_remarks() {
		return goods_remarks;
	}
	public void setGoods_remarks(String goods_remarks) {
		this.goods_remarks = goods_remarks;
	}
	@Override
	public String toString() {
		return "Goods [goods_id=" + goods_id + ", shop_unique=" + shop_unique + ", goods_barcode=" + goods_barcode
				+ ", goods_brand=" + goods_brand + ", goods_promotion=" + goods_promotion + ", goods_name=" + goods_name
				+ ", goods_alias=" + goods_alias + ", goods_in_price=" + goods_in_price + ", goods_sale_price="
				+ goods_sale_price + ", goods_date=" + goods_date + ", goods_life=" + goods_life + ", goods_points="
				+ goods_points + ", goods_address=" + goods_address + ", goods_discount=" + goods_discount
				+ ", goods_contain=" + goods_contain + ", goods_picturepath=" + goods_picturepath
				+ ", goods_kind_unique=" + goods_kind_unique + ", supplier_unique=" + supplier_unique + ", goods_spec="
				+ goods_spec + ", goods_remarks=" + goods_remarks + "]";
	}
}
