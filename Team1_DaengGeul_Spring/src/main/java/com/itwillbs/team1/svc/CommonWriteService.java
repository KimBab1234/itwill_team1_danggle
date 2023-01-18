package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.CommonDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.CommonBean;

public class CommonWriteService {

	public boolean registerCommon(CommonBean common, String sId) {
		
		boolean isWriteSuccess = false;
		
		Connection con=JdbcUtil.getConnection();
		
		CommonDAO dao = CommonDAO.getInstance();
		
		dao.setConnection(con);
		
		int insertCount = dao.insertCommon(common, sId);
		
		if(insertCount > 0) {
			JdbcUtil.commit(con);
			isWriteSuccess = true;
			
		}else {
			JdbcUtil.rollback(con);
		}
		
		return isWriteSuccess;
	}

}
