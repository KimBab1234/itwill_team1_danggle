package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.ReviewDAO;
import com.itwillbs.team1.db.JdbcUtil;

public class ReviewLikeUpdateService {

	public int isReviewLike(int review_idx, String member_id, String review_like_done) {
		System.out.println("ReviewLikeUpdateService - isReviewLike()");
		
		Connection con = JdbcUtil.getConnection();
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		dao.setConnection(con);
		
	  int review_like_count = dao.insertReviewLike(review_idx, member_id, review_like_done);
		
		if(review_like_count > -1) { // 성공시
			JdbcUtil.commit(con);
			
		}else { // 실패시
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return review_like_count;
	}


}

