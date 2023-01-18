package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.MemberDAO;
import com.itwillbs.team1.db.JdbcUtil;

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