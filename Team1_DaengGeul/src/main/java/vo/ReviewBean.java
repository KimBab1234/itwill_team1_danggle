package vo;

import java.util.Date;

/*
 * mvc_board5 데이터베이스 생성 및 board 테이블 정의
    CREATE DATABASE nnntest;
    use nnntest
    
   create table review (
   review_idx INT PRIMARY KEY AUTO_INCREMENT,
   product_idx VARCHAR(10) NOT NULL,
   member_id VARCHAR(50) NOT NULL,
   review_subject VARCHAR(50) NOT NULL,
   review_passwd VARCHAR(50) NOT NULL,
   review_score INT NOT NULL,
   review_content TEXT NOT NULL,
   review_readcount INT DEFAULT 0,
   review_date DATE NOT NULL,
   review_like_done VARCHAR(10) NOT NULL
   );
	
	리뷰 좋아요 게시판
	
	create table review_like(
	review_idx INT NOT NULL,
	member_id VARCHAR(50) NOT NULL);
	
	alter table review_like add constraint review_idx_fk 
	foreign key(review_idx) references review(review_idx) on delete cascade;
	
	alter table review_like add constraint member_id_fk
	foreign key(member_id) references member(member_id) on delete cascade;
	
	
	
	review_idx INT PRIMARY KEY AUTO_INCREMENT //리뷰 글번호
	product_idx VARCHAR(10) NOT NULL //상품 번호
	member_id VARCHAR(50) NOT NULL //멤버 아이디
    review_subject VARCHAR(50) NOT NULL //리뷰 제목
    review_score INT NOT NULL //리뷰 별점
	review_content TEXT NOT NULL //리뷰 내용
	review_like INT NOT NULL //좋아요
	review_date date NOT NULL //리뷰 작성 날짜
	
	
	 
 * 
 * mvc_board3 데이터베이스의 board 테이블(게시판) 1개 레코드(= 1개 게시물) 정보를 저장하는
 * Bean 클래스(DTO or VO) 정의
 * 
 */
public class ReviewBean {
	private int review_idx;
	private String product_idx;
	private String member_id;
	private String review_subject;
	private String review_passwd;
	private int review_score;
	private String review_content;
	private int review_readcount;
	private Date review_date;
	private String review_like_done;
	private String order_idx;
	
	public String getOrder_idx() {
		return order_idx;
	}
	public void setOrder_idx(String order_idx) {
		this.order_idx = order_idx;
	}
	public int getReview_idx() {
		return review_idx;
	}
	public void setReview_idx(int review_idx) {
		this.review_idx = review_idx;
	}
	public String getProduct_idx() {
		return product_idx;
	}
	public void setProduct_idx(String product_idx) {
		this.product_idx = product_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getReview_subject() {
		return review_subject;
	}
	public void setReview_subject(String review_subject) {
		this.review_subject = review_subject;
	}
	public String getReview_passwd() {
		return review_passwd;
	}
	public void setReview_passwd(String review_passwd) {
		this.review_passwd = review_passwd;
	}
	public int getReview_score() {
		return review_score;
	}
	public void setReview_score(int review_score) {
		this.review_score = review_score;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public int getReview_readcount() {
		return review_readcount;
	}
	public void setReview_readcount(int review_readcount) {
		this.review_readcount = review_readcount;
	}
	public Date getReview_date() {
		return review_date;
	}
	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}
	public String getReview_like_done() {
		return review_like_done;
	}
	public void setReview_like_done(String review_like_done) {
		this.review_like_done = review_like_done;
	}
	@Override
	public String toString() {
		return "ReviewBean [review_idx=" + review_idx + ", product_idx=" + product_idx + ", member_id=" + member_id
				+ ", review_subject=" + review_subject + ", review_passwd=" + review_passwd + ", review_score="
				+ review_score + ", review_content=" + review_content + ", review_readcount=" + review_readcount
				+ ", review_date=" + review_date + ", review_like_done="
				+ review_like_done + "]";
	}
	
	
	
	
	
}
