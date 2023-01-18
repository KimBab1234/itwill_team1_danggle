package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.QnaDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.QnaBean;

public class QnaDetailService {

	public QnaBean getQna(int qna_idx) {
		
		QnaBean qna = null;
		
		Connection con=JdbcUtil.getConnection();
		
		QnaDAO dao = QnaDAO.getInstance();
		
		dao.setConnection(con);
		
		qna = dao.selectQna(qna_idx);
		
		if(qna != null) {
			JdbcUtil.commit(con);
		}
		
		JdbcUtil.close(con);
		return qna;
	}

}
