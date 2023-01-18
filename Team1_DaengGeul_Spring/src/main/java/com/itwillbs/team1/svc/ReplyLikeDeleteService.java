package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.CommunityDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.Like_reply;

public class ReplyLikeDeleteService {

	public int replyLikeDelete(Like_reply like_reply) {
		int deleteCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		deleteCount = dao.replyLikeDelete(like_reply);
		
		if(deleteCount > 0) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		
		return deleteCount;
	}
	
}
