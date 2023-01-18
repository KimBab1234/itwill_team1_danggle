package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.ReviewDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.ReviewBean;

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
