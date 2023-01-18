package com.itwillbs.team1.svc.before;

import java.sql.Connection;

import com.itwillbs.team1.dao.ProductDAO;
import com.itwillbs.team1.db.JdbcUtil;
import com.itwillbs.team1.vo.ProductBean;

public class ProductDetailService {

	public ProductBean getProduct(String product_idx) {

		System.out.println("=======================================");
		System.out.println("ProductDetailService- getProduct 진입");

		System.out.println("DB 연결");
		Connection con= JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);

		ProductBean product = dao.selectProduct(product_idx);

		JdbcUtil.commit(con);
		JdbcUtil.close(con);

		return product;
	}


}
