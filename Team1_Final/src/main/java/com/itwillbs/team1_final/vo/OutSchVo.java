package com.itwillbs.team1_final.vo;

import java.sql.Date;
import java.util.List;


public class OutSchVo {

	private String OUT_SCHEDULE_CD;
	private String OUT_TYPE_NAME;
	private String BUSINESS_NO;
	private String EMP_NUM;
	private String OUT_COMPLETE;
	private Date OUT_DATE;
	private String REMARKS;
	private int TOTAL_QTY;
	
	private List<Integer> PRODUCT_CD;
	private List<String> PRODUCT_NAME;
	private List<String> SIZE_DES;
	private List<Integer> OUT_SCHEDULE_QTY;
	private int OUT_QTY; 
	private List<Date> PD_OUT_DATE;
	private List<String> PD_REMARKS;
	private String STOCK_CD;
	
	public String getOUT_SCHEDULE_CD() {
		return OUT_SCHEDULE_CD;
	}
	public void setOUT_SCHEDULE_CD(String oUT_SCHEDULE_CD) {
		OUT_SCHEDULE_CD = oUT_SCHEDULE_CD;
	}
	public String getOUT_TYPE_NAME() {
		return OUT_TYPE_NAME;
	}
	public void setOUT_TYPE_NAME(String oUT_TYPE_NAME) {
		OUT_TYPE_NAME = oUT_TYPE_NAME;
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
	public String getOUT_COMPLETE() {
		return OUT_COMPLETE;
	}
	public void setOUT_COMPLETE(String oUT_COMPLETE) {
		OUT_COMPLETE = oUT_COMPLETE;
	}
	public Date getOUT_DATE() {
		return OUT_DATE;
	}
	public void setOUT_DATE(Date oUT_DATE) {
		OUT_DATE = oUT_DATE;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}
	public int getTOTAL_QTY() {
		return TOTAL_QTY;
	}
	public void setTOTAL_QTY(int tOTAL_QTY) {
		TOTAL_QTY = tOTAL_QTY;
	}
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
	public List<Integer> getOUT_SCHEDULE_QTY() {
		return OUT_SCHEDULE_QTY;
	}
	public void setOUT_SCHEDULE_QTY(List<Integer> oUT_SCHEDULE_QTY) {
		OUT_SCHEDULE_QTY = oUT_SCHEDULE_QTY;
	}
	public int getOUT_QTY() {
		return OUT_QTY;
	}
	public void setOUT_QTY(int oUT_QTY) {
		OUT_QTY = oUT_QTY;
	}
	public List<Date> getPD_OUT_DATE() {
		return PD_OUT_DATE;
	}
	public void setPD_OUT_DATE(List<Date> pD_OUT_DATE) {
		PD_OUT_DATE = pD_OUT_DATE;
	}
	public List<String> getPD_REMARKS() {
		return PD_REMARKS;
	}
	public void setPD_REMARKS(List<String> pD_REMARKS) {
		PD_REMARKS = pD_REMARKS;
	}
	public String getSTOCK_CD() {
		return STOCK_CD;
	}
	public void setSTOCK_CD(String sTOCK_CD) {
		STOCK_CD = sTOCK_CD;
	}
	
	@Override
	public String toString() {
		return "OutSchVo [OUT_SCHEDULE_CD=" + OUT_SCHEDULE_CD + ", OUT_TYPE_NAME=" + OUT_TYPE_NAME + ", BUSINESS_NO="
				+ BUSINESS_NO + ", EMP_NUM=" + EMP_NUM + ", OUT_COMPLETE=" + OUT_COMPLETE + ", OUT_DATE=" + OUT_DATE
				+ ", REMARKS=" + REMARKS + ", TOTAL_QTY=" + TOTAL_QTY + ", PRODUCT_CD=" + PRODUCT_CD + ", PRODUCT_NAME="
				+ PRODUCT_NAME + ", SIZE_DES=" + SIZE_DES + ", OUT_SCHEDULE_QTY=" + OUT_SCHEDULE_QTY + ", OUT_QTY="
				+ OUT_QTY + ", PD_OUT_DATE=" + PD_OUT_DATE + ", PD_REMARKS=" + PD_REMARKS + ", STOCK_CD=" + STOCK_CD
				+ "]";
	}
	
}
