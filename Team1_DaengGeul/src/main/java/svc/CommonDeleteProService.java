package svc;

import java.sql.Connection;

import dao.CommonDAO;
import db.JdbcUtil;

public class CommonDeleteProService {

	public boolean isCommonWriter(int common_idx, String sId) {
		
		boolean isCommonWriter = false;
		
		Connection con=JdbcUtil.getConnection();
		
		CommonDAO dao = CommonDAO.getInstance();
		
		dao.setConnection(con);
		
		isCommonWriter = dao.isCommonWriter(common_idx,sId);
		
		JdbcUtil.close(con);
		
		return isCommonWriter;
	}

	public boolean removeCommon(int common_idx) {
		
		boolean isDeleteSuccess = false;
		
		Connection con=JdbcUtil.getConnection();
		
		CommonDAO dao = CommonDAO.getInstance();
		
		dao.setConnection(con);
		
		int deleteCount = dao.deleteCommon(common_idx);
		
		if(deleteCount>0) {
			JdbcUtil.commit(con);
			isDeleteSuccess=true;
		}else {
			JdbcUtil.rollback(con);
		}
		JdbcUtil.close(con);
		
		return isDeleteSuccess;
	}


}
