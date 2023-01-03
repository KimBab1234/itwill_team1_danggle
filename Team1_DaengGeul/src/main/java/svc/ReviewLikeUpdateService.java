package svc;

import java.sql.Connection;

import dao.ReviewDAO;
import db.JdbcUtil;

public class ReviewLikeUpdateService {

	public boolean isReviewLike(int review_idx, String member_id, String review_like_done) {
		System.out.println("ReviewLikeUpdateService - isReviewLike()");
		
		boolean isReviewLike = false;
		
		Connection con = JdbcUtil.getConnection();
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		dao.setConnection(con);
		
		int insertCount = dao.insertReviewLike(review_idx, member_id, review_like_done);
		
		if(insertCount > 0) { // 성공시
			JdbcUtil.commit(con);
			isReviewLike = true;
			
		}else { // 실패시
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return isReviewLike;
	}


}
