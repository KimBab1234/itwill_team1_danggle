package com.itwillbs.team1.svc;

import java.sql.Connection;

import com.itwillbs.team1.dao.WishDAO;
import com.itwillbs.team1.db.JdbcUtil;

public class WishDeleteProService {

	public boolean deleteWish(String sId, String[] listArr) {
		boolean isDeleteWish = false;
		
		Connection con = JdbcUtil.getConnection();
		WishDAO dao = WishDAO.getInstance();
		dao.setConnection(con);
		
		isDeleteWish = dao.deleteWish(sId, listArr);
		JdbcUtil.commit(con);

		JdbcUtil.close(con);
		return isDeleteWish;
	}

}
