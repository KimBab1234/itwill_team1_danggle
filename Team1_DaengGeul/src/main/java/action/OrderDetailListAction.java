package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.OrderService;
import vo.ActionForward;
import vo.OrderBean;

public class OrderDetailListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===========================");
		System.out.println("OrderListAction 진입");
		ActionForward forward=new ActionForward();
	
		String id = (String)request.getSession().getAttribute("sId");
		String order_idx = request.getParameter("order_idx");
		
		OrderService service = new OrderService();
		
		OrderBean order = service.getOrder(id, order_idx);
		
		request.setAttribute("order", order);
		
		forward.setPath("order/order_detail_list.jsp?order_idx="+order_idx);
		forward.setRedirect(false);
		
		return forward;
	}

}
