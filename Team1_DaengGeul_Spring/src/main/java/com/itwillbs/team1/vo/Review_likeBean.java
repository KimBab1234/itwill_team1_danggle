package com.itwillbs.team1.vo;
/*
 * 리뷰 좋아요 게시판
create table review_like(
		review_idx INT NOT NULL,
		member_id VARCHAR(50) NOT NULL);
		
		alter table review_like add constraint review_idx_fk 
		foreign key(review_idx) references review(review_idx) on delete cascade;
		
		alter table review_like add constraint member_id_fk
		foreign key(member_id) references member(member_id) on delete cascade;
 */
public class Review_likeBean {
	private int review_idx;
	private String member_id;
	
	public int getReview_idx() {
		return review_idx;
	}
	public void setReview_idx(int review_idx) {
		this.review_idx = review_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	@Override
	public String toString() {
		return "Review_like [review_idx=" + review_idx + ", member_id=" + member_id
				+ "]";
	}
	
	
}
