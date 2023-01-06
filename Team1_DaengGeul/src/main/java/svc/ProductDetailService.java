package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

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
