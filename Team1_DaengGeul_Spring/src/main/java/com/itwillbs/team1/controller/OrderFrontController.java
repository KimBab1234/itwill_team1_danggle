package com.itwillbs.team1.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.team1.svc.OrderService;
import com.itwillbs.team1.vo.OrderBean;

@Controller
public class OrderFrontController {

	////연결된 클래스의 객체를 필요할때마다 자동으로 객체 생성해서 주입해줌
	@Autowired
	private OrderService service;
	
	@GetMapping(value = "/CartListForm")
	public String ProductDetail() {
		System.out.println("장바구니화면");
		
		

		return "order/cart_form";
	}

	@GetMapping(value = "/OrderPayForm")
	public String OrderPayForm() {
		System.out.println("결제 화면");



		return "order/pay";
	}
	
	@GetMapping(value = "/OrderPayPro")
	public String OrderPayPro() {
		System.out.println("결제 진행 화면");
		
		
		
		return "order/pay_end";
	}
	
	@GetMapping(value = "/OrderList")
	public String OrderList(HttpSession session, String id, String period, Model model) {
		System.out.println("주문 내역 화면");
		
		String sId = (String)session.getAttribute("sId");

		////관리자일 경우
		if(sId.equals("admin")) {
			//파라미터로 들어온 아이디 있는지 조회
			if(id==null) {
				//없으면 session에 저장되어있으니 가져오기
				id = (String)session.getAttribute("customerId");
			} else {
				///있으면 session에 없을테니 저장해주기
				session.setAttribute("customerId",id);
			}
		} else {
			id = sId;
		}

		if(period==null) {
			period="1 WEEK";
		}

		ArrayList<OrderBean> orderList= service.getOrderList(id, period);

		model.addAttribute("orderList", orderList);
		
		
		
		return "order/order_list";
	}
	@GetMapping(value = "/OrderDetailList")
	public String OrderDetailList(HttpSession session, @RequestParam String order_idx, Model model) {
		System.out.println("상세 주문 내역 화면");
		
		String id = (String)session.getAttribute("sId");
		
		OrderBean order = service.getOrder(id, order_idx);
		
		model.addAttribute("order", order);
		
		return "order/order_detail_list";
	}
	@GetMapping(value = "/OrderPayEnd")
	public String OrderPayEnd() {
		System.out.println("결제 완료 화면");
		
		
		
		return "order/pay_end";
	}

}
