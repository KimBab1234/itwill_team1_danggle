package com.itwillbs.team1.svc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1.mapper.OrderMapper;
import com.itwillbs.team1.mapper.ProductMapper;
import com.itwillbs.team1.vo.OrderBean;
import com.itwillbs.team1.vo.ProductBean;

@Service
public class OrderService {

	@Autowired
	private OrderMapper mapper;
	private ProductMapper mapper2;

	//=====================주문 내역 저장=====================
	public boolean setProductList(OrderBean order) {

		///주문내역 저장
		int insertCount = mapper.insertOrderList(order);
		if(insertCount==0) {
			return false;
		}

		///멤버 포인트 업데이트
		int updateCount = mapper.updateMemberPoint(order.getMember_id(), order.getOrder_point());
		if(updateCount==0) {
			return false;
		}

		///상품 재고 관리
		for(OrderBean prod : order.getOrder_prod_list()) {
			updateCount = mapper2.updateProductSell(order.getOrder_prod_list());
		}
		if(updateCount==0) {
			return false;
		}

		return true;
	}
	//=====================주문 내역 가져오기=====================
	public ArrayList<OrderBean> getOrderList(String id, String period) {
		ArrayList<OrderBean> orderList = mapper.selectOrderList(id, period);

		for(OrderBean order : orderList) {
			order.setOrder_prod_list(mapper.selectOrderProdList(order.getOrder_idx()));
		}
		return orderList;
	}
	//=====================주문 내역 1개 가져오기=====================
	public OrderBean getOrder(String id, String order_idx) {
		OrderBean order = mapper.selectOrderDetail(id, order_idx);
		order.setOrder_prod_list(mapper.selectOrderProdList(order.getOrder_idx()));
		return order;
	}
	//=====================주문 상세 내역 가져오기=====================
	public ArrayList<ProductBean> getOrderProductList(String order_product_list) {
		return mapper2.selectOrderProductList(order_product_list);
	}
}
