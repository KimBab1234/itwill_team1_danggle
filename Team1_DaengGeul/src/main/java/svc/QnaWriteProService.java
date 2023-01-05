package svc;

import java.sql.Connection;

import javax.servlet.http.HttpSession;

import dao.QnaDAO;
import db.JdbcUtil;
import vo.QnaBean;

public class QnaWriteProService {

	public boolean registerQna(QnaBean qna, String sId) {

		boolean isWriteSuccess = false;
		
		
		Connection con=JdbcUtil.getConnection();
		
		QnaDAO dao = QnaDAO.getInstance();
		
		dao.setConnection(con);
		
		System.out.println("service" + sId);
		
		
		int insertCount = dao.insertQna(qna, sId);
		
		if(insertCount > 0) {
			JdbcUtil.commit(con);
			isWriteSuccess = true;
			
		}else {
			JdbcUtil.rollback(con);
		}
		
		return isWriteSuccess;
	}

}
