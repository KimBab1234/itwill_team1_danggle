package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.CommunityDAO;
import com.itwillbs.team1.db.JdbcUtil;

public class CommunityReplyDeleteService {

	public boolean replyDeleteCount(int board_idx, int reply_idx) {
		boolean deleteReply = false;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		int deleteCount = dao.replyDelete(board_idx, reply_idx);
		
		if(deleteCount > 0) {
			JdbcUtil.commit(con);
			deleteReply = true;
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return deleteReply;
	}
	
}
