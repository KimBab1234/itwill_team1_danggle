package com.itwillbs.team1_final.vo;

import java.sql.Date;
import java.util.List;


public class OutSchVo {

	private String OUT_SCHEDULE_CD;
	private String OUT_TYPE_NAME;
	private String BUSINESS_NO;
	private String EMP_NUM;
	private String EMP_NAME;
	private String OUT_COMPLETE;
	private Date OUT_DATE;
	private String REMARKS;
	private int TOTAL_QTY;
	
	private String PRODUCT_NAME;
	private String SIZE_DES;
	
	private List<Integer> PRODUCT_CD_Arr;
	private List<String> PRODUCT_NAME_Arr;
	private List<String> SIZE_DES_Arr;
	private List<Integer> OUT_SCHEDULE_QTY_Arr;
	private List<Date> PD_OUT_DATE_Arr;
	private List<String> PD_REMARKS_Arr;
	
	private int OUT_QTY_Arr;
	private String STOCK_CD_Arr;
	
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
	public String getEMP_NAME() {
		return EMP_NAME;
	}
	public void setEMP_NAME(String eMP_NAME) {
		EMP_NAME = eMP_NAME;
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
	public List<Integer> getPRODUCT_CD_Arr() {
		return PRODUCT_CD_Arr;
	}
	public void setPRODUCT_CD_Arr(List<Integer> pRODUCT_CD_Arr) {
		PRODUCT_CD_Arr = pRODUCT_CD_Arr;
	}
	public List<String> getPRODUCT_NAME_Arr() {
		return PRODUCT_NAME_Arr;
	}
	public void setPRODUCT_NAME_Arr(List<String> pRODUCT_NAME_Arr) {
		PRODUCT_NAME_Arr = pRODUCT_NAME_Arr;
	}
	public List<String> getSIZE_DES_Arr() {
		return SIZE_DES_Arr;
	}
	public void setSIZE_DES_Arr(List<String> sIZE_DES_Arr) {
		SIZE_DES_Arr = sIZE_DES_Arr;
	}
	public List<Integer> getOUT_SCHEDULE_QTY_Arr() {
		return OUT_SCHEDULE_QTY_Arr;
	}
	public void setOUT_SCHEDULE_QTY_Arr(List<Integer> oUT_SCHEDULE_QTY_Arr) {
		OUT_SCHEDULE_QTY_Arr = oUT_SCHEDULE_QTY_Arr;
	}
	public int getOUT_QTY_Arr() {
		return OUT_QTY_Arr;
	}
	public void setOUT_QTY_Arr(int oUT_QTY_Arr) {
		OUT_QTY_Arr = oUT_QTY_Arr;
	}
	public List<Date> getPD_OUT_DATE_Arr() {
		return PD_OUT_DATE_Arr;
	}
	public void setPD_OUT_DATE_Arr(List<Date> pD_OUT_DATE_Arr) {
		PD_OUT_DATE_Arr = pD_OUT_DATE_Arr;
	}
	public List<String> getPD_REMARKS_Arr() {
		return PD_REMARKS_Arr;
	}
	public void setPD_REMARKS_Arr(List<String> pD_REMARKS_Arr) {
		PD_REMARKS_Arr = pD_REMARKS_Arr;
	}
	public String getSTOCK_CD_Arr() {
		return STOCK_CD_Arr;
	}
	public void setSTOCK_CD_Arr(String sTOCK_CD_Arr) {
		STOCK_CD_Arr = sTOCK_CD_Arr;
	}
	
	@Override
	public String toString() {
		return "OutSchVo [OUT_SCHEDULE_CD=" + OUT_SCHEDULE_CD + ", OUT_TYPE_NAME=" + OUT_TYPE_NAME + ", BUSINESS_NO="
				+ BUSINESS_NO + ", EMP_NUM=" + EMP_NUM + ", EMP_NAME=" + EMP_NAME + ", OUT_COMPLETE=" + OUT_COMPLETE
				+ ", OUT_DATE=" + OUT_DATE + ", REMARKS=" + REMARKS + ", TOTAL_QTY=" + TOTAL_QTY + ", PRODUCT_NAME="
				+ PRODUCT_NAME + ", SIZE_DES=" + SIZE_DES + ", PRODUCT_CD_Arr=" + PRODUCT_CD_Arr + ", PRODUCT_NAME_Arr="
				+ PRODUCT_NAME_Arr + ", SIZE_DES_Arr=" + SIZE_DES_Arr + ", OUT_SCHEDULE_QTY_Arr=" + OUT_SCHEDULE_QTY_Arr
				+ ", OUT_QTY_Arr=" + OUT_QTY_Arr + ", PD_OUT_DATE_Arr=" + PD_OUT_DATE_Arr + ", PD_REMARKS_Arr="
				+ PD_REMARKS_Arr + ", STOCK_CD_Arr=" + STOCK_CD_Arr + "]";
	}
	
}
