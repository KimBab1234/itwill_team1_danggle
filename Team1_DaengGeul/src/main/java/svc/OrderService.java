package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.OrderedDAO;
import dao.ProductDAO;
import db.JdbcUtil;
import vo.OrderBean;
import vo.ProductBean;

public class OrderService {

	//=====================주문 내역 저장=====================
	public boolean getProductList(OrderBean order) {

		System.out.println("=======================================");
		System.out.println("OrderPayProService- getProductList 진입");

		System.out.println("DB 연결");
		Connection con= JdbcUtil.getConnection();
		OrderedDAO dao = OrderedDAO.getInstance();
		dao.setConnection(con);

		boolean isSuccess = false;

		///주문내역 저장 및 멤버 포인트 업데이트
		boolean isSuccessOrder = dao.insertOrderList(order);

		boolean isSuccessUpdatePoint = dao.updateMemberPoint(order.getMember_id(), order.getOrder_point());

		if(isSuccessOrder && isSuccessUpdatePoint) {
			
			ProductDAO dao2 = ProductDAO.getInstance();
			boolean isSuccessUpdate = dao2.updateProductSell(order.getOrder_prod_idx(), order.getOrder_prod_cnt());
			
			if(isSuccessUpdate) {
				JdbcUtil.commit(con);
				isSuccess = true;
			} else {
				JdbcUtil.rollback(con);
			}
		} else {
			JdbcUtil.rollback(con);
		}

		JdbcUtil.close(con);

		return isSuccess;
	}

	//=====================주문 내역 가져오기=====================
	public ArrayList<OrderBean> getOrderList(String id, String period) {

		System.out.println("=======================================");
		System.out.println("OrderListService- getOrderList 진입");

		System.out.println("DB 연결");
		Connection con= JdbcUtil.getConnection();
		OrderedDAO dao = OrderedDAO.getInstance();
		dao.setConnection(con);

		ArrayList<OrderBean> orderList= dao.selectOrderList(id, period);

		JdbcUtil.close(con);

		return orderList;
	}

	//=====================주문 내역 1개 가져오기=====================
	public OrderBean getOrder(String id, String order_idx) {

		System.out.println("=======================================");
		System.out.println("OrderListService- getOrder 진입");

		System.out.println("DB 연결");
		Connection con= JdbcUtil.getConnection();
		OrderedDAO dao = OrderedDAO.getInstance();
		dao.setConnection(con);

		OrderBean order= dao.selectOrderDetail(id, order_idx);

		JdbcUtil.close(con);

		return order;
	}

	//=====================주문 상세 내역 가져오기=====================
	public ArrayList<ProductBean> getOrderProductList(String order_product_list) {

		System.out.println("=======================================");
		System.out.println("OrderListService- getOrderList 진입");

		System.out.println("DB 연결");
		Connection con= JdbcUtil.getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setCon(con);

		ArrayList<ProductBean> orderProdList= dao.selectOrderProductList(order_product_list);

		JdbcUtil.close(con);

		return orderProdList;
	}

	
}
