package com.itwillbs.team1_final.vo;

import java.sql.Date;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class HrVO {

	private int idx;
	private String EMP_NUM;
	private String newEMP_NUM;
	private String EMP_NAME;
	private String DEPT_CD;
	private String DEPT_NAME;
	private String GRADE_CD;
	private String GRADE_NAME;
	private String EMP_TEL;
	private String EMP_TEL1;
	private String EMP_TEL2;
	private String EMP_TEL3;
	private String EMP_DTEL;
	private String EMP_DTEL1;
	private String EMP_DTEL2;
	private String EMP_DTEL3;
	private String EMP_EMAIL;
	private String EMP_EMAIL1;
	private String EMP_EMAIL2;
	private String EMP_POST_NO;
	private String EMP_ADDR;
	private String EMP_ADDR_DETAIL;
	private Date HIRE_DATE;
	private String WORK_CD;
	private String WORK_TYPE;
	private String PRIV_CD;
	private String PRIV_TYPE;
	private String PHOTO;
	private MultipartFile registPHOTO;
	private boolean[] PRIV_CD_Arr;
	private String EMP_PASS;
	private String EMP_PASS_NEW;
	
	public String getEMP_PASS_NEW() {
		return EMP_PASS_NEW;
	}
	public void setEMP_PASS_NEW(String eMP_PASS_NEW) {
		EMP_PASS_NEW = eMP_PASS_NEW;
	}
	public String getEMP_PASS() {
		return EMP_PASS;
	}
	public void setEMP_PASS(String eMP_PASS) {
		EMP_PASS = eMP_PASS;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}
	public void setDEPT_NAME(String dEPT_NAME) {
		DEPT_NAME = dEPT_NAME;
	}
	public String getGRADE_NAME() {
		return GRADE_NAME;
	}
	public void setGRADE_NAME(String gRADE_NAME) {
		GRADE_NAME = gRADE_NAME;
	}
	public String getWORK_TYPE() {
		return WORK_TYPE;
	}
	public void setWORK_TYPE(String wORK_TYPE) {
		WORK_TYPE = wORK_TYPE;
	}
	public String getPRIV_TYPE() {
		return PRIV_TYPE;
	}
	public void setPRIV_TYPE(String pRIV_TYPE) {
		PRIV_TYPE = pRIV_TYPE;
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
	public String getDEPT_CD() {
		return DEPT_CD;
	}
	public void setDEPT_CD(String dEPT_CD) {
		DEPT_CD = dEPT_CD;
	}
	public String getGRADE_CD() {
		return GRADE_CD;
	}
	public void setGRADE_CD(String gRADE_CD) {
		GRADE_CD = gRADE_CD;
	}
	public String getEMP_TEL() {
		return EMP_TEL;
	}
	public void setEMP_TEL(String eMP_TEL) {
		EMP_TEL = eMP_TEL;
	}
	public String getEMP_DTEL() {
		return EMP_DTEL;
	}
	public void setEMP_DTEL(String eMP_DTEL) {
		EMP_DTEL = eMP_DTEL;
	}
	public String getEMP_EMAIL() {
		return EMP_EMAIL;
	}
	public void setEMP_EMAIL(String eMP_EMAIL) {
		EMP_EMAIL = eMP_EMAIL;
	}
	public String getEMP_POST_NO() {
		return EMP_POST_NO;
	}
	public void setEMP_POST_NO(String eMP_POST_NO) {
		EMP_POST_NO = eMP_POST_NO;
	}
	public String getEMP_ADDR() {
		return EMP_ADDR;
	}
	public void setEMP_ADDR(String eMP_ADDR) {
		EMP_ADDR = eMP_ADDR;
	}
	public Date getHIRE_DATE() {
		return HIRE_DATE;
	}
	public void setHIRE_DATE(Date hIRE_DATE) {
		HIRE_DATE = hIRE_DATE;
	}
	public String getWORK_CD() {
		return WORK_CD;
	}
	public void setWORK_CD(String wORK_CD) {
		WORK_CD = wORK_CD;
	}
	public String getPRIV_CD() {
		return PRIV_CD;
	}
	public void setPRIV_CD(String pRIV_CD) {
		PRIV_CD = pRIV_CD;
	}
	public String getPHOTO() {
		return PHOTO;
	}
	public void setPHOTO(String pHOTO) {
		PHOTO = pHOTO;
	}
	public MultipartFile getRegistPHOTO() {
		return registPHOTO;
	}
	public void setRegistPHOTO(MultipartFile registPHOTO) {
		this.registPHOTO = registPHOTO;
	}
	
	public String getEMP_TEL1() {
		return EMP_TEL1;
	}
	public void setEMP_TEL1(String eMP_TEL1) {
		EMP_TEL1 = eMP_TEL1;
	}
	public String getEMP_TEL2() {
		return EMP_TEL2;
	}
	public void setEMP_TEL2(String eMP_TEL2) {
		EMP_TEL2 = eMP_TEL2;
	}
	public String getEMP_TEL3() {
		return EMP_TEL3;
	}
	public void setEMP_TEL3(String eMP_TEL3) {
		EMP_TEL3 = eMP_TEL3;
	}
	public String getEMP_DTEL1() {
		return EMP_DTEL1;
	}
	public void setEMP_DTEL1(String eMP_DTEL1) {
		EMP_DTEL1 = eMP_DTEL1;
	}
	public String getEMP_DTEL2() {
		return EMP_DTEL2;
	}
	public void setEMP_DTEL2(String eMP_DTEL2) {
		EMP_DTEL2 = eMP_DTEL2;
	}
	public String getEMP_DTEL3() {
		return EMP_DTEL3;
	}
	public void setEMP_DTEL3(String eMP_DTEL3) {
		EMP_DTEL3 = eMP_DTEL3;
	}
	public String getEMP_EMAIL1() {
		return EMP_EMAIL1;
	}
	public void setEMP_EMAIL1(String eMP_EMAIL1) {
		EMP_EMAIL1 = eMP_EMAIL1;
	}
	public String getEMP_EMAIL2() {
		return EMP_EMAIL2;
	}
	public void setEMP_EMAIL2(String eMP_EMAIL2) {
		EMP_EMAIL2 = eMP_EMAIL2;
	}
	
	public String getEMP_ADDR_DETAIL() {
		return EMP_ADDR_DETAIL;
	}
	public void setEMP_ADDR_DETAIL(String eMP_ADDR_DETAIL) {
		EMP_ADDR_DETAIL = eMP_ADDR_DETAIL;
	}
	public boolean[] getPRIV_CD_Arr() {
		return PRIV_CD_Arr;
	}
	public void setPRIV_CD_Arr(boolean[] pRIV_CD_Arr) {
		PRIV_CD_Arr = pRIV_CD_Arr;
	}
	public String getNewEMP_NUM() {
		return newEMP_NUM;
	}
	public void setNewEMP_NUM(String newEMP_NUM) {
		this.newEMP_NUM = newEMP_NUM;
	}
	
	@Override
	public String toString() {
		return "HrVO [idx=" + idx + ", EMP_NUM=" + EMP_NUM + ", EMP_NAME=" + EMP_NAME + ", DEPT_CD=" + DEPT_CD
				+ ", DEPT_NAME=" + DEPT_NAME + ", GRADE_CD=" + GRADE_CD + ", GRADE_NAME=" + GRADE_NAME + ", EMP_TEL="
				+ EMP_TEL + ", EMP_TEL1=" + EMP_TEL1 + ", EMP_TEL2=" + EMP_TEL2 + ", EMP_TEL3=" + EMP_TEL3
				+ ", EMP_DTEL=" + EMP_DTEL + ", EMP_DTEL1=" + EMP_DTEL1 + ", EMP_DTEL2=" + EMP_DTEL2 + ", EMP_DTEL3="
				+ EMP_DTEL3 + ", EMP_EMAIL=" + EMP_EMAIL + ", EMP_EMAIL1=" + EMP_EMAIL1 + ", EMP_EMAIL2=" + EMP_EMAIL2
				+ ", EMP_POST_NO=" + EMP_POST_NO + ", EMP_ADDR=" + EMP_ADDR + ", EMP_ADDR_DETAIL=" + EMP_ADDR_DETAIL
				+ ", HIRE_DATE=" + HIRE_DATE + ", WORK_CD=" + WORK_CD + ", WORK_TYPE=" + WORK_TYPE + ", PRIV_CD="
				+ PRIV_CD + ", PRIV_TYPE=" + PRIV_TYPE + ", PHOTO=" + PHOTO + ", registPHOTO=" + registPHOTO
				+ ", PRIV_CD_Arr=" + Arrays.toString(PRIV_CD_Arr) + "]";
	}
	
}
