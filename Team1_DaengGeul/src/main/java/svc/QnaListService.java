package svc;

import java.sql.Connection;
import java.util.List;

import dao.QnaDAO;
import db.JdbcUtil;
import vo.QnaBean;

public class QnaListService {

	public List<QnaBean> getQnaList(String sId,String keyword, int startRow, int listLimit) {
		
		List<QnaBean> qnaList = null;
		
		Connection con=JdbcUtil.getConnection();
		
		QnaDAO dao = QnaDAO.getInstance(); 
		
		dao.setConnection(con);
		
		qnaList = dao.selectQnaList(sId,keyword,startRow,listLimit);
		
		
		JdbcUtil.close(con);
		
		return qnaList;
	}

	public int getQnaListCount(String keyword) {
		
		int listCount = 0;
		
		Connection con=JdbcUtil.getConnection();
		
		QnaDAO dao = QnaDAO.getInstance();
		
		dao.setConnection(con);
		
		System.out.println("키워드 : " +keyword);
		listCount =  dao.selectQnaListCount(keyword);
		
		JdbcUtil.close(con);
		
		return listCount;
	}

}
