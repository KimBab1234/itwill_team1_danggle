package vo;
/*
CREATE VIEW wishlist AS(
	SELECT w.member_id,
		w.product_idx,
        w.wish_date, 
        b.book_name 'product_name',
		b.book_price 'product_price',
		b.book_discount 'product_discount',
		b.book_img 'product_real_img'
	FROM wish w INNER JOIN book b
						ON w.product_idx=b.book_idx
	union
	SELECT w.member_id, 
		w.product_idx,
        w.wish_date, 
        g.goods_name 'product_name', 
        g.goods_price 'product_price', 
        g.goods_discount 'product_discount',
        g.goods_img 'product_real_img'
	FROM wish w INNER JOIN goods g
						ON w.product_idx=g.goods_idx
);
 */

import java.sql.Date;

public class WishlistBean {
	private String member_id;
	private String product_idx;
	private Date wish_date;
	private String product_name;
	private int product_price;
	private int product_discount;
	private String product_real_img;
	
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
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getProduct_discount() {
		return product_discount;
	}
	public void setProduct_discount(int product_discount) {
		this.product_discount = product_discount;
	}
	public String getProduct_real_img() {
		return product_real_img;
	}
	public void setProduct_real_img(String product_real_img) {
		this.product_real_img = product_real_img;
	}
	
	@Override
	public String toString() {
		return "WishlistBean [member_id=" + member_id + ", product_idx=" + product_idx + ", wish_date=" + wish_date
				+ ", product_name=" + product_name + ", product_price=" + product_price + ", product_discount="
				+ product_discount + ", product_real_img=" + product_real_img + "]";
	}
	
}
