package svc;

import java.sql.Connection;

import dao.CommunityDAO;
import db.JdbcUtil;
import vo.CommunityBean;

public class CommunityWriteService {

	public boolean registBoard(CommunityBean community) {
		boolean successWrite = false;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		int successCount = dao.writeCommunityBoard(community);
		
		if(successCount > 0) {
			JdbcUtil.commit(con);
			successWrite = true;
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return successWrite;
	}
	
}
