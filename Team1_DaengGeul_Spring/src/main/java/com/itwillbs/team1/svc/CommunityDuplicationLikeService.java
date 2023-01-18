package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.CommunityDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.Like_community;

public class CommunityDuplicationLikeService {

	public boolean duplicateLike(Like_community like) {
		boolean duplicationLike = false;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		duplicationLike = dao.duplicateLike(like);
		
		JdbcUtil.close(con);
		
		return duplicationLike;
	}
}
