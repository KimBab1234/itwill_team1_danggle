package svc;

import java.sql.Connection;

import dao.CommunityDAO;
import db.JdbcUtil;
import vo.CommunityBean;

public class CommunityModifyService {

	public boolean communityModify(CommunityBean community) {
		boolean successModify = false;
		System.out.println("communityModify");
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		int successCount = dao.successModify(community);
		
		if(successCount > 0) {
			JdbcUtil.commit(con);
			successModify = true;
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return successModify;
	}

}
