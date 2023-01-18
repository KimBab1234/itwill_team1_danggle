package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.ReviewDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.ReviewBean;

public class ReviewDetailService {
public ReviewBean getReview(int review_idx, boolean isUpdateReadCount, String sId) {
		
		// 글 상세정보 조회
		// 글번호와 함께 조회수 증가여부를 파라미터로 전달
		
		ReviewBean review  = null;
		
		// 공통작업 1
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업 2
		ReviewDAO dao = ReviewDAO.getInstance();
		
		// 공통작업 3
		dao.setConnection(con);
		review = dao.selectReview(review_idx, sId);
		
//		System.out.println("review: " + review);
		
		if(review != null && isUpdateReadCount ) {
			int updateCount = dao.updateReadCount(review_idx);
			
			if(updateCount > 0) {
			
			JdbcUtil.commit(con);
			
			review.setReview_readcount(review.getReview_readcount()+1);
			}
			
		}
		
		// 공통작업 4
		JdbcUtil.close(con);
		
		return review;
	}

}