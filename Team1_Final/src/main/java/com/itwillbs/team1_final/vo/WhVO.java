package com.itwillbs.team1_final.vo;

public class WhVO {

	private String WH_CD;
	private String WH_NAME;
	private String WH_GUBUN;
	private String WH_LOCATION;
	private String WH_ADDR;
	private String WH_TEL;
	private String WH_MAN_NAME;
	private String WH_USE;
	private String REMARKS;
	public String getWH_CD() {
		return WH_CD;
	}
	public void setWH_CD(String wH_CD) {
		WH_CD = wH_CD;
	}
	public String getWH_NAME() {
		return WH_NAME;
	}
	public void setWH_NAME(String wH_NAME) {
		WH_NAME = wH_NAME;
	}
	public String getWH_GUBUN() {
		return WH_GUBUN;
	}
	public void setWH_GUBUN(String wH_GUBUN) {
		WH_GUBUN = wH_GUBUN;
	}
	public String getWH_LOCATION() {
		return WH_LOCATION;
	}
	public void setWH_LOCATION(String wH_LOCATION) {
		WH_LOCATION = wH_LOCATION;
	}
	public String getWH_ADDR() {
		return WH_ADDR;
	}
	public void setWH_ADDR(String wH_ADDR) {
		WH_ADDR = wH_ADDR;
	}
	public String getWH_TEL() {
		return WH_TEL;
	}
	public void setWH_TEL(String wH_TEL) {
		WH_TEL = wH_TEL;
	}
	public String getWH_MAN_NAME() {
		return WH_MAN_NAME;
	}
	public void setWH_MAN_NAME(String wH_MAN_NAME) {
		WH_MAN_NAME = wH_MAN_NAME;
	}
	public String getWH_USE() {
		return WH_USE;
	}
	public void setWH_USE(String wH_USE) {
		WH_USE = wH_USE;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}
	@Override
	public String toString() {
		return "WhVO [WH_CD=" + WH_CD + ", WH_NAME=" + WH_NAME + ", WH_GUBUN=" + WH_GUBUN + ", WH_LOCATION="
				+ WH_LOCATION + ", WH_ADDR=" + WH_ADDR + ", WH_TEL=" + WH_TEL + ", WH_MAN_NAME=" + WH_MAN_NAME
				+ ", WH_USE=" + WH_USE + ", REMARKS=" + REMARKS + "]";
	}
	
}