package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.OrderListAction;
import action.OrderPayFormAction;
import action.OrderDetailListAction;
import action.OrderPayProAction;
import vo.ActionForward;

@WebServlet("*.or")
public class OrderFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("===========================");
		System.out.println("OrderFrontController");
		
		String command=request.getServletPath();
		ActionForward forward = null;
		Action action=null;
		
		///=======================URL에 따른 Action 분리=======================///
		
		if(command.equals("/CartListForm.or")) {
			System.out.println("장바구니화면");
			forward = new ActionForward();
			forward.setPath("order/cart_form.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/OrderPayForm.or")) {
			System.out.println("결제 화면");
			action = new OrderPayFormAction();
			forward = action.execute(request, response);
		} else if(command.equals("/OrderPayPro.or")) {
			System.out.println("결제 진행 화면");
			action = new OrderPayProAction();
			forward = action.execute(request, response);
		} else if(command.equals("/OrderList.or")) {
			System.out.println("주문 내역 화면");
			action = new OrderListAction();
			forward = action.execute(request, response);
		}else if(command.equals("/OrderDetailList.or")) {
			System.out.println("상세 주문 내역 화면");
			action = new OrderDetailListAction();
			forward = action.execute(request, response);
		} else if(command.equals("/OrderPayEnd.or")) {
			System.out.println("결제 완료 화면");
			forward = new ActionForward();
			forward.setPath("order/pay_end.jsp");
			forward.setRedirect(false);
		} 
				
		///=======================페이지 이동 구문=======================///
		
		if(forward!=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	}
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
