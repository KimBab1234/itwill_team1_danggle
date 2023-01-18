package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.CommunityDAO;
import com.itwillbs.team1.db.JdbcUtil;

public class CommunityDeleteService {

	public boolean deletBoard(int board_idx) {
		boolean deleteBoard = false;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		int deleteCount = dao.deleteBoard(board_idx);
		
		System.out.println(deleteCount);
		
		if(deleteCount > 0) {
			JdbcUtil.commit(con);
			deleteBoard = true;
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return deleteBoard;
	}

}
