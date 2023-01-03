package svc;

import java.sql.Connection;

import dao.ReviewDAO;
import db.JdbcUtil;
import vo.ReviewBean;

public class ReviewModifyProService {

	public boolean isReviewWriter(ReviewBean review) {
		
		boolean isReviewWriter = false;
		
		// 공통작업 1
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업 2
		ReviewDAO dao = ReviewDAO.getInstance();
		
		// 공통작업 3
		dao.setConnection(con);
		
		// 5. ReviewModifyProService 클래스의 인스턴스 생성 및 isReviewWriter() 메서드 호출하여
		// 글 삭제 가능 여부(= 패스워드 일치 여부) 판별 요청
		//   => 파라미터 : 글번호, 패스워드    리턴타입 : boolean(isReviewWriter)
		isReviewWriter = dao.isReviewWriter(review.getReview_idx(), review.getReview_passwd());
		
		// 공통작업 4
		JdbcUtil.close(con);
		
		return isReviewWriter;
	}

	public boolean modifyReview(ReviewBean review) {
		
		boolean isModifySuccess = false;
		
		// 공통작업 1
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업 2
		ReviewDAO dao = ReviewDAO.getInstance();
		
		// 공통작업 3
		dao.setConnection(con);
		
		int updateCount = dao.updateReview(review);
		
		if(updateCount > 0) {
			JdbcUtil.commit(con);
			isModifySuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		// 공통작업 4
		JdbcUtil.close(con);
		
		return isModifySuccess;
	}

}
