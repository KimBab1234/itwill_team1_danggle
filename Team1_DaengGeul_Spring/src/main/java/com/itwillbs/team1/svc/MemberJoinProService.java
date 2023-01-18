package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.MemberDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.MemberBean;

public class MemberJoinProService {

	public boolean joinMember(MemberBean member) {
		boolean isJoinSuccess = false; 
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		int insertCount = dao.insertMember(member);
		
		if(insertCount > 0 ) {
			JdbcUtil.commit(con);
			isJoinSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return isJoinSuccess;
		
	}
}
