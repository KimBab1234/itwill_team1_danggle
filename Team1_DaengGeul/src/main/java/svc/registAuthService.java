package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.AuthBean;

public class registAuthService {

	public void registAuth(AuthBean auth) {

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
