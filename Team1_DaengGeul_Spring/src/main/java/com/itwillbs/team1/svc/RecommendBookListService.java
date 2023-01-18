package com.itwillbs.team1.svc;

import java.sql.Connection;
import java.util.ArrayList;

import com.itwillbs.team1.dao.ProductDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.ProductBean;

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
