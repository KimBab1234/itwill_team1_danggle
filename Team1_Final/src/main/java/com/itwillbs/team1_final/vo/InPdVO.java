package com.itwillbs.team1_final.vo;

import java.sql.Date;

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
	private String STOCK_CD; // 재고번호
	
	
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
	public String getSTOCK_CD() {
		return STOCK_CD;
	}
	public void setSTOCK_CD(String sTOCK_CD) {
		STOCK_CD = sTOCK_CD;
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
				+ IN_PD_COMPLETE + ", STOCK_CD=" + STOCK_CD + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
	
}
