package svc;

import java.sql.Connection;
import java.util.List;

import dao.WishDAO;
import db.JdbcUtil;
import vo.WishlistBean;

public class WishlistService {

	// 찜목록 트랜젝션
	public List<WishlistBean> wishlist(String sId, String keyword, int startRow, int listLimit) {
		List<WishlistBean> wishlist = null;
		
		Connection con = JdbcUtil.getConnection();
		WishDAO dao = WishDAO.getInstance();
		dao.setConnection(con);
		
		wishlist = dao.selectWishlist(sId, keyword, startRow, listLimit);
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return wishlist;
	}

	// 찜목록 페이징 처리 트랜젝션
	public int getWishlistCount(String sId, String keyword) {
		int wishlistCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		WishDAO dao = WishDAO.getInstance();
		dao.setConnection(con);
		
		wishlistCount = dao.wishlistCount(sId, keyword);
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		return wishlistCount;
	}

}