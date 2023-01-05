package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

public class ProductDeleteService {

	public ProductBean selectFileName(String product_idx) {
		System.out.println("selectFileName - 파일 이름 조회");
		ProductBean product = null;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);
		
		product = dao.selectFileName(product_idx);
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return product;
	}

	public int removeProduct(String product_idx) {
		System.out.println("removeProduct - 상품 삭제");
		int deleteCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);
		
		deleteCount = dao.deleteProduct(product_idx);
		
		if(deleteCount > 0) {
			JdbcUtil.commit(con);
		}else {
			JdbcUtil.rollback(con);
		}
		
		return deleteCount;
	}

}
