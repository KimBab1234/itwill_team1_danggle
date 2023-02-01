package com.itwillbs.team1_final.vo;

public class WhAreaVO {

	private String WH_CD;
	private String WH_AREA;
	private int WH_AREA_CD;
	public String getWH_CD() {
		return WH_CD;
	}
	public void setWH_CD(String wH_CD) {
		WH_CD = wH_CD;
	}
	public String getWH_AREA() {
		return WH_AREA;
	}
	public void setWH_AREA(String wH_AREA) {
		WH_AREA = wH_AREA;
	}
	public int getWH_AREA_CD() {
		return WH_AREA_CD;
	}
	public void setWH_AREA_CD(int wH_AREA_CD) {
		WH_AREA_CD = wH_AREA_CD;
	}
	@Override
	public String toString() {
		return "WhInVO [WH_CD=" + WH_CD + ", WH_AREA=" + WH_AREA + ", WH_AREA_CD=" + WH_AREA_CD + "]";
	}
	
	
}
