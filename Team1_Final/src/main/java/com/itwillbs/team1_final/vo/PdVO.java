package com.itwillbs.team1_final.vo;

import org.springframework.web.multipart.MultipartFile;

/*
PRODUCT_CD	품목코드	NUMBER
PRODUCT_NAME	품목명	VARCHAR(100)
PRODUCT_GROUP_BOTTOM_CD	품목그룹(소)	VARCHAR(50)
SIZE_DES	규격	VARCHAR(30)
UNIT	단위	VARCHAR(30)
BARCODE	바코드	VARCHAR(30)
IN_UNIT_PRICE	입고단가	NUMBER(10,2)
OUT_UNIT_PRICE	출고단가	NUMBER(10,2)
PRODUCT_TYPE_CD	품목타입코드	VARCHAR(10)
BUSINESS_NO	구매거래처코드	VARCHAR(30)
PRODUCT_IMAGE	대표이미지	VARCHAR(100)
REMARKS	적요	VARCHAR(2000)

PRODUCT_GROUP_TOP_CD 품목그룹코드(대)
PRODUCT_GROUP_TOP_NAME 품목그룹명(대)

CREATE TABLE PRODUCT (
PRODUCT_CD INT PRIMARY KEY,
PRODUCT_NAME VARCHAR(100) NOT NULL,
PRODUCT_GROUP_BOTTOM_CD VARCHAR(50) NOT NULL,
SIZE_DES VARCHAR(30),
UNIT VARCHAR(30) NOT NULL,
BARCODE VARCHAR(30) NOT NULL,
IN_UNIT_PRICE INT NOT NULL,
OUT_UNIT_PRICE INT NOT NULL,
PRODUCT_TYPE_CD VARCHAR(10) NOT NULL,
BUSINESS_NO VARCHAR(10) NOT NULL,
PRODUCT_IMAGE VARCHAR(100),
REMARKS VARCHAR(2000)
);
 */
public class PdVO {

	private int PRODUCT_CD;
	private String PRODUCT_NAME;
	private String PRODUCT_GROUP_BOTTOM_CD;
	private String SIZE_DES;
	private String UNIT;
	private String BARCODE;
	private int IN_UNIT_PRICE;
	private int OUT_UNIT_PRICE;
	private String PRODUCT_TYPE_CD;
	private String BUSINESS_NO;
	private String PRODUCT_IMAGE;
	private String REMARKS;
	private MultipartFile file;
	private int PRODUCT_GROUP_TOP_CD;
	private String PRODUCT_GROUP_TOP_NAME;
	private String PRODUCT_GROUP_BOTTOM_NAME;
	
	public int getPRODUCT_CD() {
		return PRODUCT_CD;
	}
	public void setPRODUCT_CD(int pRODUCT_CD) {
		PRODUCT_CD = pRODUCT_CD;
	}
	public String getPRODUCT_NAME() {
		return PRODUCT_NAME;
	}
	public void setPRODUCT_NAME(String pRODUCT_NAME) {
		PRODUCT_NAME = pRODUCT_NAME;
	}
	public String getPRODUCT_GROUP_BOTTOM_CD() {
		return PRODUCT_GROUP_BOTTOM_CD;
	}
	public void setPRODUCT_GROUP_BOTTOM_CD(String pRODUCT_GROUP_BOTTOM_CD) {
		PRODUCT_GROUP_BOTTOM_CD = pRODUCT_GROUP_BOTTOM_CD;
	}
	public String getSIZE_DES() {
		return SIZE_DES;
	}
	public void setSIZE_DES(String sIZE_DES) {
		SIZE_DES = sIZE_DES;
	}
	public String getUNIT() {
		return UNIT;
	}
	public void setUNIT(String uNIT) {
		UNIT = uNIT;
	}
	public String getBARCODE() {
		return BARCODE;
	}
	public void setBARCODE(String bARCODE) {
		BARCODE = bARCODE;
	}
	public int getIN_UNIT_PRICE() {
		return IN_UNIT_PRICE;
	}
	public void setIN_UNIT_PRICE(int iN_UNIT_PRICE) {
		IN_UNIT_PRICE = iN_UNIT_PRICE;
	}
	public int getOUT_UNIT_PRICE() {
		return OUT_UNIT_PRICE;
	}
	public void setOUT_UNIT_PRICE(int oUT_UNIT_PRICE) {
		OUT_UNIT_PRICE = oUT_UNIT_PRICE;
	}
	public String getPRODUCT_TYPE_CD() {
		return PRODUCT_TYPE_CD;
	}
	public void setPRODUCT_TYPE_CD(String pRODUCT_TYPE_CD) {
		PRODUCT_TYPE_CD = pRODUCT_TYPE_CD;
	}
	public String getBUSINESS_NO() {
		return BUSINESS_NO;
	}
	public void setBUSINESS_NO(String bUSINESS_NO) {
		BUSINESS_NO = bUSINESS_NO;
	}
	public String getPRODUCT_IMAGE() {
		return PRODUCT_IMAGE;
	}
	public void setPRODUCT_IMAGE(String pRODUCT_IMAGE) {
		PRODUCT_IMAGE = pRODUCT_IMAGE;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "ItemVO [PRODUCT_CD=" + PRODUCT_CD + ", PRODUCT_NAME=" + PRODUCT_NAME + ", PRODUCT_GROUP_BOTTOM_CD="
				+ PRODUCT_GROUP_BOTTOM_CD + ", SIZE_DES=" + SIZE_DES + ", UNIT=" + UNIT + ", BARCODE=" + BARCODE
				+ ", IN_UNIT_PRICE=" + IN_UNIT_PRICE + ", OUT_UNIT_PRICE=" + OUT_UNIT_PRICE + ", PRODUCT_TYPE_CD="
				+ PRODUCT_TYPE_CD + ", BUSINESS_NO=" + BUSINESS_NO + ", PRODUCT_IMAGE=" + PRODUCT_IMAGE + ", REMARKS="
				+ REMARKS + ", file=" + file + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
