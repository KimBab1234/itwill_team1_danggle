package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.ReviewDAO;
import com.itwillbs.team1.db.JdbcUtil;

public class ReviewDeleteProService {

	public boolean isReviewWriter(int review_idx, String review_passwd) {
		boolean isReviewWriter = false;
		
		// 공통작업 1
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업 2
		ReviewDAO dao = ReviewDAO.getInstance();
		
		// 공통작업 3
		dao.setConnection(con);
		
		// 5. ReviewDeleteProService 클래스의 인스턴스 생성 및 isReviewWriter() 메서드 호출하여
		// 글 삭제 가능 여부(= 패스워드 일치 여부) 판별 요청
		//   => 파라미터 : 글번호, 패스워드    리턴타입 : boolean(isReviewWriter)
		isReviewWriter = dao.isReviewWriter(review_idx, review_passwd);
		
		// 공통작업 4
		JdbcUtil.close(con);
		
		return isReviewWriter;
	}

	public boolean removeReview(int review_idx) {
		boolean isDeleteSuccess = false;
		
		// 공통작업 1
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업 2
		ReviewDAO dao = ReviewDAO.getInstance();
		
		// 공통작업 3
		dao.setConnection(con);
		
		int deleteCount = dao.deleteReview(review_idx);
		
		// 리턴받은 결과를 판별하여 commit, rollback
		if(deleteCount > 0) {
			JdbcUtil.commit(con);
			isDeleteSuccess = true;
		}else {
			JdbcUtil.rollback(con);
		}
		
		
		// 공통작업 4
		JdbcUtil.close(con);
		
		return isDeleteSuccess;
	}



}
