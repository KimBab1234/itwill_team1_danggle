package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;

public class MemberSearchIdService {

	public String searchId(String email) {
		String findId = null;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		findId = dao.searchId(email);
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return findId;
	}

}
