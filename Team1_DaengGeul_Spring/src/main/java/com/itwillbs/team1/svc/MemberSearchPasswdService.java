package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.MemberDAO;
import com.itwillbs.team1.db.JdbcUtil;

public class MemberSearchPasswdService {

	public void searchPasswd(String id, String tempPasswd) {
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		dao.UpdateTempPasswd(id, tempPasswd);
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
	}

}
