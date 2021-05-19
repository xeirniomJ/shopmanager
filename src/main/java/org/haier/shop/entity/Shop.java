package org.haier.shop.entity;
/**
 * 店铺信息
 * @author Administrator
 *
 */
public class Shop {
	//店铺编号
	private Integer shop_id;
	//店铺唯一标识符
	private String shop_unique;
	//超市所在商圈
	private String trade_area_unique;
	//店铺名称
	private String shop_name;
	//店铺地址
	private String shop_address_detail;
	//店铺联系方式
	private String shop_phone;
	//店铺备注
	private String shop_remark;
	//店铺图片路径
	private String shop_image_path;
	//合作身份者ID，签约账户
	private String shop_partner;
	//店铺账号
	private String shop_seller_email;
	//店铺所在经度
	private Double shop_longitude;
	//店铺所在维度
	private Double shop_latitude;
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public String getShop_unique() {
		return shop_unique;
	}
	public void setShop_unique(String shop_unique) {
		this.shop_unique = shop_unique;
	}
	public String getTrade_area_unique() {
		return trade_area_unique;
	}
	public void setTrade_area_unique(String trade_area_unique) {
		this.trade_area_unique = trade_area_unique;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShop_address_detail() {
		return shop_address_detail;
	}
	public void setShop_address_detail(String shop_address_detail) {
		this.shop_address_detail = shop_address_detail;
	}
	public String getShop_phone() {
		return shop_phone;
	}
	public void setShop_phone(String shop_phone) {
		this.shop_phone = shop_phone;
	}
	public String getShop_remark() {
		return shop_remark;
	}
	public void setShop_remark(String shop_remark) {
		this.shop_remark = shop_remark;
	}
	public String getShop_image_path() {
		return shop_image_path;
	}
	public void setShop_image_path(String shop_image_path) {
		this.shop_image_path = shop_image_path;
	}
	public String getShop_partner() {
		return shop_partner;
	}
	public void setShop_partner(String shop_partner) {
		this.shop_partner = shop_partner;
	}
	public String getShop_seller_email() {
		return shop_seller_email;
	}
	public void setShop_seller_email(String shop_seller_email) {
		this.shop_seller_email = shop_seller_email;
	}
	public Double getShop_longitude() {
		return shop_longitude;
	}
	public void setShop_longitude(Double shop_longitude) {
		this.shop_longitude = shop_longitude;
	}
	public Double getShop_latitude() {
		return shop_latitude;
	}
	public void setShop_latitude(Double shop_latitude) {
		this.shop_latitude = shop_latitude;
	}
	@Override
	public String toString() {
		return "Shop [shop_id=" + shop_id + ", shop_unique=" + shop_unique + ", trade_area_unique=" + trade_area_unique
				+ ", shop_name=" + shop_name + ", shop_address_detail=" + shop_address_detail + ", shop_phone="
				+ shop_phone + ", shop_remark=" + shop_remark + ", shop_image_path=" + shop_image_path
				+ ", shop_partner=" + shop_partner + ", shop_seller_email=" + shop_seller_email + ", shop_longitude="
				+ shop_longitude + ", shop_latitude=" + shop_latitude + "]";
	}

}
