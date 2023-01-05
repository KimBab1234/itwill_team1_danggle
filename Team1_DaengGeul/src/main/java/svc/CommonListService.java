package svc;

import java.sql.Connection;
import java.util.List;

import vo.CommonBean;
import dao.CommonDAO;
import db.JdbcUtil;

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
