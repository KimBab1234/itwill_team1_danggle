package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.CommunityDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.CommunityBean;

public class CommunityDetailService {

	public CommunityBean communityDetail(int idx,boolean updateReadcount) {
		CommunityBean community = null;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		community = dao.selectDetailList(idx);
		
		if(community != null) {
			int readCount = dao.updateReadcount(idx);
			
			if(readCount > 0) {
				JdbcUtil.commit(con);
				community.setBoard_readcount(community.getBoard_readcount()+1);
			}
		}
		JdbcUtil.close(con);
		
		return community;
	}

}
