package svc;

import java.sql.Connection;

import dao.CommunityDAO;
import db.JdbcUtil;


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
