package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;

public class MemberCheckCertProService {

	public String getAuthCode(String id) {
		String selectedCertNum = null;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		selectedCertNum = dao.selectAuthCode(id);

		if(selectedCertNum != null) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);			
		}
		
		JdbcUtil.close(con);
		
		return selectedCertNum;
	}

}
