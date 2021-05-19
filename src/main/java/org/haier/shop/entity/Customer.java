package org.haier.shop.entity;

import java.sql.Date;

/**
 * 会员信息
 * @author Administrator
 *
 */
public class Customer {
	//会员编号
	private Integer cus_id;
	//会员唯一标识符
	private String cus_unique;
	//会员登录账号
	private String cus_account;
	//会员登录密码
	private String cus_pwd;
	//会员姓名
	private String cus_name;
	//会员别名
	private String cus_alias;
	//会员性别
	private String cus_sex;
	//会员电话
	private String cus_phone;
	//会员总积分
	private Integer cus_points;
	//会员注册日期
	private Date cus_regedit_datel;
	//会员生日
	private Date cus_birthday;
	//会员等级
	private Integer cus_level;
	//会员邮箱
	private String cus_email;
	public Integer getCus_id() {
		return cus_id;
	}
	public void setCus_id(Integer cus_id) {
		this.cus_id = cus_id;
	}
	public String getCus_unique() {
		return cus_unique;
	}
	public void setCus_unique(String cus_unique) {
		this.cus_unique = cus_unique;
	}
	public String getCus_account() {
		return cus_account;
	}
	public void setCus_account(String cus_account) {
		this.cus_account = cus_account;
	}
	public String getCus_pwd() {
		return cus_pwd;
	}
	public void setCus_pwd(String cus_pwd) {
		this.cus_pwd = cus_pwd;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getCus_alias() {
		return cus_alias;
	}
	public void setCus_alias(String cus_alias) {
		this.cus_alias = cus_alias;
	}
	public String getCus_sex() {
		return cus_sex;
	}
	public void setCus_sex(String cus_sex) {
		this.cus_sex = cus_sex;
	}
	public String getCus_phone() {
		return cus_phone;
	}
	public void setCus_phone(String cus_phone) {
		this.cus_phone = cus_phone;
	}
	public Integer getCus_points() {
		return cus_points;
	}
	public void setCus_points(Integer cus_points) {
		this.cus_points = cus_points;
	}
	public Date getCus_regedit_datel() {
		return cus_regedit_datel;
	}
	public void setCus_regedit_datel(Date cus_regedit_datel) {
		this.cus_regedit_datel = cus_regedit_datel;
	}
	public Date getCus_birthday() {
		return cus_birthday;
	}
	public void setCus_birthday(Date cus_birthday) {
		this.cus_birthday = cus_birthday;
	}
	public Integer getCus_level() {
		return cus_level;
	}
	public void setCus_level(Integer cus_level) {
		this.cus_level = cus_level;
	}
	public String getCus_email() {
		return cus_email;
	}
	public void setCus_email(String cus_email) {
		this.cus_email = cus_email;
	}
	@Override
	public String toString() {
		return "Customer [cus_id=" + cus_id + ", cus_unique=" + cus_unique + ", cus_account=" + cus_account
				+ ", cus_pwd=" + cus_pwd + ", cus_name=" + cus_name + ", cus_alias=" + cus_alias + ", cus_sex="
				+ cus_sex + ", cus_phone=" + cus_phone + ", cus_points=" + cus_points + ", cus_regedit_datel="
				+ cus_regedit_datel + ", cus_birthday=" + cus_birthday + ", cus_level=" + cus_level + ", cus_email="
				+ cus_email + "]";
	}
	
}
