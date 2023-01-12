package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

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