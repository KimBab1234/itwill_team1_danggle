package com.itwillbs.team1_final.vo;

public class WhLocationVO {

	private String WH_AREA_CD;
	public String getWH_AREA_CD() {
		return WH_AREA_CD;
	}
	public void setWH_AREA_CD(String wH_AREA_CD) {
		WH_AREA_CD = wH_AREA_CD;
	}
	public String getWH_LOC_IN_AREA() {
		return WH_LOC_IN_AREA;
	}
	public void setWH_LOC_IN_AREA(String wH_LOC_IN_AREA) {
		WH_LOC_IN_AREA = wH_LOC_IN_AREA;
	}
	public String getWH_LOC_IN_AREA_CD() {
		return WH_LOC_IN_AREA_CD;
	}
	public void setWH_LOC_IN_AREA_CD(String wH_LOC_IN_AREA_CD) {
		WH_LOC_IN_AREA_CD = wH_LOC_IN_AREA_CD;
	}
	private String WH_LOC_IN_AREA;
	private String WH_LOC_IN_AREA_CD;
	@Override
	public String toString() {
		return "WhLoVO [WH_AREA_CD=" + WH_AREA_CD + ", WH_LOC_IN_AREA=" + WH_LOC_IN_AREA + ", WH_LOC_IN_AREA_CD="
				+ WH_LOC_IN_AREA_CD + "]";
	}
	
	
}
