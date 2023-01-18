package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.CommonDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.CommonBean;

public class CommonDetailService {

	public CommonBean getCommon(int common_idx) {
		
		CommonBean common = null;
		
		Connection con=JdbcUtil.getConnection();
		
		CommonDAO dao = CommonDAO.getInstance();
		
		dao.setConnection(con);
		
		common = dao.selectCommon(common_idx);
		
		if(common != null) {
			JdbcUtil.commit(con);
		}
		JdbcUtil.close(con);
		return common;
	}

}
