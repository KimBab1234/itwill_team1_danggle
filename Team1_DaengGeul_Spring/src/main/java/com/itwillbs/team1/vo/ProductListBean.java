package com.itwillbs.team1.vo;


public class ProductListBean {
	private String type;
	private String keyword;
	private String pageNum;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public ProductListBean(String type, String keyword) {
		super();
		this.type = type;
		this.keyword = keyword;
	}
	
	
}
