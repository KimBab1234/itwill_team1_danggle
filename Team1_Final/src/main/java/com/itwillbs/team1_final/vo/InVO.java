package com.itwillbs.team1_final.vo;

import java.sql.Date;
import java.util.List;

public class InVO {
	
	private String IN_SCHEDULE_CD; // 입고예정코드
	private String IN_TYPE_CD; // 입고유형코드
	private String BUSINESS_NO; // 거래처코드
	private String EMP_NUM; // 담당자(사원번호)
	private Date IN_DATE; // 납기일자
	private String REMARKS; // 적요
	private String IN_COMPLETE; // 진행상태
	private String IN_TYPE_NAME; //입고유형명
	private int TOTAL_QTY; // 총 입고예정수량
	
	// 입고예정품목
	private List<Integer> PRODUCT_CD; // 품목코드
	private List<String> PRODUCT_NAME; // 품목명
	private List<String> SIZE_DES; // 규격
	private List<Integer> IN_SCHEDULE_QTY; // 입고예정수량
	private int IN_QTY; // 입고수량 
	private List<Date> IN_PD_DATE; // 납기일자
	private List<String> IN_PD_REMARKS; // 적요
	private String STOCK_CD; // 재고번호
	
	
	

	public List<Integer> getPRODUCT_CD() {
		return PRODUCT_CD;
	}

	public void setPRODUCT_CD(List<Integer> pRODUCT_CD) {
		PRODUCT_CD = pRODUCT_CD;
	}

	public List<String> getPRODUCT_NAME() {
		return PRODUCT_NAME;
	}

	public void setPRODUCT_NAME(List<String> pRODUCT_NAME) {
		PRODUCT_NAME = pRODUCT_NAME;
	}

	public List<String> getSIZE_DES() {
		return SIZE_DES;
	}

	public void setSIZE_DES(List<String> sIZE_DES) {
		SIZE_DES = sIZE_DES;
	}

	public List<Integer> getIN_SCHEDULE_QTY() {
		return IN_SCHEDULE_QTY;
	}

	public void setIN_SCHEDULE_QTY(List<Integer> iN_SCHEDULE_QTY) {
		IN_SCHEDULE_QTY = iN_SCHEDULE_QTY;
	}

	public List<Date> getIN_PD_DATE() {
		return IN_PD_DATE;
	}

	public void setIN_PD_DATE(List<Date> iN_PD_DATE) {
		IN_PD_DATE = iN_PD_DATE;
	}

	public List<String> getIN_PD_REMARKS() {
		return IN_PD_REMARKS;
	}

	public void setIN_PD_REMARKS(List<String> iN_PD_REMARKS) {
		IN_PD_REMARKS = iN_PD_REMARKS;
	}

	public int getIN_QTY() {
		return IN_QTY;
	}

	public void setIN_QTY(int iN_QTY) {
		IN_QTY = iN_QTY;
	}

	public String getSTOCK_CD() {
		return STOCK_CD;
	}

	public void setSTOCK_CD(String sTOCK_CD) {
		STOCK_CD = sTOCK_CD;
	}

	public int getTOTAL_QTY() {
		return TOTAL_QTY;
	}

	public void setTOTAL_QTY(int tOTAL_QTY) {
		TOTAL_QTY = tOTAL_QTY;
	}

	public String getIN_SCHEDULE_CD() {
		return IN_SCHEDULE_CD;
	}

	public void setIN_SCHEDULE_CD(String iN_SCHEDULE_CD) {
		IN_SCHEDULE_CD = iN_SCHEDULE_CD;
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

	public Date getIN_DATE() {
		return IN_DATE;
	}

	public void setIN_DATE(Date iN_DATE) {
		IN_DATE = iN_DATE;
	}

	public String getREMARKS() {
		return REMARKS;
	}

	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
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

	@Override
	public String toString() {
		return "InVO [IN_SCHEDULE_CD=" + IN_SCHEDULE_CD + ", IN_TYPE_CD=" + IN_TYPE_CD + ", BUSINESS_NO=" + BUSINESS_NO
				+ ", EMP_NUM=" + EMP_NUM + ", IN_DATE=" + IN_DATE + ", REMARKS=" + REMARKS + ", IN_COMPLETE="
				+ IN_COMPLETE + ", IN_TYPE_NAME=" + IN_TYPE_NAME + ", TOTAL_QTY=" + TOTAL_QTY + ", PRODUCT_CD="
				+ PRODUCT_CD + ", PRODUCT_NAME=" + PRODUCT_NAME + ", SIZE_DES=" + SIZE_DES + ", IN_SCHEDULE_QTY="
				+ IN_SCHEDULE_QTY + ", IN_QTY=" + IN_QTY + ", IN_PD_DATE=" + IN_PD_DATE + ", IN_PD_REMARKS="
				+ IN_PD_REMARKS + ", STOCK_CD=" + STOCK_CD + ", toString()=" + super.toString() + "]";
	}

	
}
