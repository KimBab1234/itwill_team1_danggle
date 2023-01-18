package com.itwillbs.team1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.team1.action.Action;
import com.itwillbs.team1.action.WishDeleteProAction;
import com.itwillbs.team1.action.WishProAction;
import com.itwillbs.team1.action.WishlistAction;
import com.itwillbs.team1.vo.ActionForward;

@WebServlet("*.ws")
public class WishFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();

		Action action = null;
		ActionForward forward = null;
		
		// 찜등록 후, 찜등록 개수 증가
		if(command.equals("/WishPro.ws")) {
			System.out.println("찜하기!");
			action = new WishProAction();
			forward = action.execute(request, response);
		
		// 찜목록
		} else if(command.equals("/Wishlist.ws")) {
			System.out.println("찜목록");
			action = new WishlistAction();
			forward = action.execute(request, response);
		
		// 찜상품 삭제 및 개수 감소
		} else if(command.equals("/WishDeletePro.ws")) {
			System.out.println("찜상품 삭제");
			action = new WishDeleteProAction();
			forward = action.execute(request, response);
		}
		
		if(forward != null) {
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