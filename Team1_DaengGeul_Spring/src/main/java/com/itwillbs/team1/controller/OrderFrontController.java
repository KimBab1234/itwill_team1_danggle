package com.itwillbs.team1.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.team1.svc.OrderService;
import com.itwillbs.team1.vo.OrderBean;
import com.itwillbs.team1.vo.OrderProdBean;

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

	@PostMapping(value = "/OrderPayForm")
	public String OrderPayForm(int totalPay, Model model, HttpSession session) {
		System.out.println("결제 화면");

		model.addAttribute("totalPay",totalPay);

		//		//멤버 테이블에서 적립금, 쿠폰 정보 가져오기
		//		String id = (String)session.getAttribute("sId");
		//		MemberInfoService moService = new MemberInfoService();
		//		MemberBean member = moService.getMemberInfo(id);
		//		request.setAttribute("member", member);

		return "order/pay";
	}

	@PostMapping(value = "/OrderPayPro")
	public String OrderPayPro(@ModelAttribute OrderBean order,HttpSession session, @RequestParam(value = "prodname") List<String> prodname, String cartJson, Model model) throws Exception {
		System.out.println("결제 진행 화면");

		JSONParser jsonParser = new JSONParser();
		JSONArray cart;

		try {
			cart = (JSONArray)jsonParser.parse(cartJson);

			////orderInfo에 들어갈 데이터 (1회만 저장하면됨)
			order.setMember_id((String)session.getAttribute("sId"));
			order.setOrder_idx(order.getMember_id()+order.getOrder_idx());
			order.setOrder_address(order.getPostcode()+" "+order.getRoadAddress()+" "+order.getAddressDetail());
			order.setOrder_phone(order.getOrder_phone1()+"-"+order.getOrder_phone2()+"-"+order.getOrder_phone3());
			System.out.println("order 정보 : "+order.toString());

			////orderProd에 들어갈 데이터 (상품별로 배열에 저장 필요)
			ArrayList<OrderProdBean> orderProdList = new ArrayList<OrderProdBean>();

			for(int i=0; i<cart.size(); i++) {

				JSONArray prodIdx = (JSONArray)cart.get(i);
				String order_prod = (String)prodIdx.get(0);

				OrderProdBean orderProd = new OrderProdBean();
				orderProd.setIdx(order_prod.substring(0, order_prod.indexOf("_opt_")));
				JSONArray prod = (JSONArray)prodIdx.get(1);

				JSONArray prodInfo = (JSONArray)prod.get(0);
				orderProd.setOpt((String)prodInfo.get(1)==null?"-":(String)prodInfo.get(1));
				prodInfo = (JSONArray)prod.get(4);
				orderProd.setCnt(Integer.parseInt(String.valueOf(prodInfo.get(1))));
				prodInfo = (JSONArray)prod.get(1);
				orderProd.setPrice(Integer.parseInt(String.valueOf(prodInfo.get(1))));

				orderProd.setName(prodname.get(i));
				orderProd.setOrder_idx(order.getOrder_idx());
				System.out.println(orderProd);

				orderProdList.add(orderProd);
			}

			order.setOrder_prod_list(orderProdList);

			///주문내역 저장하러가기
			Boolean isSuccessInsert = service.setProductList(order);

			if(isSuccessInsert) {
				return "redirect:/OrderPayEnd";
			} else {
				model.addAttribute("msg","주문 실패!");
				return "fail_back";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "fail_back";
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
