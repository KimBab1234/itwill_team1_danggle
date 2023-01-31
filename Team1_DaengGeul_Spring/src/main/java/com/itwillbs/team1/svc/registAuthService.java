package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.MemberDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.AuthVO;

public class registAuthService {

	public void registAuth(AuthVO auth) {

		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		int registCount = dao.registAuth(auth);
		if(registCount > 0) {
			JdbcUtil.commit(con);
		}
		
		JdbcUtil.close(con);
	}

}
