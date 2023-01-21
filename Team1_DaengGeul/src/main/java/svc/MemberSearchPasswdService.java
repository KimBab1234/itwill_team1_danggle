package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;

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
