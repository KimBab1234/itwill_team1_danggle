package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

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
