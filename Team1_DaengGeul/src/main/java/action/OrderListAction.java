package action;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.OrderService;
import vo.ActionForward;
import vo.OrderBean;

public class OrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===========================");
		System.out.println("OrderListAction 진입");
		ActionForward forward=new ActionForward();
	
		String id = (String)request.getSession().getAttribute("sId");
		
		String period = request.getParameter("period");
		
		if(period==null) {
			period="1 WEEK";
		}
		
		OrderService service = new OrderService();
		
		ArrayList<OrderBean> orderList= new ArrayList<>();
		orderList = service.getOrderList(id, period);

		request.setAttribute("orderList", orderList);
		
		forward.setPath("order/order_list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
