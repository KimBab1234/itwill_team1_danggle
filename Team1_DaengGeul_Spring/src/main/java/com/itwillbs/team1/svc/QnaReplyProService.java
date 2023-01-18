package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.QnaDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.QnaBean;

public class QnaReplyProService {

	public boolean registReplyQna(QnaBean qna) {

		boolean isWriteSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		// 공통작업-2. BoardDAO 객체 가져오기
		QnaDAO dao = QnaDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		int insertCount = dao.insertReplyQna(qna);
		
		if(insertCount > 0) { // 성공 시
			JdbcUtil.commit(con);
			isWriteSuccess = true;
		} else { // 실패 시
			JdbcUtil.rollback(con);
		}
		
		// 공통작업-4. Connection 객체 반환하기
		JdbcUtil.close(con);
		
		return isWriteSuccess;
	}

}
