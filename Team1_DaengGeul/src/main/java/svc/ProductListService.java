package svc;

import java.sql.Connection;
import java.util.List;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

public class ProductListService {

	public List<ProductBean> getProductList(String product_type, String sort, String keyword, int pageStartRow, int pageProductCount) {

		System.out.println("=======================================");
		System.out.println("ProductListService- getProductList 진입");

		System.out.println("DB 연결");
		Connection con= JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);

		List<ProductBean> productList = dao.selectProductList(product_type, sort, keyword, pageStartRow,pageProductCount);
		JdbcUtil.commit(con);
		JdbcUtil.close(con);

		return productList;
	}
	
	public int getProductCount(String product_type, String sort, String keyword) {

		System.out.println("=======================================");
		System.out.println("ProductListService- getProductCount 진입");

		System.out.println("DB 연결");
		Connection con= JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);

		int productCount = dao.selectProductCount(product_type, sort, keyword);
		JdbcUtil.commit(con);
		JdbcUtil.close(con);

		return productCount;
	}

}
