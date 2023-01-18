package com.itwillbs.team1.svc;

import java.sql.Connection;
import java.util.ArrayList;

import com.itwillbs.team1.dao.ProductDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.ProductBean;

public class RecommendBookService {

	public int recommendBook(ProductBean book) {
		System.out.println("RecommendBookService - 추천 도서 등록 서비스 페이지");
		int insertCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);
		
		insertCount = dao.recommendBook(book);
		
		if(insertCount > 0) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return insertCount;
	}


}
