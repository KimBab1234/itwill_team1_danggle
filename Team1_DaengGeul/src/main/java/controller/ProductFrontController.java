package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ProductDetailAction;
import action.ProductListAction;
import vo.ActionForward;

@WebServlet("*.go")
public class ProductFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("===========================");
		System.out.println("MemberFrontController");
		
		String command=request.getServletPath();
		ActionForward forward = null;
		Action action=null;
		
		///=======================URL에 따른 Action 분리=======================///
		
		if(command.equals("/ProductDetail.go")) {
			System.out.println("책 or 굿즈 상세페이지");
			action = new ProductDetailAction();
			forward = action.execute(request, response);
		} else if(command.equals("/ProductList.go")) {
			System.out.println("상품 목록");
			action = new ProductListAction();
			forward = action.execute(request, response);
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
