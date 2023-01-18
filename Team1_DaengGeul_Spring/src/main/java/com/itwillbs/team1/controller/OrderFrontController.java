package com.itwillbs.team1.controller;

import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.itwillbs.team1.svc.ProductService;
import com.itwillbs.team1.svc.ReviewListService;
import com.itwillbs.team1.vo.PageInfo;
import com.itwillbs.team1.vo.ProductBean;
import com.itwillbs.team1.vo.ProductListBean;
import com.itwillbs.team1.vo.ReviewBean;

@Controller
public class OrderFrontController {

	////연결된 클래스의 객체를 필요할때마다 자동으로 객체 생성해서 주입해줌
	@Autowired
	private ProductService service;
	
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
	public String OrderList() {
		System.out.println("주문 내역 화면");
		
		
		
		return "order/order_list";
	}
	@GetMapping(value = "/OrderDetailList")
	public String OrderDetailList() {
		System.out.println("상세 주문 내역 화면");
		
		
		
		return "order/order_detail_list";
	}
	@GetMapping(value = "/OrderPayEnd")
	public String OrderPayEnd() {
		System.out.println("결제 완료 화면");
		
		
		
		return "order/pay_end";
	}

}
