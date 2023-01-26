package com.itwillbs.team1.svc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1.mapper.OrderMapper;
import com.itwillbs.team1.mapper.ProductMapper;
import com.itwillbs.team1.vo.OrderBean;
import com.itwillbs.team1.vo.OrderProdBean;
import com.itwillbs.team1.vo.ProductBean;

@Service
public class OrderService {
	
	@Autowired
	private OrderMapper mapper;
	@Autowired
	private ProductMapper mapper2;
	
	//=====================주문 내역 저장=====================
	public boolean setProductList(OrderBean order) {
		System.out.println("주문 내역 저장 - setProductList");
		boolean success=false;
		
		try {
			///주문내역 저장
			int insertCount = mapper.insertOrder(order);
			if(insertCount==0) {
				success = false;
			}

			insertCount=0;
			for(OrderProdBean prod : order.getOrder_prod_list()) {
				insertCount += mapper.insertOrderProd(prod);
			}
			if(insertCount != order.getOrder_prod_list().size()) {
				success = false;
			}

			///멤버 포인트 업데이트
			int updateCount = mapper.updateMemberPoint(order.getMember_id(), (-1)*order.getOrder_point());
			if(updateCount==0) {
				success = false;
			}

			///상품 재고 관리
			updateCount=0;
			for(OrderProdBean prod : order.getOrder_prod_list()) {
				updateCount += mapper2.updateProductSell(prod, prod.getIdx().charAt(0)=='B'? "book" : "goods");
			}

			if(updateCount != order.getOrder_prod_list().size()) {
				success = false;
			}

			for(OrderProdBean prod : order.getOrder_prod_list()) {
				if(!prod.getOpt().equals("-")) {
					System.out.println(prod);
					int updateOptCount = mapper2.updateProductOptSell(prod);
					if(updateOptCount==0) {
						success = false;
					}
				}
			}
			System.out.println("커밋");
			success=true;
		}catch (Exception e) {
			System.out.println("롤백");
			e.printStackTrace();
		} 
		finally {
		}
		return success;
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
