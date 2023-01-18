package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.MemberDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.MemberBean;

public class MemberUpdateProService {

	public int updateMember(MemberBean member, String newPasswd, String newPasswd2) {
		int updateCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);

		boolean isRightUser = dao.isRightUser(member.getMember_id(), member.getMember_passwd());
		
		// 유저확인
		if(isRightUser) {
			
			// 비밀번호 변경 (+회원정보수정) 시
			if(newPasswd != null && !newPasswd.equals("") && newPasswd.equals(newPasswd2)) {
				updateCount = dao.updateMember(member, newPasswd);
				JdbcUtil.commit(con);
			// 회원정보만 수정 시
			} else {
				updateCount = dao.updateMember(member, member.getMember_passwd());
				JdbcUtil.commit(con);
			}
			
		}
		
		JdbcUtil.close(con);
		
		return updateCount;
	}

}
