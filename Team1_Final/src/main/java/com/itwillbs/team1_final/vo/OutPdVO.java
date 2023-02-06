package com.itwillbs.team1_final.vo;

import java.util.Date;

public class OutPdVO {

	private String OUT_SCHEDULE_CD;
	private int PRODUCT_CD;
	private int OUT_SCHEDULE_QTY;
	private int OUT_QTY;
	private Date OUT_DATE;
	private String REMARKS;
	private String STOCK_CD;
	private String OUT_COMPLETE;
	
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
	public String getSTOCK_CD() {
		return STOCK_CD;
	}
	public void setSTOCK_CD(String sTOCK_CD) {
		STOCK_CD = sTOCK_CD;
	}
	public String getOUT_COMPLETE() {
		return OUT_COMPLETE;
	}
	public void setOUT_COMPLETE(String oUT_COMPLETE) {
		OUT_COMPLETE = oUT_COMPLETE;
	}
	
	@Override
	public String toString() {
		return "OutPdVO [OUT_SCHEDULE_CD=" + OUT_SCHEDULE_CD + ", PRODUCT_CD=" + PRODUCT_CD + ", OUT_SCHEDULE_QTY="
				+ OUT_SCHEDULE_QTY + ", OUT_QTY=" + OUT_QTY + ", OUT_DATE=" + OUT_DATE + ", REMARKS=" + REMARKS
				+ ", STOCK_CD=" + STOCK_CD + ", OUT_COMPLETE=" + OUT_COMPLETE + "]";
	}
	
}
