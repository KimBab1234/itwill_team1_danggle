package svc;

import java.sql.Connection;

import dao.WishDAO;
import db.JdbcUtil;

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
