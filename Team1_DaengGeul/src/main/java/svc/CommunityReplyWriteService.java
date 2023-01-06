package svc;

import java.sql.Connection;

import dao.CommunityDAO;
import db.JdbcUtil;
import vo.Reply;

public class CommunityReplyWriteService {

	public boolean registReply(Reply reply) {
		boolean successReply = false;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		int replyInsert = dao.writeReply(reply);
		
		if(replyInsert > 0) {
			JdbcUtil.commit(con);
			successReply = true;
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return successReply;
	}
	
}
