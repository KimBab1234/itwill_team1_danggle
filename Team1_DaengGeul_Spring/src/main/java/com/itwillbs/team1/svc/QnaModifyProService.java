package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.QnaDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.QnaBean;

public class QnaModifyProService {

	public boolean isQnaWriter(QnaBean qna) {
		
		boolean isBoardWriter = false;
		
		Connection con = JdbcUtil.getConnection();
		
		QnaDAO dao = QnaDAO.getInstance();
		
		dao.setConnection(con);
		
		isBoardWriter = dao.isQnaWriter(qna.getQna_idx(),qna.getMember_id());
		
		JdbcUtil.close(con);
		return isBoardWriter;
	}

	public boolean modifyQna(QnaBean qna) {
		boolean isModifySuccess = false;
		
		Connection con=JdbcUtil.getConnection();
		
		QnaDAO dao = QnaDAO.getInstance();
		
		dao.setConnection(con);
		
		int modifyCount = dao.updateQna(qna);
		if(modifyCount>0) {
			JdbcUtil.commit(con);
			isModifySuccess=true;
		}else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return false;
	}

}
