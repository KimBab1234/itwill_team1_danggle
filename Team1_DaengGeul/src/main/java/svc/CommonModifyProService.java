package svc;

import java.sql.Connection;

import dao.CommonDAO;
import db.JdbcUtil;
import vo.CommonBean;

public class CommonModifyProService {

	public boolean isCommonWriter(CommonBean common, String sId) {
		
		boolean isCommonWriter = false;
		
		Connection con = JdbcUtil.getConnection();
		
		CommonDAO dao = CommonDAO.getInstance();
		
		dao.setConnection(con);
		
		isCommonWriter = dao.isCommonWriter(common.getCommon_idx(), sId);
		
		JdbcUtil.close(con);
		
		return isCommonWriter;
	}

	public boolean modifyCommon(CommonBean common) {
		
		boolean isModifySuccess = false;
		
		Connection con=JdbcUtil.getConnection();
		
		CommonDAO dao = CommonDAO.getInstance();
		
		dao.setConnection(con);
		
		int modifyCount = dao.updateCommon(common);
		if(modifyCount>0) {
			JdbcUtil.commit(con);
			isModifySuccess=true;
		}else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return isModifySuccess;
	}

}
