package org.haier.shop.entity;
/**
 * 会员积分管理
 */
public class Cus_points {
	//编号
	private Integer cus_points_id;
	//店铺编号
	private String shop_unique;
	//会员编号
	private String cus_unique;
	//会员积分
	private Integer cus_shop_points;
	public Integer getCus_points_id() {
		return cus_points_id;
	}
	public void setCus_points_id(Integer cus_points_id) {
		this.cus_points_id = cus_points_id;
	}
	public String getShop_unique() {
		return shop_unique;
	}
	public void setShop_unique(String shop_unique) {
		this.shop_unique = shop_unique;
	}
	public String getCus_unique() {
		return cus_unique;
	}
	public void setCus_unique(String cus_unique) {
		this.cus_unique = cus_unique;
	}
	public Integer getCus_shop_points() {
		return cus_shop_points;
	}
	public void setCus_shop_points(Integer cus_shop_points) {
		this.cus_shop_points = cus_shop_points;
	}
	
}
