package svc;

import java.sql.Connection;

import dao.CommunityDAO;
import db.JdbcUtil;
import vo.Like_reply;

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
