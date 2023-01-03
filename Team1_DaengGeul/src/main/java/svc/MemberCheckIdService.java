package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;

public class MemberCheckIdService {

	public boolean isExistId(String id) {
		boolean isExist = false;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		isExist = dao.selectMemberId(id);

		JdbcUtil.close(con);
		
		return isExist;
	}

}
