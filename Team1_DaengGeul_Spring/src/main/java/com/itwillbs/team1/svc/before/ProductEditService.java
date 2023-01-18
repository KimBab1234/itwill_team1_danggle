package com.itwillbs.team1.svc.before;

import java.sql.Connection;

import com.itwillbs.team1.dao.ProductDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.ProductBean;

public class ProductEditService {

	public ProductBean getBook(String product_idx) {
		System.out.println("ProductEditService(getBook) - 책 수정");
		ProductBean book = null;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);
		
		book = dao.selectBook(product_idx);
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return book;
	}

	public ProductBean getGoods(String product_idx) {
		System.out.println("ProductEditService(getGoods) - 굿즈 수정");
		ProductBean goods = null;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);
		
		goods = dao.selectGoods(product_idx);
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return goods;
	}

}
