package com.itwillbs.team1.vo;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author user
 *
 */
public class ProductBean {
	// 상품 번호
	private int idx;
	private String product_idx;
	// 공통
	private String name;
	private int price; 
	private int quantity;
	private int sel_count;
	private String detail;
	private String img;
	private String detail_img;
	private int discount;
	private int dis_price;
	
	//지선 상품목록 위한 변수 추가
	private double review_score;
	private int rank;
	
	// + 책
	private String genre;
	private String writer;
	private String publisher;
	private Date date;
	
	//굿즈 옵션
	private List<String> goods_opt;
	
	// 경민 : 추천 도서, 굿즈 옵션 위한 변수 추가
	private String[] reco_idx;

    private List<String> option_name;
	private List<Integer> option_qauntity;
	
	//지선 : 스프링 컨버전하면서 option만 모아놓은 arraylist가 필요했음..
	private List<ProductOptBean> option;
	
	// 경민 : 파일 두 개 배열로 받아올 변수
	private MultipartFile[] files;
	
	// 상품 유형 구분을 위한 변수 추가
	private String group;
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public List<ProductOptBean> getOption() {
		return option;
	}
	public void setOption(List<ProductOptBean> option) {
		this.option = option;
	}
	public String[] getReco_idx() {
		return reco_idx;
	}
	public void setReco_idx(String[] reco_idx) {
		this.reco_idx = reco_idx;
	}
	public List<String> getOption_name() {
		return option_name;
	}
	public void setOption_name(List<String> option_name) {
		this.option_name = option_name;
	}
	public List<Integer> getOption_qauntity() {
		return option_qauntity;
	}
	public void setOption_qauntity(List<Integer> option_qauntity) {
		this.option_qauntity = option_qauntity;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public double getReview_score() {
		return review_score;
	}
	public void setReview_score(double review_score) {
		this.review_score = review_score;
	}
	public List<String> getGoods_opt() {
		return goods_opt;
	}
	public void setGoods_opt(List<String> goods_opt) {
		this.goods_opt = goods_opt;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getProduct_idx() {
		return product_idx;
	}
	public void setProduct_idx(String product_idx) {
		this.product_idx = product_idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getSel_count() {
		return sel_count;
	}
	public void setSel_count(int sel_count) {
		this.sel_count = sel_count;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDetail_img() {
		return detail_img;
	}
	public void setDetail_img(String detail_img) {
		this.detail_img = detail_img;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getDis_price() {
		return dis_price;
	}
	public void setDis_price(int dis_price) {
		this.dis_price = dis_price;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "ProductBean [idx=" + idx + ", product_idx=" + product_idx + ", name=" + name + ", price=" + price
				+ ", quantity=" + quantity + ", sel_count=" + sel_count + ", detail=" + detail + ", img=" + img
				+ ", detail_img=" + detail_img + ", discount=" + discount + ", dis_price=" + dis_price
				+ ", review_score=" + review_score + ", rank=" + rank + ", genre=" + genre + ", writer=" + writer
				+ ", publisher=" + publisher + ", date=" + date + ", goods_opt=" + goods_opt + ", reco_idx="
				+ Arrays.toString(reco_idx) + ", option_name=" + option_name + ", option_qauntity=" + option_qauntity
				+ ", option=" + option + "]";
	}
	
	
}
