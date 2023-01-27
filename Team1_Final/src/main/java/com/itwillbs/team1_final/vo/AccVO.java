package com.itwillbs.team1_final.vo;

public class AccVO {
	private String ADDR;
	private String G_GUBUN;
	private String FAX;
	private String MOBILE_NO;
	private String URL_PATH;
	private String MAN_NAME;
	private String MAN_TEL;
	private String MAN_EMAIL;
	private String ACC_NAME;
	public String getADDR() {
		return ADDR;
	}
	public void setADDR(String aDDR) {
		ADDR = aDDR;
	}
	public String getG_GUBUN() {
		return G_GUBUN;
	}
	public void setG_GUBUN(String g_GUBUN) {
		G_GUBUN = g_GUBUN;
	}
	public String getFAX() {
		return FAX;
	}
	public void setFAX(String fAX) {
		FAX = fAX;
	}
	public String getMOBILE_NO() {
		return MOBILE_NO;
	}
	public void setMOBILE_NO(String mOBILE_NO) {
		MOBILE_NO = mOBILE_NO;
	}
	public String getURL_PATH() {
		return URL_PATH;
	}
	public void setURL_PATH(String uRL_PATH) {
		URL_PATH = uRL_PATH;
	}
	public String getMAN_NAME() {
		return MAN_NAME;
	}
	public void setMAN_NAME(String mAN_NAME) {
		MAN_NAME = mAN_NAME;
	}
	public String getMAN_TEL() {
		return MAN_TEL;
	}
	public void setMAN_TEL(String mAN_TEL) {
		MAN_TEL = mAN_TEL;
	}
	public String getMAN_EMAIL() {
		return MAN_EMAIL;
	}
	public void setMAN_EMAIL(String mAN_EMAIL) {
		MAN_EMAIL = mAN_EMAIL;
	}
	public String getACC_NAME() {
		return ACC_NAME;
	}
	public void setACC_NAME(String aCC_NAME) {
		ACC_NAME = aCC_NAME;
	}
	
	@Override
	public String toString() {
		return "AccVO [ADDR=" + ADDR + ", G_GUBUN=" + G_GUBUN + ", FAX=" + FAX + ", MOBILE_NO=" + MOBILE_NO
				+ ", URL_PATH=" + URL_PATH + ", MAN_NAME=" + MAN_NAME + ", MAN_TEL=" + MAN_TEL + ", MAN_EMAIL="
				+ MAN_EMAIL + ", ACC_NAME=" + ACC_NAME + "]";
	}

}
