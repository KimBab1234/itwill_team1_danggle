package vo;

import java.sql.Date;

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
	private String book_genre;
	private String book_writer;
	private String book_publisher;
	private Date book_date;
	
	//굿즈 옵션
	private String goods_opt;
	
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
	public String getGoods_opt() {
		return goods_opt;
	}
	public void setGoods_opt(String goods_opt) {
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
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getBook_genre() {
		return book_genre;
	}
	public void setBook_genre(String book_genre) {
		this.book_genre = book_genre;
	}
	public String getBook_writer() {
		return book_writer;
	}
	public void setBook_writer(String book_writer) {
		this.book_writer = book_writer;
	}
	public String getBook_publisher() {
		return book_publisher;
	}
	public void setBook_publisher(String book_publisher) {
		this.book_publisher = book_publisher;
	}
	public Date getBook_date() {
		return book_date;
	}
	public void setBook_date(Date book_date) {
		this.book_date = book_date;
	}
	
	public int getDis_price() {
		return dis_price;
	}
	public void setDis_price(int dis_price) {
		this.dis_price = dis_price;
	}
	
	@Override
	public String toString() {
		return "ProductBean [idx=" + idx + ", product_idx=" + product_idx + ", name=" + name + ", price=" + price
				+ ", quantity=" + quantity + ", sel_count=" + sel_count + ", detail=" + detail + ", img=" + img
				+ ", detail_img=" + detail_img + ", discount=" + discount + ", dis_price=" + dis_price
				+ ", review_score=" + review_score + ", rank=" + rank + ", book_genre=" + book_genre + ", book_writer="
				+ book_writer + ", book_publisher=" + book_publisher + ", book_date=" + book_date + ", goods_opt="
				+ goods_opt + "]";
	}
	
	
	
}
