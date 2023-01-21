package com.itwillbs.team1.vo;

public class OrderProdBean {

	private String order_idx;
	private String status;
	private String idx;
	private String name;
	private String opt;
	private int cnt;
	private int price;
	private String review_write;
	private String img;
	
	public String getOrder_idx() {
		return order_idx;
	}
	public void setOrder_idx(String order_idx) {
		this.order_idx = order_idx;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getReview_write() {
		return review_write;
	}
	public void setReview_write(String review_write) {
		this.review_write = review_write;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	@Override
	public String toString() {
		return "OrderProdBean [status=" + status + ", idx=" + idx + ", name=" + name + ", opt=" + opt + ", cnt=" + cnt
				+ ", price=" + price + ", review_write=" + review_write + ", img=" + img + "]";
	}
	
}
