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
		
		boolean isSuccess = false;

		///주문내역 저장
		isSuccess = isSuccess && mapper.insertOrderList(order);

		///멤버 포인트 업데이트
		isSuccess = isSuccess && mapper.updateMemberPoint(order.getMember_id(), order.getOrder_point());

		///상품 재고 관리
		isSuccess = isSuccess && mapper2.updateProductSell(order.getOrder_prod_idx(), order.getOrder_prod_opt() ,order.getOrder_prod_cnt());
		
		return isSuccess;
	}
	//=====================주문 내역 가져오기=====================
	public ArrayList<OrderBean> getOrderList(String id, String period) {
		return mapper.selectOrderList(id, period);
	}
	//=====================주문 내역 1개 가져오기=====================
	public OrderBean getOrder(String id, String order_idx) {
		return mapper.selectOrderDetail(id, order_idx);
	}
	//=====================주문 상세 내역 가져오기=====================
	public ArrayList<ProductBean> getOrderProductList(String order_product_list) {
		return mapper2.selectOrderProductList(order_product_list);
	}
}
