package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.CommunityDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.Like_community;

public class CommunityLikeDeleteService {

	public int likeDeleteCommunity(Like_community like, int board_idx) {
		int deleteSuccess = 0;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		 deleteSuccess = dao.communityDeleteLike(like, board_idx);
		
		if(deleteSuccess > 0) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		
		return deleteSuccess;
	}

}
