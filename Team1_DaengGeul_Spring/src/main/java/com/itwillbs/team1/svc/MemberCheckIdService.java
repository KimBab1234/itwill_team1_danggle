package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.MemberDAO;
import com.itwillbs.team1.db.JdbcUtil;

public class MemberCheckIdService {

	public boolean isExistId(String id) {
		boolean isExist = false;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		isExist = dao.selectMemberId(id);
		JdbcUtil.commit(con);

		JdbcUtil.close(con);
		
		return isExist;
	}

}
