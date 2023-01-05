package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

public class RecommendBookListService {

	public ArrayList<ProductBean> selectRecommendBook() {
		ArrayList<ProductBean> recoList = null;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);
		
		recoList = dao.selectRecoBook();
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return recoList;
	}

}
