package svc;

import java.sql.Connection;

import dao.CommunityDAO;
import db.JdbcUtil;
import vo.Like_community;

public class CommunityLikeService {

	public boolean likeCommunity(Like_community like) {
		boolean likeSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		int likeCount = dao.communityLike(like);
		
		if(likeCount > 0) {
			JdbcUtil.commit(con);
			likeSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return likeSuccess;
	}

}
