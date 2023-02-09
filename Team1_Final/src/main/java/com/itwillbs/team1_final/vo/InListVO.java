package com.itwillbs.team1_final.vo;

import java.sql.Date;

public class InListVO {

	private String IN_SCHEDULE_CD; // 입고예정코드
	private String IN_TYPE_NAME; //입고유형명
	private String CUST_NAME; // 거래처명
	private String EMP_NAME; // 사원명
	private String PRODUCT_NAME; // 품목명
	private String SIZE_DES; // 규격
	private Date IN_DATE; // 납기일자
	private int TOTAL_QTY; // 총 입고예정수량
	private String IN_COMPLETE; // 진행상태
	
	//  입고 처리 목록
	private String IN_PD_SCHEDULE_CD; // 입고 예정 코드
	private Date IN_PD_DATE; // 납기일자
	private int IN_SCHEDULE_QTY; // 입고 예정 수량
	private int IN_QTY; // 입고 수량
	private String IN_PD_REMARKS; // 적요
	private int PRODUCT_CD;
	
	
	
	
	
	
	public int getPRODUCT_CD() {
		return PRODUCT_CD;
	}

	public void setPRODUCT_CD(int pRODUCT_CD) {
		PRODUCT_CD = pRODUCT_CD;
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

	public String getIN_PD_REMARKS() {
		return IN_PD_REMARKS;
	}

	public void setIN_PD_REMARKS(String iN_PD_REMARKS) {
		IN_PD_REMARKS = iN_PD_REMARKS;
	}

	public String getIN_SCHEDULE_CD() {
		return IN_SCHEDULE_CD;
	}
	
	public void setIN_SCHEDULE_CD(String iN_SCHEDULE_CD) {
		IN_SCHEDULE_CD = iN_SCHEDULE_CD;
	}
	
	public Date getIN_DATE() {
		return IN_DATE;
	}
	
	public void setIN_DATE(Date iN_DATE) {
		IN_DATE = iN_DATE;
	}
	
	public int getTOTAL_QTY() {
		return TOTAL_QTY;
	}
	
	public void setTOTAL_QTY(int tOTAL_QTY) {
		TOTAL_QTY = tOTAL_QTY;
	}
	
	public String getIN_COMPLETE() {
		return IN_COMPLETE;
	}
	
	public void setIN_COMPLETE(String iN_COMPLETE) {
		IN_COMPLETE = iN_COMPLETE;
	}
	
	public String getIN_TYPE_NAME() {
		return IN_TYPE_NAME;
	}
	
	public void setIN_TYPE_NAME(String iN_TYPE_NAME) {
		IN_TYPE_NAME = iN_TYPE_NAME;
	}
	
	public String getCUST_NAME() {
		return CUST_NAME;
	}
	
	public void setCUST_NAME(String cUST_NAME) {
		CUST_NAME = cUST_NAME;
	}
	
	public String getEMP_NAME() {
		return EMP_NAME;
	}
	
	public void setEMP_NAME(String eMP_NAME) {
		EMP_NAME = eMP_NAME;
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

	@Override
	public String toString() {
		return "InListVO [IN_SCHEDULE_CD=" + IN_SCHEDULE_CD + ", IN_TYPE_NAME=" + IN_TYPE_NAME + ", CUST_NAME="
				+ CUST_NAME + ", EMP_NAME=" + EMP_NAME + ", PRODUCT_NAME=" + PRODUCT_NAME + ", SIZE_DES=" + SIZE_DES
				+ ", IN_DATE=" + IN_DATE + ", TOTAL_QTY=" + TOTAL_QTY + ", IN_COMPLETE=" + IN_COMPLETE
				+ ", IN_PD_SCHEDULE_CD=" + IN_PD_SCHEDULE_CD + ", IN_PD_DATE=" + IN_PD_DATE + ", IN_SCHEDULE_QTY="
				+ IN_SCHEDULE_QTY + ", IN_QTY=" + IN_QTY + ", IN_PD_REMARKS=" + IN_PD_REMARKS + ", toString()="
				+ super.toString() + "]";
	}

	

	
	
}
