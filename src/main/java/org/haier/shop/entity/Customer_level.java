package org.haier.shop.entity;
/**
 * 会员等级实体类
 * @author Administrator
 *
 */
public class Customer_level {
	//会员等级编号
	private String customer_level_id;
	//会员等级唯一标识符
	private String customer_level_unique;
	//会员等级名称
	private String customer_level_name;
	//会员等级积分需求
	private String customer_level_points;
	//会员折扣率
	private 	Double customer_level_discount;
	
	public String getcustomer_level_unique() {
		return customer_level_unique;
	}
	public void setcustomer_level_unique(String customer_level_unique) {
		this.customer_level_unique = customer_level_unique;
	}
	public String getcustomer_level_id() {
		return customer_level_id;
	}
	public void setcustomer_level_id(String customer_level_id) {
		this.customer_level_id = customer_level_id;
	}
	public String getcustomer_level_name() {
		return customer_level_name;
	}
	public void setcustomer_level_name(String customer_level_name) {
		this.customer_level_name = customer_level_name;
	}
	public String getcustomer_level_points() {
		return customer_level_points;
	}
	public void setcustomer_level_points(String customer_level_points) {
		this.customer_level_points = customer_level_points;
	}
	public Double getcustomer_level_discount() {
		return customer_level_discount;
	}
	public void setcustomer_level_discount(Double customer_level_discount) {
		this.customer_level_discount = customer_level_discount;
	}
	@Override
	public String toString() {
		return "customertomer_level [customer_level_id=" + customer_level_id + ", customer_level_unique=" + customer_level_unique
				+ ", customer_level_name=" + customer_level_name + ", customer_level_points=" + customer_level_points
				+ ", customer_level_discount=" + customer_level_discount + "]";
	}
}