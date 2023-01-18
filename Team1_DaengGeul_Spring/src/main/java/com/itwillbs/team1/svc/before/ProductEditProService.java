package com.itwillbs.team1.svc.before;

import java.sql.Connection;

import com.itwillbs.team1.dao.ProductDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.ProductBean;

public class ProductEditProService {

	public int updateBook(ProductBean product) {
		System.out.println("updateBook - 책 수정 서비스 페이지");
		int updateCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);
		
		updateCount = dao.updateBook(product);
		
		if(updateCount > 0) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return updateCount;
	}

	public int updateGoods(ProductBean product) {
		System.out.println("updateGoods - 굿즈 수정 서비스 페이지");
		int updateCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);
		
		updateCount = dao.updateGoods(product);
		
		
		if(updateCount > 0) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return updateCount;
	}

}	
