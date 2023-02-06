package com.itwillbs.team1_final.vo;

import java.util.Date;

public class OutSchVo {

	private String OUT_SCHEDULE_CD;
	private String BUSINESS_NO;
	private String EMP_NUM;
	private Date OUT_DATE;
	private String REMARKS;
	private String OUT_COMPLETE;
	
	public String getOUT_SCHEDULE_CD() {
		return OUT_SCHEDULE_CD;
	}
	public void setOUT_SCHEDULE_CD(String oUT_SCHEDULE_CD) {
		OUT_SCHEDULE_CD = oUT_SCHEDULE_CD;
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
	public String getOUT_COMPLETE() {
		return OUT_COMPLETE;
	}
	public void setOUT_COMPLETE(String oUT_COMPLETE) {
		OUT_COMPLETE = oUT_COMPLETE;
	}
	
	@Override
	public String toString() {
		return "OutVo [OUT_SCHEDULE_CD=" + OUT_SCHEDULE_CD + ", BUSINESS_NO=" + BUSINESS_NO + ", EMP_NUM=" + EMP_NUM
				+ ", OUT_DATE=" + OUT_DATE + ", REMARKS=" + REMARKS + ", OUT_COMPLETE=" + OUT_COMPLETE + "]";
	}
	
	
}
