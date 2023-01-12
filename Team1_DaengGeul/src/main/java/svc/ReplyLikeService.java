package svc;

import java.sql.Connection;

import dao.CommunityDAO;
import db.JdbcUtil;
import vo.Like_reply;

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
