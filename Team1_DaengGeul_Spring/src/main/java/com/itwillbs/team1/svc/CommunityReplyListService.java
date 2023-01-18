package com.itwillbs.team1.svc;

import java.sql.Connection;
import java.util.ArrayList;

import com.itwillbs.team1.dao.CommunityDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.Reply;

public class CommunityReplyListService {

	public ArrayList<Reply> communityReplyList(int idx, String id) {
		ArrayList<Reply> replyList = null;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		replyList = dao.selectReplyList(idx, id);
		
		JdbcUtil.close(con);
		
		return replyList;
	}


}
