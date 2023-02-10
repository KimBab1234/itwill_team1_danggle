package com.itwillbs.team1_final.vo;

import java.util.Date;

public class OutPdVO {

	private String OUT_SCHEDULE_CD;
	private int PRODUCT_CD;
	private String PRODUCT_NAME;
	private String SIZE_DES;
	private int OUT_SCHEDULE_QTY;
	private int OUT_QTY;
	private Date PD_OUT_DATE;
	private String PD_REMARKS;
	private String OUT_COMPLETE;
	private String STOCK_CD;
	private String STOCK_QTY;
	private String WH_NAME;
	private String WH_AREA;
	private String WH_LOC_IN_AREA;
	
	
	public String getOUT_SCHEDULE_CD() {
		return OUT_SCHEDULE_CD;
	}
	public void setOUT_SCHEDULE_CD(String oUT_SCHEDULE_CD) {
		OUT_SCHEDULE_CD = oUT_SCHEDULE_CD;
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
	public int getOUT_SCHEDULE_QTY() {
		return OUT_SCHEDULE_QTY;
	}
	public void setOUT_SCHEDULE_QTY(int oUT_SCHEDULE_QTY) {
		OUT_SCHEDULE_QTY = oUT_SCHEDULE_QTY;
	}
	public int getOUT_QTY() {
		return OUT_QTY;
	}
	public void setOUT_QTY(int oUT_QTY) {
		OUT_QTY = oUT_QTY;
	}
	public Date getPD_OUT_DATE() {
		return PD_OUT_DATE;
	}
	public void setPD_OUT_DATE(Date pD_OUT_DATE) {
		PD_OUT_DATE = pD_OUT_DATE;
	}
	public String getPD_REMARKS() {
		return PD_REMARKS;
	}
	public void setPD_REMARKS(String pD_REMARKS) {
		PD_REMARKS = pD_REMARKS;
	}
	public String getOUT_COMPLETE() {
		return OUT_COMPLETE;
	}
	public void setOUT_COMPLETE(String oUT_COMPLETE) {
		OUT_COMPLETE = oUT_COMPLETE;
	}
	public String getSTOCK_CD() {
		return STOCK_CD;
	}
	public void setSTOCK_CD(String sTOCK_CD) {
		STOCK_CD = sTOCK_CD;
	}
	public String getSTOCK_QTY() {
		return STOCK_QTY;
	}
	public void setSTOCK_QTY(String sTOCK_QTY) {
		STOCK_QTY = sTOCK_QTY;
	}
	public String getWH_NAME() {
		return WH_NAME;
	}
	public void setWH_NAME(String wH_NAME) {
		WH_NAME = wH_NAME;
	}
	public String getWH_AREA() {
		return WH_AREA;
	}
	public void setWH_AREA(String wH_AREA) {
		WH_AREA = wH_AREA;
	}
	public String getWH_LOC_IN_AREA() {
		return WH_LOC_IN_AREA;
	}
	public void setWH_LOC_IN_AREA(String wH_LOC_IN_AREA) {
		WH_LOC_IN_AREA = wH_LOC_IN_AREA;
	}
	
	@Override
	public String toString() {
		return "OutPdVO [OUT_SCHEDULE_CD=" + OUT_SCHEDULE_CD + ", PRODUCT_CD=" + PRODUCT_CD + ", PRODUCT_NAME="
				+ PRODUCT_NAME + ", SIZE_DES=" + SIZE_DES + ", OUT_SCHEDULE_QTY=" + OUT_SCHEDULE_QTY + ", OUT_QTY="
				+ OUT_QTY + ", PD_OUT_DATE=" + PD_OUT_DATE + ", PD_REMARKS=" + PD_REMARKS + ", OUT_COMPLETE="
				+ OUT_COMPLETE + ", STOCK_CD=" + STOCK_CD + ", STOCK_QTY=" + STOCK_QTY + ", WH_NAME=" + WH_NAME
				+ ", WH_AREA=" + WH_AREA + ", WH_LOC_IN_AREA=" + WH_LOC_IN_AREA + "]";
	}
	
}
