package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;

public class RecommendBookDeleteService {

	public int deleteRecommendBook(String product_idx) {
		System.out.println("deleteRecommendBook - 추천 도서 삭제 서비스 페이지");
		int deleteCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);
		
		deleteCount = dao.deleteRecoBook(product_idx);
		
		if(deleteCount > 0) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return deleteCount;
	}

}
