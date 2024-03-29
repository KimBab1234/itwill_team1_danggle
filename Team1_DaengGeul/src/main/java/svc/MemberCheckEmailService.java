package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;

public class MemberCheckEmailService {

	public boolean checkEmail(String email) {
		boolean isExistEmail = false;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		isExistEmail = dao.selectMemberEmail(email);
		JdbcUtil.commit(con);

		JdbcUtil.close(con);
		
		return isExistEmail;
		
	}

}
