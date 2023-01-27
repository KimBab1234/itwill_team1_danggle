package com.itwillbs.team1_final.vo;


/*
품목코드 ITEM_CD
품목명 ITEM_NAME
품목그룹 ITEM_GROUP
규격 ITEM_STANDARD
바코드 ITEM_BARCODE
입고단가 ITEM_IN_COST
출고단가 ITEM_OUT_COST
품목구분 ITEM_GUBUN
대표이미지 ITEM_IMAGE
 */
public class Item {

	private String ITEM_CD;
	private String ITEM_NAME;
	private String ITEM_GROUP;
	private String ITEM_STANDARD;
	private String ITEM_BARCODE;
	private int ITEM_IN_COST;
	private int ITEM_OUT_COST;
	private String ITEM_GUBUN;
	private String ITEM_IMAGE;
	
	public String getITEM_CD() {
		return ITEM_CD;
	}
	public void setITEM_CD(String iTEM_CD) {
		ITEM_CD = iTEM_CD;
	}
	public String getITEM_NAME() {
		return ITEM_NAME;
	}
	public void setITEM_NAME(String iTEM_NAME) {
		ITEM_NAME = iTEM_NAME;
	}
	public String getITEM_GROUP() {
		return ITEM_GROUP;
	}
	public void setITEM_GROUP(String iTEM_GROUP) {
		ITEM_GROUP = iTEM_GROUP;
	}
	public String getITEM_STANDARD() {
		return ITEM_STANDARD;
	}
	public void setITEM_STANDARD(String iTEM_STANDARD) {
		ITEM_STANDARD = iTEM_STANDARD;
	}
	public String getITEM_BARCODE() {
		return ITEM_BARCODE;
	}
	public void setITEM_BARCODE(String iTEM_BARCODE) {
		ITEM_BARCODE = iTEM_BARCODE;
	}
	public int getITEM_IN_COST() {
		return ITEM_IN_COST;
	}
	public void setITEM_IN_COST(int iTEM_IN_COST) {
		ITEM_IN_COST = iTEM_IN_COST;
	}
	public int getITEM_OUT_COST() {
		return ITEM_OUT_COST;
	}
	public void setITEM_OUT_COST(int iTEM_OUT_COST) {
		ITEM_OUT_COST = iTEM_OUT_COST;
	}
	public String getITEM_GUBUN() {
		return ITEM_GUBUN;
	}
	public void setITEM_GUBUN(String iTEM_GUBUN) {
		ITEM_GUBUN = iTEM_GUBUN;
	}
	public String getITEM_IMAGE() {
		return ITEM_IMAGE;
	}
	public void setITEM_IMAGE(String iTEM_IMAGE) {
		ITEM_IMAGE = iTEM_IMAGE;
	}
	@Override
	public String toString() {
		return "Item [ITEM_CD=" + ITEM_CD + ", ITEM_NAME=" + ITEM_NAME + ", ITEM_GROUP=" + ITEM_GROUP
				+ ", ITEM_STANDARD=" + ITEM_STANDARD + ", ITEM_BARCODE=" + ITEM_BARCODE + ", ITEM_IN_COST="
				+ ITEM_IN_COST + ", ITEM_OUT_COST=" + ITEM_OUT_COST + ", ITEM_GUBUN=" + ITEM_GUBUN + ", ITEM_IMAGE="
				+ ITEM_IMAGE + "]";
	}
	
	
	
	
	
}
