package svc;

import java.sql.Connection;

import dao.CommunityDAO;
import db.JdbcUtil;
import vo.CommunityBean;

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
