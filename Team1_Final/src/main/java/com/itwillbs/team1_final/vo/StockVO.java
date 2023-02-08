package com.itwillbs.team1_final.vo;

import java.sql.Date;
import java.util.Arrays;

public class StockVO {

	private Integer STOCK_CD;
	private int PRODUCT_CD;
	private String PRODUCT_NAME;
	private String WH_NAME;
	private String WH_AREA;
	private String WH_LOC_IN_AREA;
	private Integer WH_LOC_IN_AREA_CD;
	private Integer SOURCE_STOCK_CD;
	private Integer TARGET_STOCK_CD;
	private String STOCK_CONTROL_TYPE_CD;
	private String STOCK_CONTROL_TYPE_NAME;
	private String IN_PD_SCHEDULE_CD;
	private Integer QTY;
	private Integer MOVE_QTY;
	private int STOCK_QTY;
	private String EMP_NUM;
	private String EMP_NAME;
	private Date STOCK_DATE;
	private String REMARKS;
	private String[] PRODUCT_NAME_Arr;
	private Integer[] STOCK_CD_Arr;
	private String[] STOCK_CONTROL_TYPE_CD_Arr;
	private int[] PRODUCT_CD_Arr;
	private Integer[] SOURCE_STOCK_CD_Arr;
	private Integer[] TARGET_STOCK_CD_Arr;
	private Integer[] QTY_Arr;
	private Integer[] MOVE_QTY_Arr;
	private String[] REMARKS_Arr;
	private Integer[] WH_LOC_IN_AREA_CD_Arr;
	private String[] IN_PD_SCHEDULE_CD_Arr;
	private String SIZE_DES; 
	
	
	public String getSIZE_DES() {
		return SIZE_DES;
	}
	public void setSIZE_DES(String sIZE_DES) {
		SIZE_DES = sIZE_DES;
	}
	public String getWH_NAME() {
		return WH_NAME;
	}
	public void setWH_NAME(String wH_NAME) {
		WH_NAME = wH_NAME;
	}
	
	public Integer[] getSTOCK_CD_Arr() {
		return STOCK_CD_Arr;
	}
	public void setSTOCK_CD_Arr(Integer[] sTOCK_CD_Arr) {
		STOCK_CD_Arr = sTOCK_CD_Arr;
	}
	public String[] getSTOCK_CONTROL_TYPE_CD_Arr() {
		return STOCK_CONTROL_TYPE_CD_Arr;
	}
	public void setSTOCK_CONTROL_TYPE_CD_Arr(String[] sTOCK_CONTROL_TYPE_CD_Arr) {
		STOCK_CONTROL_TYPE_CD_Arr = sTOCK_CONTROL_TYPE_CD_Arr;
	}
	public int[] getPRODUCT_CD_Arr() {
		return PRODUCT_CD_Arr;
	}
	public void setPRODUCT_CD_Arr(int[] pRODUCT_CD_Arr) {
		PRODUCT_CD_Arr = pRODUCT_CD_Arr;
	}
	public Integer[] getSOURCE_STOCK_CD_Arr() {
		return SOURCE_STOCK_CD_Arr;
	}
	public void setSOURCE_STOCK_CD_Arr(Integer[] sOURCE_STOCK_CD_Arr) {
		SOURCE_STOCK_CD_Arr = sOURCE_STOCK_CD_Arr;
	}
	public Integer[] getTARGET_STOCK_CD_Arr() {
		return TARGET_STOCK_CD_Arr;
	}
	public void setTARGET_STOCK_CD_Arr(Integer[] tARGET_STOCK_CD_Arr) {
		TARGET_STOCK_CD_Arr = tARGET_STOCK_CD_Arr;
	}
	public Integer[] getQTY_Arr() {
		return QTY_Arr;
	}
	public void setQTY_Arr(Integer[] qTY_Arr) {
		QTY_Arr = qTY_Arr;
	}
	public Integer[] getMOVE_QTY_Arr() {
		return MOVE_QTY_Arr;
	}
	public void setMOVE_QTY_Arr(Integer[] mOVE_QTY_Arr) {
		MOVE_QTY_Arr = mOVE_QTY_Arr;
	}
	public String[] getREMARKS_Arr() {
		return REMARKS_Arr;
	}
	public void setREMARKS_Arr(String[] rEMARKS_Arr) {
		REMARKS_Arr = rEMARKS_Arr;
	}
	
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
	public Integer getSOURCE_STOCK_CD() {
		return SOURCE_STOCK_CD;
	}
	public void setSOURCE_STOCK_CD(Integer sOURCE_STOCK_CD) {
		SOURCE_STOCK_CD = sOURCE_STOCK_CD;
	}
	public Integer getTARGET_STOCK_CD() {
		return TARGET_STOCK_CD;
	}
	public void setTARGET_STOCK_CD(Integer tARGET_STOCK_CD) {
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
	
	
	public void setSTOCK_CD(Integer sTOCK_CD) {
		STOCK_CD = sTOCK_CD;
	}
	
	public int getMOVE_QTY() {
		return MOVE_QTY;
	}
	public void setMOVE_QTY(int mOVE_QTY) {
		MOVE_QTY = mOVE_QTY;
	}
	public Integer[] getWH_LOC_IN_AREA_CD_Arr() {
		return WH_LOC_IN_AREA_CD_Arr;
	}
	public void setWH_LOC_IN_AREA_CD_Arr(Integer[] wH_LOC_IN_AREA_CD_Arr) {
		WH_LOC_IN_AREA_CD_Arr = wH_LOC_IN_AREA_CD_Arr;
	}
	public void setWH_LOC_IN_AREA_CD(Integer wH_LOC_IN_AREA_CD) {
		WH_LOC_IN_AREA_CD = wH_LOC_IN_AREA_CD;
	}
	public void setQTY(Integer qTY) {
		QTY = qTY;
	}
	public void setMOVE_QTY(Integer mOVE_QTY) {
		MOVE_QTY = mOVE_QTY;
	}
	@Override
	public String toString() {
		return "StockVO [STOCK_CD=" + STOCK_CD + ", PRODUCT_CD=" + PRODUCT_CD + ", PRODUCT_NAME=" + PRODUCT_NAME
				+ ", WH_NAME=" + WH_NAME + ", WH_AREA=" + WH_AREA + ", WH_LOC_IN_AREA=" + WH_LOC_IN_AREA
				+ ", WH_LOC_IN_AREA_CD=" + WH_LOC_IN_AREA_CD + ", SOURCE_STOCK_CD=" + SOURCE_STOCK_CD
				+ ", TARGET_STOCK_CD=" + TARGET_STOCK_CD + ", STOCK_CONTROL_TYPE_CD=" + STOCK_CONTROL_TYPE_CD
				+ ", STOCK_CONTROL_TYPE_NAME=" + STOCK_CONTROL_TYPE_NAME + ", IN_PD_SCHEDULE_CD=" + IN_PD_SCHEDULE_CD
				+ ", QTY=" + QTY + ", MOVE_QTY=" + MOVE_QTY + ", STOCK_QTY=" + STOCK_QTY + ", EMP_NUM=" + EMP_NUM
				+ ", EMP_NAME=" + EMP_NAME + ", STOCK_DATE=" + STOCK_DATE + ", REMARKS=" + REMARKS
				+ ", PRODUCT_NAME_Arr=" + Arrays.toString(PRODUCT_NAME_Arr) + ", STOCK_CD_Arr="
				+ Arrays.toString(STOCK_CD_Arr) + ", STOCK_CONTROL_TYPE_CD_Arr="
				+ Arrays.toString(STOCK_CONTROL_TYPE_CD_Arr) + ", PRODUCT_CD_Arr=" + Arrays.toString(PRODUCT_CD_Arr)
				+ ", SOURCE_STOCK_CD_Arr=" + Arrays.toString(SOURCE_STOCK_CD_Arr) + ", TARGET_STOCK_CD_Arr="
				+ Arrays.toString(TARGET_STOCK_CD_Arr) + ", QTY_Arr=" + Arrays.toString(QTY_Arr) + ", MOVE_QTY_Arr="
				+ Arrays.toString(MOVE_QTY_Arr) + ", REMARKS_Arr=" + Arrays.toString(REMARKS_Arr)
				+ ", WH_LOC_IN_AREA_CD_Arr=" + Arrays.toString(WH_LOC_IN_AREA_CD_Arr) + ", IN_PD_SCHEDULE_CD_Arr="
				+ Arrays.toString(IN_PD_SCHEDULE_CD_Arr) + ", SIZE_DES=" + SIZE_DES + "]";
	}
	public String getIN_PD_SCHEDULE_CD() {
		return IN_PD_SCHEDULE_CD;
	}
	public void setIN_PD_SCHEDULE_CD(String iN_PD_SCHEDULE_CD) {
		IN_PD_SCHEDULE_CD = iN_PD_SCHEDULE_CD;
	}
	public String[] getPRODUCT_NAME_Arr() {
		return PRODUCT_NAME_Arr;
	}
	public void setPRODUCT_NAME_Arr(String[] pRODUCT_NAME_Arr) {
		PRODUCT_NAME_Arr = pRODUCT_NAME_Arr;
	}
	public String[] getIN_PD_SCHEDULE_CD_Arr() {
		return IN_PD_SCHEDULE_CD_Arr;
	}
	public void setIN_PD_SCHEDULE_CD_Arr(String[] iN_PD_SCHEDULE_CD_Arr) {
		IN_PD_SCHEDULE_CD_Arr = iN_PD_SCHEDULE_CD_Arr;
	}
	
	
	
}
