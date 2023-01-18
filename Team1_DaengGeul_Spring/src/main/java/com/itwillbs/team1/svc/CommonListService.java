package com.itwillbs.team1.svc;

import java.sql.Connection;
import java.util.List;

import com.itwillbs.team1.vo.CommonBean;
import com.itwillbs.team1.dao.CommonDAO;
import com.itwillbs.team1.db.JdbcUtil;

public class CommonListService {

	public List<CommonBean> getCommonList(CommonBean common) {

		List<CommonBean> commonList = null;
		
		Connection con=JdbcUtil.getConnection();
		
		CommonDAO dao = CommonDAO.getInstance(); 
		
		dao.setConnection(con);
		
		
		commonList = dao.selectList(common);
		
		JdbcUtil.close(con);
		
		return commonList;
	}

}
