package com.itwillbs.team1_final.vo;

import java.sql.Date;

public class StockVO {

	private int STOCK_CD;
	private int PRODUCT_CD;
	private int WH_LOC_IN_AREA_CD;
	private int STOCK_QTY;
	private String PRODUCT_NAME;
	private String WH_LOC_IN_AREA;
	private String WH_AREA;
	private int SOURCE_STOCK_CD;
	private int TARGET_STOCK_CD;
	private String STOCK_CONTROL_TYPE_CD;
	private String STOCK_CONTROL_TYPE_NAME;
	private int QTY;
	private String EMP_NUM;
	private String EMP_NAME;
	private Date STOCK_DATE;
	private String REMARKS;
	
	public int getSTOCK_CD() {
		return STOCK_CD;
	}
	public void setSTOCK_CD(int sTOCK_CD) {
		STOCK_CD = sTOCK_CD;
	}
	public int getPRODUCT_CD() {
		return PRODUCT_CD;
	}
	public void setPRODUCT_CD(int pRODUCT_CD) {
		PRODUCT_CD = pRODUCT_CD;
	}
	public int getWH_LOC_IN_AREA_CD() {
		return WH_LOC_IN_AREA_CD;
	}
	public void setWH_LOC_IN_AREA_CD(int wH_LOC_IN_AREA_CD) {
		WH_LOC_IN_AREA_CD = wH_LOC_IN_AREA_CD;
	}
	public int getSTOCK_QTY() {
		return STOCK_QTY;
	}
	public void setSTOCK_QTY(int sTOCK_QTY) {
		STOCK_QTY = sTOCK_QTY;
	}
	public String getPRODUCT_NAME() {
		return PRODUCT_NAME;
	}
	public void setPRODUCT_NAME(String pRODUCT_NAME) {
		PRODUCT_NAME = pRODUCT_NAME;
	}
	public String getWH_LOC_IN_AREA() {
		return WH_LOC_IN_AREA;
	}
	public void setWH_LOC_IN_AREA(String wH_LOC_IN_AREA) {
		WH_LOC_IN_AREA = wH_LOC_IN_AREA;
	}
	public String getWH_AREA() {
		return WH_AREA;
	}
	public void setWH_AREA(String wH_AREA) {
		WH_AREA = wH_AREA;
	}
	public int getSOURCE_STOCK_CD() {
		return SOURCE_STOCK_CD;
	}
	public void setSOURCE_STOCK_CD(int sOURCE_STOCK_CD) {
		SOURCE_STOCK_CD = sOURCE_STOCK_CD;
	}
	public int getTARGET_STOCK_CD() {
		return TARGET_STOCK_CD;
	}
	public void setTARGET_STOCK_CD(int tARGET_STOCK_CD) {
		TARGET_STOCK_CD = tARGET_STOCK_CD;
	}
	public String getSTOCK_CONTROL_TYPE_CD() {
		return STOCK_CONTROL_TYPE_CD;
	}
	public void setSTOCK_CONTROL_TYPE_CD(String sTOCK_CONTROL_TYPE_CD) {
		STOCK_CONTROL_TYPE_CD = sTOCK_CONTROL_TYPE_CD;
	}
	public String getSTOCK_CONTROL_TYPE_NAME() {
		return STOCK_CONTROL_TYPE_NAME;
	}
	public void setSTOCK_CONTROL_TYPE_NAME(String sTOCK_CONTROL_TYPE_NAME) {
		STOCK_CONTROL_TYPE_NAME = sTOCK_CONTROL_TYPE_NAME;
	}
	public int getQTY() {
		return QTY;
	}
	public void setQTY(int qTY) {
		QTY = qTY;
	}
	public String getEMP_NUM() {
		return EMP_NUM;
	}
	public void setEMP_NUM(String eMP_NUM) {
		EMP_NUM = eMP_NUM;
	}
	public String getEMP_NAME() {
		return EMP_NAME;
	}
	public void setEMP_NAME(String eMP_NAME) {
		EMP_NAME = eMP_NAME;
	}
	public Date getSTOCK_DATE() {
		return STOCK_DATE;
	}
	public void setSTOCK_DATE(Date sTOCK_DATE) {
		STOCK_DATE = sTOCK_DATE;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}
	
	@Override
	public String toString() {
		return "StockVO [STOCK_CD=" + STOCK_CD + ", PRODUCT_CD=" + PRODUCT_CD + ", WH_LOC_IN_AREA_CD="
				+ WH_LOC_IN_AREA_CD + ", STOCK_QTY=" + STOCK_QTY + ", PRODUCT_NAME=" + PRODUCT_NAME
				+ ", WH_LOC_IN_AREA=" + WH_LOC_IN_AREA + ", WH_AREA=" + WH_AREA + ", SOURCE_STOCK_CD=" + SOURCE_STOCK_CD
				+ ", TARGET_STOCK_CD=" + TARGET_STOCK_CD + ", STOCK_CONTROL_TYPE_CD=" + STOCK_CONTROL_TYPE_CD
				+ ", STOCK_CONTROL_TYPE_NAME=" + STOCK_CONTROL_TYPE_NAME + ", QTY=" + QTY + ", EMP_NUM=" + EMP_NUM
				+ ", EMP_NAME=" + EMP_NAME + ", STOCK_DATE=" + STOCK_DATE + ", REMARKS=" + REMARKS + "]";
	}
	
		
}
