package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;

public class SearchNEService {

	public String[] SearchNE(String id) {
		String[] findInfo = null;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		findInfo = dao.SearchNE(id);
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return findInfo;
	}

}
