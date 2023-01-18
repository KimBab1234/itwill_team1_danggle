package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.CommunityDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.Like_community;

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
