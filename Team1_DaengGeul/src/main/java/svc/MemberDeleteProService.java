package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;

public class MemberDeleteProService {

	public int deleteMember(String id, String passwd) {
		int deleteMemberCount = 0;
		boolean isRightUser;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		isRightUser = dao.isRightUser(id, passwd);
		if(isRightUser) {
			deleteMemberCount = dao.deleteMember(id);
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return deleteMemberCount;
	}

}