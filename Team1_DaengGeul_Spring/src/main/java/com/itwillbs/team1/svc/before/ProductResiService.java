package com.itwillbs.team1.svc.before;

import java.sql.Connection;

import com.itwillbs.team1.dao.ProductDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.ProductBean;

public class ProductResiService {

	public int bookregistration(ProductBean book) {
		System.out.println("bookregistration - 책 등록 서비스 페이지");
		int insertCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);
		
		insertCount = dao.regiBook(book);
		
		if(insertCount > 0) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return insertCount;
	
	}

	public int goodsregistration(ProductBean goods) {
		System.out.println("goodsregistration - 굿즈 등록 서비스 페이지");
		int insertCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);
		
		insertCount = dao.regiGoods(goods);
		
		if(insertCount > 0) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return insertCount;
	}

}
