package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.CommunityDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.CommunityBean;

public class CommunityWriteService {

	public boolean registBoard(CommunityBean community) {
		boolean successWrite = false;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		int successCount = dao.writeCommunityBoard(community);
		
		if(successCount > 0) {
			JdbcUtil.commit(con);
			successWrite = true;
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return successWrite;
	}
	
}
