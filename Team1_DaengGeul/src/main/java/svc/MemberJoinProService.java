package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class MemberJoinProService {

	public boolean joinMember(MemberBean member) {
		boolean isJoinSuccess = false; 
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		int insertCount = dao.insertMember(member);
		
		if(insertCount > 0 ) {
			JdbcUtil.commit(con);
			isJoinSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return isJoinSuccess;
		
	}
}
