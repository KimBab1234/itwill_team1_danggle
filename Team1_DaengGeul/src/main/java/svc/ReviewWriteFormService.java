package svc;

import java.sql.Connection;

import dao.ReviewDAO;
import db.JdbcUtil;
import vo.ReviewBean;

public class ReviewWriteFormService {

	public ReviewBean getReview(String product_idx) {
		ReviewBean review = null;
		
		Connection con = JdbcUtil.getConnection();
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		dao.setConnection(con);
		
		review = dao.selectProduct(product_idx);
		
		JdbcUtil.commit(con);
			
		JdbcUtil.close(con);
				
		return review;
	}

}
