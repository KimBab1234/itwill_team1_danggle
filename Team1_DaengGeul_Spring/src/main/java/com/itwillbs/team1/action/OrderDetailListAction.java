package com.itwillbs.team1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.team1.svc.OrderService2;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.OrderBean;

public class OrderDetailListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===========================");
		System.out.println("OrderListAction 진입");
		ActionForward forward=new ActionForward();
	
		String id = (String)request.getSession().getAttribute("sId");
		String order_idx = request.getParameter("order_idx");
		
		OrderService2 service = new OrderService2();
		
		OrderBean order = service.getOrder(id, order_idx);
		
		request.setAttribute("order", order);
		
		forward.setPath("order/order_detail_list.jsp?order_idx="+order_idx);
		forward.setRedirect(false);
		
		return forward;
	}

}
