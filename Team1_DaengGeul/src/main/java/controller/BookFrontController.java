package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ProductDeleteAction;
import action.ProductEditAction;
import action.ProductEditListAction;
import action.ProductEditProAction;
import action.ProductRegiProAction;
import action.RecommendBookAction;
import vo.ActionForward;


@WebServlet("*.ad")
public class BookFrontController extends HttpServlet {
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BookFrontController");
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getServletPath();
		ActionForward forward= null;
		Action action = null;
		
		if(command.equals("/ProductRegiForm.ad")) {
			System.out.println("상품 등록 페이지");
			
			forward = new ActionForward();
			forward.setPath("product/product_registration_form.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/ProductRegiPro.ad")){
			System.out.println("상품 등록 처리 - Pro");
			
			action = new ProductRegiProAction();
			forward = action.execute(request, response);
			
		}else if(command.equals("/Goods.ad")){
			System.out.println("굿즈 목록 페이지"); // 확인용 페이지
			
			forward = new ActionForward();
			forward.setPath("product/goods_regi.jsp");
			
		}else if(command.equals("/Book.ad")){
			System.out.println("책 목록 페이지"); // 확인용 페이지
			
			forward = new ActionForward();
			forward.setPath("product/book_regi.jsp");
			
		}else if(command.equals("/ProductList.ad")){
			System.out.println("상품 목록 페이지");
			
			action = new ProductEditListAction();
			forward = action.execute(request, response);
			
		}else if(command.equals("/ProductEditForm.ad")){
			System.out.println("상품 수정 페이지");
			
			action = new ProductEditAction();
			forward = action.execute(request, response);
			
		}else if(command.equals("/ProductEditPro.ad")) {
			System.out.println("상품 수정 처리 - Pro");
		
			action = new ProductEditProAction();
			forward = action.execute(request, response);
			
		} else if(command.equals("/ProductDelete.ad")){
			System.out.println("상품 삭제");
			
			action = new ProductDeleteAction();
			forward = action.execute(request, response);
			
		}else if(command.equals("/RecommendBook.ad")) {
			System.out.println("추천 도서 등록");
			action = new RecommendBookAction();
			forward = action.execute(request, response);
		}
		
		
		
		
		if(forward != null) {
			if(forward.isRedirect() ) {
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