package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.MemberDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.MemberBean;

public class MemberInfoService {

	public MemberBean getMemberInfo(String id) {
		MemberBean member = null;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		member = dao.getMemberInfo(id);
		
		if(member != null) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return member;
	}

}