package com.itwillbs.team1.vo;

import java.sql.Date;
import java.util.ArrayList;

public class OrderBean {

	private String order_imp_uid;
	private String order_merchant_uid;
	private String member_id;
	private int order_total_pay;
	private String order_payment;
	private int order_point;
	private String order_name;
	private String order_address;
	private String order_phone;
	private Date order_date;
	private ArrayList<String>  order_status;
	private ArrayList<String> order_prod_idx;
	private ArrayList<String> order_prod_name;
	private ArrayList<String> order_prod_opt;
	private ArrayList<Integer> order_prod_cnt;
	private ArrayList<Integer> order_prod_price;
	private ArrayList<String> review_write;
	private ArrayList<String> order_prod_img;

	public ArrayList<String> getOrder_prod_img() {
		return order_prod_img;
	}
	public void setOrder_prod_img(ArrayList<String> order_prod_img) {
		this.order_prod_img = order_prod_img;
	}
	public ArrayList<String> getReview_write() {
		return review_write;
	}
	public void setReview_write(ArrayList<String> review_write) {
		this.review_write = review_write;
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
	public String getOrder_merchant_uid() {
		return order_merchant_uid;
	}
	public void setOrder_merchant_uid(String order_merchant_uid) {
		this.order_merchant_uid = order_merchant_uid;
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
	
	public ArrayList<String> getOrder_status() {
		return order_status;
	}
	public void setOrder_status(ArrayList<String> order_status) {
		this.order_status = order_status;
	}
	public ArrayList<String> getOrder_prod_idx() {
		return order_prod_idx;
	}
	public void setOrder_prod_idx(ArrayList<String> order_prod_idx) {
		this.order_prod_idx = order_prod_idx;
	}
	public ArrayList<String> getOrder_prod_name() {
		return order_prod_name;
	}
	public void setOrder_prod_name(ArrayList<String> order_prod_name) {
		this.order_prod_name = order_prod_name;
	}
	public ArrayList<String> getOrder_prod_opt() {
		return order_prod_opt;
	}
	public void setOrder_prod_opt(ArrayList<String> order_prod_opt) {
		this.order_prod_opt = order_prod_opt;
	}
	public ArrayList<Integer> getOrder_prod_cnt() {
		return order_prod_cnt;
	}
	public void setOrder_prod_cnt(ArrayList<Integer> order_prod_cnt) {
		this.order_prod_cnt = order_prod_cnt;
	}
	public ArrayList<Integer> getOrder_prod_price() {
		return order_prod_price;
	}
	public void setOrder_prod_price(ArrayList<Integer> order_prod_price) {
		this.order_prod_price = order_prod_price;
	}
	
	@Override
	public String toString() {
		return "OrderBean [order_imp_uid=" + order_imp_uid + ", order_merchant_uid=" + order_merchant_uid
				+ ", member_id=" + member_id + ", order_total_pay=" + order_total_pay + ", order_payment="
				+ order_payment + ", order_point=" + order_point + ", order_name=" + order_name + ", order_address="
				+ order_address + ", order_phone=" + order_phone + ", order_date=" + order_date + ", order_status="
				+ order_status + ", order_prod_idx=" + order_prod_idx + ", order_prod_name=" + order_prod_name
				+ ", order_prod_opt=" + order_prod_opt + ", order_prod_cnt=" + order_prod_cnt + ", order_prod_price="
				+ order_prod_price + ", review_write=" + review_write + "]";
	}
	

}
