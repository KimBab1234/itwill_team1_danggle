package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.MemberDAO;
import com.itwillbs.team1.db.JdbcUtil;

public class MemberLoginProService {

	public boolean loginMember(String id, String passwd) {
		boolean isloginSuccess = false; 
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		isloginSuccess = dao.loginMember(id, passwd);
		
		if(isloginSuccess) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return isloginSuccess;
		
	}
	
}