package svc;

import java.sql.Connection;

import dao.OrderedDAO;
import dao.ReviewDAO;
import db.JdbcUtil;
import vo.ReviewBean;

public class ReviewWriteProService {

	public boolean registReview(ReviewBean review) {
		System.out.println("ReviewWriteProService - registReview()");
		
		// 1.
		boolean isWriteSuccess = false;
		
		// 2. 
		Connection con = JdbcUtil.getConnection();
		
		// 3.
		ReviewDAO dao = ReviewDAO.getInstance();
		
		// 4.
		dao.setConnection(con);
		
		// 5.
		int insertCount = dao.insertReview(review);
		
		// 6.
		if(insertCount > 0) { // 성공시
			
			OrderedDAO dao2 = OrderedDAO.getInstance();
			
			boolean updateSuccess=dao2.updateMemberPoint(review.getMember_id(), 500);
			
			if(updateSuccess) {
				JdbcUtil.commit(con);
				isWriteSuccess = true;
			} else {
				JdbcUtil.rollback(con);
			}
			
		}else { // 실패시
			JdbcUtil.rollback(con);
		}
		
		// 7.
		JdbcUtil.close(con);
		
		// 8.
		return isWriteSuccess; // ReviewWriteProAction으로 리턴
		
	}


}
