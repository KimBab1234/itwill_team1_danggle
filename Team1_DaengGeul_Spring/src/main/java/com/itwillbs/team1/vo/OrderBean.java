package com.itwillbs.team1.vo;

import java.sql.Date;
import java.util.ArrayList;

public class OrderBean {

	private String order_imp_uid;
	private String order_idx;
	private String member_id;
	private int order_total_pay;
	private String order_payment;
	private int order_point;
	private String order_name;
	private String order_address;
	private String order_phone;
	private Date order_date;
	private ArrayList<OrderBean> order_prod_list;
	
	public ArrayList<OrderBean> getOrder_prod_list() {
		return order_prod_list;
	}
	public void setOrder_prod_list(ArrayList<OrderBean> order_prod_list) {
		this.order_prod_list = order_prod_list;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getOrder_total_pay() {
		return order_total_pay;
	}
	public void setOrder_total_pay(int order_total_pay) {
		this.order_total_pay = order_total_pay;
	}
	public String getOrder_payment() {
		return order_payment;
	}
	public void setOrder_payment(String order_payment) {
		this.order_payment = order_payment;
	}
	public int getOrder_point() {
		return order_point;
	}
	public void setOrder_point(int order_point) {
		this.order_point = order_point;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	
	public String getOrder_imp_uid() {
		return order_imp_uid;
	}
	public void setOrder_imp_uid(String order_imp_uid) {
		this.order_imp_uid = order_imp_uid;
	}
	public String getOrder_idx() {
		return order_idx;
	}
	public void setOrder_idx(String order_idx) {
		this.order_idx = order_idx;
	}
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public String getOrder_address() {
		return order_address;
	}
	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}
	public String getOrder_phone() {
		return order_phone;
	}
	public void setOrder_phone(String order_phone) {
		this.order_phone = order_phone;
	}
	@Override
	public String toString() {
		return "OrderBean [order_imp_uid=" + order_imp_uid + ", order_idx=" + order_idx + ", member_id=" + member_id
				+ ", order_total_pay=" + order_total_pay + ", order_payment=" + order_payment + ", order_point="
				+ order_point + ", order_name=" + order_name + ", order_address=" + order_address + ", order_phone="
				+ order_phone + ", order_date=" + order_date + ", order_prod_list=" + order_prod_list + "]";
	}

}
