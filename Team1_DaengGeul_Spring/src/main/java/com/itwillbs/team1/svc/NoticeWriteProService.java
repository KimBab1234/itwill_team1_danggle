package com.itwillbs.team1.svc;

import java.sql.Connection;


import com.itwillbs.team1.dao.NoticeDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.NoticeBean;

public class NoticeWriteProService {

	public boolean registerNotice(NoticeBean notice) {
		
		boolean isWriteSuccess = false;
		
		Connection con=JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		int insertCount = dao.insertNotice(notice);
		
		if(insertCount > 0) {
			JdbcUtil.commit(con);
			isWriteSuccess = true;
		}else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return isWriteSuccess;
	}

}
