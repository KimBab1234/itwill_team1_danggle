package com.itwillbs.team1_final.vo;

import java.util.List;

public class OutListInfoVO {

	private String OUT_SCHEDULE_CD;
	private String BUSINESS_NO;
	private String emp_name;
	
	private List<String> PRODUCT_NAME;
	private List<String> SIZE_DES;
	
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
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
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
	
	@Override
	public String toString() {
		return "OutListInfo [OUT_SCHEDULE_CD=" + OUT_SCHEDULE_CD + ", BUSINESS_NO=" + BUSINESS_NO + ", emp_name="
				+ emp_name + ", PRODUCT_NAME=" + PRODUCT_NAME + ", SIZE_DES=" + SIZE_DES + "]";
	}

}
