package svc;

import java.sql.Connection;

import dao.CommunityDAO;
import db.JdbcUtil;
import vo.CommunityBean;

public class CommunityDetailService {

	public CommunityBean communityDetail(int idx) {
		CommunityBean community = null;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		community = dao.selectDetailList(idx);
		
		JdbcUtil.close(con);
		
		return community;
	}

}
