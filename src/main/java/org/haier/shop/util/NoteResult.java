package org.haier.shop.util;


import java.io.Serializable;

/**
 * 作为所有请求的响应对象
 *
 */
public class NoteResult implements Serializable{
	private static final long serialVersionUID = 1L;
	//	private static final long serialVersionUID = 1L;
	private int status;//状态
	private String msg;//提示消息
	private Object data;//返回的数据
	private Object data1;//返回的数据1
	private Object address;//返回的数据2
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getData1() {
		return data1;
	}
	public void setData1(Object data1) {
		this.data1 = data1;
	}
	public Object getAddress() {
		return address;
	}
	public void setAddress(Object address) {
		this.address = address;
	}
	
	
}
