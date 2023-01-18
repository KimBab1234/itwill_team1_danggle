package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.CommunityDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.Reply;

public class CommunityReplyWriteService {

	public boolean registReply(Reply reply) {
		boolean successReply = false;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		int replyInsert = dao.writeReply(reply);
		
		if(replyInsert > 0) {
			JdbcUtil.commit(con);
			successReply = true;
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return successReply;
	}
	
}
