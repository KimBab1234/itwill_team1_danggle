package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.MemberDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.MemberBean;

public class MemberPointService {

	public int getMemberPoint(String id) {
		int point = -1;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		point = dao.getMemberPoint(id);
		
		if(point != -1) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return point;
	}

}