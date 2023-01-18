package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.CommunityDAO;
import com.itwillbs.team1.db.JdbcUtil;


public class CommunityLikeCountService {

	public int likeCountService(int idx) {
		int likeCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		likeCount = dao.communityLikeCount(idx);
		
		return likeCount;
	}

}
