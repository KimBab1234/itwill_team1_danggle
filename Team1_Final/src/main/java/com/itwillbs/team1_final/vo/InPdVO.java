package com.itwillbs.team1_final.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InPdVO {
	
	private String IN_PD_SCHEDULE_CD; // 입고예정코드
	private int PRODUCT_CD; // 품목코드
	private String PRODUCT_NAME; // 품목명
	private String SIZE_DES; // 규격
	private int IN_SCHEDULE_QTY; // 입고예정수량
	private int IN_QTY; // 입고수량 
	
	private Date IN_PD_DATE; // 납기일자
	private String IN_PD_REMARKS; // 적요
	private String IN_PD_COMPLETE; // 진행상태

	
	private String IN_TYPE_CD;
	private String BUSINESS_NO;
	private String EMP_NUM;
	private String REMARKS;
	
	private Date IN_DATE;
	private String EMP_NAME;
	private String CUST_NAME;
	
	private String in_product_cd;
	
	private int TOTAL_QTY;
	
	private String wh_loc_in_area; // 위치명
	private String wh_name; // 창고 이름
	private String wh_area; // 창고구역
	private int stock_cd; // 재고번호
	private int stock_qty;
	private int WH_LOC_IN_AREA_CD;
	
	
	
	
	
	public int getWH_LOC_IN_AREA_CD() {
		return WH_LOC_IN_AREA_CD;
	}
	public void setWH_LOC_IN_AREA_CD(int wH_LOC_IN_AREA_CD) {
		WH_LOC_IN_AREA_CD = wH_LOC_IN_AREA_CD;
	}

	public int getStock_qty() {
		return stock_qty;
	}
	public void setStock_qty(int stock_qty) {
		this.stock_qty = stock_qty;
	}
	public int getStock_cd() {
		return stock_cd;
	}
	public void setStock_cd(int stock_cd) {
		this.stock_cd = stock_cd;
	}
	public String getWh_loc_in_area() {
		return wh_loc_in_area;
	}
	public void setWh_loc_in_area(String wh_loc_in_area) {
		this.wh_loc_in_area = wh_loc_in_area;
	}
	public String getWh_name() {
		return wh_name;
	}
	public void setWh_name(String wh_name) {
		this.wh_name = wh_name;
	}
	public String getWh_area() {
		return wh_area;
	}
	public void setWh_area(String wh_area) {
		this.wh_area = wh_area;
	}
	
	
	public int getTOTAL_QTY() {
		return TOTAL_QTY;
	}
	public void setTOTAL_QTY(int tOTAL_QTY) {
		TOTAL_QTY = tOTAL_QTY;
	}
	public String getIn_product_cd() {
		return in_product_cd;
	}
	public void setIn_product_cd(String in_product_cd) {
		this.in_product_cd = in_product_cd;
	}
	public String getIN_TYPE_CD() {
		return IN_TYPE_CD;
	}
	public void setIN_TYPE_CD(String iN_TYPE_CD) {
		IN_TYPE_CD = iN_TYPE_CD;
	}
	public String getBUSINESS_NO() {
		return BUSINESS_NO;
	}
	public void setBUSINESS_NO(String bUSINESS_NO) {
		BUSINESS_NO = bUSINESS_NO;
	}
	public String getEMP_NUM() {
		return EMP_NUM;
	}
	public void setEMP_NUM(String eMP_NUM) {
		EMP_NUM = eMP_NUM;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}
	public Date getIN_DATE() {
		return IN_DATE;
	}
	public void setIN_DATE(Date iN_DATE) {
		IN_DATE = iN_DATE;
	}
	public String getEMP_NAME() {
		return EMP_NAME;
	}
	public void setEMP_NAME(String eMP_NAME) {
		EMP_NAME = eMP_NAME;
	}
	public String getCUST_NAME() {
		return CUST_NAME;
	}
	public void setCUST_NAME(String cUST_NAME) {
		CUST_NAME = cUST_NAME;
	}
	public String getIN_PD_SCHEDULE_CD() {
		return IN_PD_SCHEDULE_CD;
	}
	public void setIN_PD_SCHEDULE_CD(String iN_PD_SCHEDULE_CD) {
		IN_PD_SCHEDULE_CD = iN_PD_SCHEDULE_CD;
	}
	public Date getIN_PD_DATE() {
		return IN_PD_DATE;
	}
	public void setIN_PD_DATE(Date iN_PD_DATE) {
		IN_PD_DATE = iN_PD_DATE;
	}
	public String getIN_PD_REMARKS() {
		return IN_PD_REMARKS;
	}
	public void setIN_PD_REMARKS(String iN_PD_REMARKS) {
		IN_PD_REMARKS = iN_PD_REMARKS;
	}

	public String getIN_PD_COMPLETE() {
		return IN_PD_COMPLETE;
	}
	public void setIN_PD_COMPLETE(String iN_PD_COMPLETE) {
		IN_PD_COMPLETE = iN_PD_COMPLETE;
	}

	
	public int getPRODUCT_CD() {
		return PRODUCT_CD;
	}
	public void setPRODUCT_CD(int pRODUCT_CD) {
		PRODUCT_CD = pRODUCT_CD;
	}
	public String getPRODUCT_NAME() {
		return PRODUCT_NAME;
	}
	public void setPRODUCT_NAME(String pRODUCT_NAME) {
		PRODUCT_NAME = pRODUCT_NAME;
	}
	public String getSIZE_DES() {
		return SIZE_DES;
	}
	public void setSIZE_DES(String sIZE_DES) {
		SIZE_DES = sIZE_DES;
	}
	public int getIN_SCHEDULE_QTY() {
		return IN_SCHEDULE_QTY;
	}
	public void setIN_SCHEDULE_QTY(int iN_SCHEDULE_QTY) {
		IN_SCHEDULE_QTY = iN_SCHEDULE_QTY;
	}
	public int getIN_QTY() {
		return IN_QTY;
	}
	public void setIN_QTY(int iN_QTY) {
		IN_QTY = iN_QTY;
	}
	
	@Override
	public String toString() {
		return "InPdVO [IN_PD_SCHEDULE_CD=" + IN_PD_SCHEDULE_CD + ", PRODUCT_CD=" + PRODUCT_CD + ", PRODUCT_NAME="
				+ PRODUCT_NAME + ", SIZE_DES=" + SIZE_DES + ", IN_SCHEDULE_QTY=" + IN_SCHEDULE_QTY + ", IN_QTY="
				+ IN_QTY + ", IN_PD_DATE=" + IN_PD_DATE + ", IN_PD_REMARKS=" + IN_PD_REMARKS + ", IN_PD_COMPLETE="
				+ IN_PD_COMPLETE + ", IN_TYPE_CD=" + IN_TYPE_CD + ", BUSINESS_NO=" + BUSINESS_NO + ", EMP_NUM="
				+ EMP_NUM + ", REMARKS=" + REMARKS + ", IN_DATE=" + IN_DATE + ", EMP_NAME=" + EMP_NAME + ", CUST_NAME="
				+ CUST_NAME + ", in_product_cd=" + in_product_cd + ", TOTAL_QTY=" + TOTAL_QTY + ", wh_loc_in_area="
				+ wh_loc_in_area + ", wh_name=" + wh_name + ", wh_area=" + wh_area + ", stock_cd=" + stock_cd
				+ ", stock_qty=" + stock_qty + ", WH_LOC_IN_AREA_CD=" + WH_LOC_IN_AREA_CD + ", toString()="
				+ super.toString() + "]";
	}
	
	

	
}
