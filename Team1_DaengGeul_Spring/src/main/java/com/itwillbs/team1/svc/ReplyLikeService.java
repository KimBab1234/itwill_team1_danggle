package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.CommunityDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.Like_reply;

public class ReplyLikeService {

	public boolean likeReply(Like_reply like) {
		boolean likeSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		int likeCount = dao.replyLike(like);
		
		if(likeCount > 0) {
			JdbcUtil.commit(con);
			likeSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		return likeSuccess;
	}
	
	
}
