package com.itwillbs.team1.svc;

import java.sql.Connection;
import java.util.List;

import com.itwillbs.team1.dao.QnaDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.QnaBean;

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

	public int getQnaListCount(String sId,String keyword) {
		
		int listCount = 0;
		
		Connection con=JdbcUtil.getConnection();
		
		QnaDAO dao = QnaDAO.getInstance();
		
		dao.setConnection(con);
		
		System.out.println("키워드 : " +keyword);
		listCount =  dao.selectQnaListCount(sId,keyword);
		
		JdbcUtil.close(con);
		
		return listCount;
	}

}
