package com.itwillbs.team1.vo;

import java.sql.Timestamp;

public class NoticeBean {

	private int notice_idx;
	private String notice_subject;
	private String notice_content;
	private String notice_file;
	private String notice_real_file;
	private Timestamp notice_date;
	private int notice_readcount;
	
	public int getNotice_idx() {
		return notice_idx;
	}
	public void setNotice_idx(int notice_idx) {
		this.notice_idx = notice_idx;
	}
	public String getNotice_subject() {
		return notice_subject;
	}
	public void setNotice_subject(String notice_subject) {
		this.notice_subject = notice_subject;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public String getNotice_file() {
		return notice_file;
	}
	public void setNotice_file(String notice_file) {
		this.notice_file = notice_file;
	}
	public String getNotice_real_file() {
		return notice_real_file;
	}
	public void setNotice_real_file(String notice_real_file) {
		this.notice_real_file = notice_real_file;
	}
	public Timestamp getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(Timestamp notice_date) {
		this.notice_date = notice_date;
	}
	public int getNotice_readcount() {
		return notice_readcount;
	}
	public void setNotice_readcount(int notice_readcount) {
		this.notice_readcount = notice_readcount;
	}
	@Override
	public String toString() {
		return "NoticeBean [notice_idx=" + notice_idx + ", notice_subject=" + notice_subject + ", notice_content="
				+ notice_content + ", notice_file=" + notice_file + ", notice_real_file=" + notice_real_file
				+ ", notice_date=" + notice_date + ", notice_readcount=" + notice_readcount + "]";
	}
	
}
