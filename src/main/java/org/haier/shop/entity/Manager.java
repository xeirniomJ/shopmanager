package org.haier.shop.entity;

public class Manager {
	//员工编号
	private Integer manager_id;
	//唯一标识符
	private String manager_unique;
	//员工账号
	private String manganer_account;
	//员工密码
	private String manager_pwd;
	//员工姓名
	private String manager_name;
	//员工联系方式
	private String manager_phone;
	//员工对应店铺编号
	private String shop_unique;
	public String getShop_unique() {
		return shop_unique;
	}
	public void setShop_unique(String shop_unique) {
		this.shop_unique = shop_unique;
	}
	public Integer getManager_id() {
		return manager_id;
	}
	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}
	public String getManager_unique() {
		return manager_unique;
	}
	public void setManager_unique(String manager_unique) {
		this.manager_unique = manager_unique;
	}
	public String getManganer_account() {
		return manganer_account;
	}
	public void setManganer_account(String manganer_account) {
		this.manganer_account = manganer_account;
	}
	public String getManager_pwd() {
		return manager_pwd;
	}
	public void setManager_pwd(String manager_pwd) {
		this.manager_pwd = manager_pwd;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getManager_phone() {
		return manager_phone;
	}
	public void setManager_phone(String manager_phone) {
		this.manager_phone = manager_phone;
	}
	
}
