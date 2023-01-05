package svc;

import java.sql.Connection;

import dao.WishDAO;
import db.JdbcUtil;

public class WishProService {

	public boolean registWish(String product_idx, String sId) {
		boolean isRegistWish = false;
		
		Connection con = JdbcUtil.getConnection();
		WishDAO dao = WishDAO.getInstance();
		dao.setConnection(con);
		
		isRegistWish = dao.registWish(product_idx, sId);
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return isRegistWish;
	}
	
}