package com.itwillbs.team1.svc;

import java.sql.Connection;
import java.util.List;

import com.itwillbs.team1.dao.CommunityDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.CommunityBean;

public class CommunityListService {

	public List<CommunityBean> getList(int type, String keyword, int startRow, int listLimit) {
		List<CommunityBean> CommunityList = null;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		CommunityList = dao.selectCommunityList(type, keyword, startRow, listLimit);
		JdbcUtil.close(con);
		
		return CommunityList;
	}

	public int getBoardListCount(String keyword, int board_type) {
			int BoardListCount = 0;
			
			Connection con = JdbcUtil.getConnection();
			CommunityDAO dao = CommunityDAO.getInstance();
			dao.setConnection(con);
			
			BoardListCount = dao.listCount(keyword,board_type);
			
			JdbcUtil.close(con);
			
			return BoardListCount;
		}
}
