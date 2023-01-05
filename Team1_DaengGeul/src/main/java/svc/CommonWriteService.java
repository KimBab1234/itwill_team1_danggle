package svc;

import java.sql.Connection;

import dao.CommonDAO;
import db.JdbcUtil;
import vo.ActionForward;
import vo.CommonBean;

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
