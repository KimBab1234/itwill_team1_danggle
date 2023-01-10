package svc;

import java.sql.Connection;

import dao.CommunityDAO;
import db.JdbcUtil;
import vo.Like_community;

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
