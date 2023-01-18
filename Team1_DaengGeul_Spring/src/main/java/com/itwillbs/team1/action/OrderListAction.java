package com.itwillbs.team1.action;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.team1.svc.OrderService2;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.OrderBean;

public class OrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===========================");
		System.out.println("OrderListAction 진입");
		ActionForward forward=new ActionForward();

		String id = (String)request.getSession().getAttribute("sId");

		////관리자일 경우
		if(id.equals("admin")) {

			//파라미터로 들어온 아이디 있는지 조회
			id=request.getParameter("id");
			
			if(id==null) {
				//없으면 session에 저장되어있으니 가져오기
				id = (String)request.getSession().getAttribute("customerId");
			} else {
				///있으면 session에 없을테니 저장해주기
				request.getSession().setAttribute("customerId",id);
			}

			
		}

		String period = request.getParameter("period");

		if(period==null) {
			period="1 WEEK";
		}

		OrderService2 service = new OrderService2();

		ArrayList<OrderBean> orderList= new ArrayList<>();
		orderList = service.getOrderList(id, period);

		request.setAttribute("orderList", orderList);

		forward.setPath("order/order_list.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
