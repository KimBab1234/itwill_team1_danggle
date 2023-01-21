package svc;

import java.sql.Connection;
import java.util.List;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class MemberListService {

	// 회원목록 트랜젝션
	public List<MemberBean> getMemberList(String keyword, int startRow, int listLimit) {
		List<MemberBean> memberList = null;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		memberList = dao.selectMemberList(keyword, startRow, listLimit);
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return memberList;
	}

	
	// 회원목록 페이징 처리 트랜젝션
	public int getMemberListCount(String keyword) {
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		listCount = dao.selectMemberListCount(keyword);
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		return listCount;
	}

}