package svc;

import java.sql.Connection;

import dao.CommunityDAO;
import db.JdbcUtil;
import vo.Like_community;

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
