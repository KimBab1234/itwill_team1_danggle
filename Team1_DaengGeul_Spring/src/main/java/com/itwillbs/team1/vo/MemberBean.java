package com.itwillbs.team1.vo;
/*
 * member 테이블 정의
 * ---------------------------------
	CREATE TABLE member (
		member_id VARCHAR(50) PRIMARY KEY,
		member_passwd VARCHAR(200) NOT NULL,
		member_name VARCHAR(15) NOT NULL,
		member_phone VARCHAR(20) NOT NULL UNIQUE,
		member_email VARCHAR(50) NOT NULL UNIQUE,
		member_postcode VARCHAR(50) NOT NULL,
		member_roadAddress VARCHAR(50) NOT NULL,
		member_jibunAddress VARCHAR(50) NOT NULL,
		member_addressDetail VARCHAR(200) NOT NULL,
		member_join_date VARCHAR(20) NOT NULL,
		member_gender VARCHAR(1) NOT NULL,
		member_point INT,
		member_coupon VARCHAR(20),
		member_authStatus varchar(2) NOT NULL
			default 'N'
			CONSTRAINT mAuth_yn CHECK(member_authStatus IN ('Y', 'N'))
 	);
 */

import java.sql.Date;

public class MemberBean {
	private String member_id;
	private String member_passwd;
	private String member_name;
	private String member_phone;
	private String member_email;
	private String member_postcode;
	private String member_roadAddress;
	private String member_jibunAddress;
	private String member_addressDetail;
	private Date member_join_date;
	private String member_gender;
	private int member_point;
	private String member_coupon;
	private String member_authStatus;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_passwd() {
		return member_passwd;
	}
	public void setMember_passwd(String member_passwd) {
		this.member_passwd = member_passwd;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_postcode() {
		return member_postcode;
	}
	public void setMember_postcode(String member_postcode) {
		this.member_postcode = member_postcode;
	}
	public String getMember_roadAddress() {
		return member_roadAddress;
	}
	public void setMember_roadAddress(String member_roadAddress) {
		this.member_roadAddress = member_roadAddress;
	}
	public String getMember_jibunAddress() {
		return member_jibunAddress;
	}
	public void setMember_jibunAddress(String member_jibunAddress) {
		this.member_jibunAddress = member_jibunAddress;
	}
	public String getMember_addressDetail() {
		return member_addressDetail;
	}
	public void setMember_addressDetail(String member_addressDetail) {
		this.member_addressDetail = member_addressDetail;
	}
	public Date getMember_join_date() {
		return member_join_date;
	}
	public void setMember_join_date(Date member_join_date) {
		this.member_join_date = member_join_date;
	}
	public String getMember_gender() {
		return member_gender;
	}
	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}
	public int getMember_point() {
		return member_point;
	}
	public void setMember_point(int member_point) {
		this.member_point = member_point;
	}
	public String getMember_coupon() {
		return member_coupon;
	}
	public void setMember_coupon(String member_coupon) {
		this.member_coupon = member_coupon;
	}
	public String getMember_authStatus() {
		return member_authStatus;
	}
	public void setMember_authStatus(String member_authStatus) {
		this.member_authStatus = member_authStatus;
	}
	
	@Override
	public String toString() {
		return "MemberBean [member_id=" + member_id + ", member_passwd=" + member_passwd + ", member_name="
				+ member_name + ", member_phone=" + member_phone + ", member_email=" + member_email
				+ ", member_postcode=" + member_postcode + ", member_roadAddress=" + member_roadAddress
				+ ", member_jibunAddress=" + member_jibunAddress + ", member_addressDetail=" + member_addressDetail
				+ ", member_join_date=" + member_join_date + ", member_gender=" + member_gender + ", member_point="
				+ member_point + ", member_coupon=" + member_coupon + ", member_authStatus=" + member_authStatus + "]";
	}
	
}