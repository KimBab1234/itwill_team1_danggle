package com.itwillbs.team1.vo;


public class ProductOptBean {
	
	private String goodsOpt_idx;
	private String option_name;
	private String option_quantity;
	 
	
	public String getGoodsOpt_idx() {
		return goodsOpt_idx;
	}
	public void setGoodsOpt_idx(String goodsOpt_idx) {
		this.goodsOpt_idx = goodsOpt_idx;
	}
	public String getOption_name() {
		return option_name;
	}
	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}
	public String getOption_quantity() {
		return option_quantity;
	}
	public void setOption_quantity(String option_quantity) {
		this.option_quantity = option_quantity;
	}
	
	@Override
	public String toString() {
		return "ProductOptBean [goodsOpt_idx=" + goodsOpt_idx + ", option_name=" + option_name + ", option_qauntity="
				+ option_quantity + "]";
	}
	
	
}
