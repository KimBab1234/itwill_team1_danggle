package svc;

import java.sql.Connection;
import java.util.List;

import dao.CommunityDAO;
import db.JdbcUtil;
import vo.CommunityBean;

public class CommunityListService {

	public List<CommunityBean> getList(int type) {
		List<CommunityBean> CommunityList = null;
		
		Connection con = JdbcUtil.getConnection();
		CommunityDAO dao = CommunityDAO.getInstance();
		dao.setConnection(con);
		
		CommunityList = dao.selectCommunityList(type);
		
		JdbcUtil.close(con);
		
		return CommunityList;
	}
}
