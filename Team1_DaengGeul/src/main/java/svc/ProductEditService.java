package svc;

import java.sql.Connection;

import dao.ProductDAO;
import db.JdbcUtil;
import vo.ProductBean;

public class ProductEditService {

	public ProductBean getBook(String product_idx) {
		System.out.println("ProductEditService(getBook) - 책 수정");
		ProductBean book = null;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);
		
		book = dao.selectBook(product_idx);
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return book;
	}

	public ProductBean getGoods(String product_idx) {
		System.out.println("ProductEditService(getGoods) - 굿즈 수정");
		ProductBean goods = null;
		
		Connection con = JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);
		
		goods = dao.selectGoods(product_idx);
		
		JdbcUtil.commit(con);
		
		JdbcUtil.close(con);
		
		return goods;
	}

}
