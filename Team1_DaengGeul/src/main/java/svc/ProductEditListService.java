package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

public class ProductEditListService {

	public ArrayList<ProductBean> getProductList(int startRow, int listLimit) {
		System.out.println("상품 목록 조회 서비스 페이지");
		ArrayList<ProductBean> productList = null;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
	
		dao.setCon(con);
		
		productList = dao.selectProductList(startRow, listLimit);
		
		JdbcUtil.close(con);
		
		return productList;
	}

	public int getProductListCount() {
		// 한 페이지에서 조회할 목록 개수 계산
		
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);
		
		listCount = dao.getProductListCount();
		return listCount;
	}

}
