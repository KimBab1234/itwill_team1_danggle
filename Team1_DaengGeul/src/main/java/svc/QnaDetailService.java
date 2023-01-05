package svc;

import java.sql.Connection;

import dao.QnaDAO;
import db.JdbcUtil;
import vo.QnaBean;

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
