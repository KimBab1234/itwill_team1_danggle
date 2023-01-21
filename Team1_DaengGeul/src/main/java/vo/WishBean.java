package vo;
/*
Create table wish(
	member_id VARCHAR(50) NOT NULL,
	product_idx VARCHAR(10) NOT NULL,
	wish_date date NOT NULL,
	CONSTRAINT wish_mem_fk 
		FOREIGN KEY(member_id)
		REFERENCES Member(member_id)
);
 */

import java.sql.Date;

public class WishBean {
	private String member_id;
	private String product_idx;
	private Date wish_date;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getProduct_idx() {
		return product_idx;
	}
	public void setProduct_idx(String product_idx) {
		this.product_idx = product_idx;
	}
	public Date getWish_date() {
		return wish_date;
	}
	public void setWish_date(Date wish_date) {
		this.wish_date = wish_date;
	}
	
	@Override
	public String toString() {
		return "WishBean [member_id=" + member_id + ", product_idx=" + product_idx + ", wish_date=" + wish_date + "]";
	}
	
}
