package svc;

import java.sql.Connection;

import dao.CommonDAO;
import db.JdbcUtil;
import vo.CommonBean;

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
