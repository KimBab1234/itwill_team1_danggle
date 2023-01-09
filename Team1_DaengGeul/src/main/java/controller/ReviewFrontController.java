package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ReviewDeleteProAction;
import action.ReviewDetailAction;
import action.ReviewLikeUpdateAction;
import action.ReviewListAction;
import action.ReviewModifyFormAction;
import action.ReviewModifyProAction;
import action.ReviewWriteFormAction;
import action.ReviewWriteProAction;
import vo.ActionForward;

@WebServlet("*.re")
public class ReviewFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ReviewFrontController");
		
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		// 공통으로 사용할 변수 선언
		Action action = null;
		ActionForward forward = null;
		//===================================================================
		
		if(command.equals("/ReviewWriteForm.re")) {
			System.out.println("리뷰쓰기 폼");
			action= new ReviewWriteFormAction();
			forward = action.execute(request, response);	
		
		}else if(command.equals("/ReviewWritePro.re")) {
			System.out.println("리뷰쓰기 작업");
			action= new ReviewWriteProAction();
			forward = action.execute(request, response);
			
		}else if(command.equals("/ReviewList.re")) {
			System.out.println("리뷰 목록 작업");
			action = new ReviewListAction();
			forward = action.execute(request, response);
			
		}else if(command.equals("/ReviewDetail.re")) {
			System.out.println("리뷰 디테일 작업");
			action = new ReviewDetailAction();
			forward = action.execute(request, response);
			
		}else if(command.equals("/ReviewDeleteForm.re")) {
			System.out.println("리뷰 삭제 폼 작업");
			forward = new ActionForward();
			forward.setPath("review/review_delete.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/ReviewDeletePro.re")) {
			System.out.println("리뷰 삭제 프로 작업");
			action = new ReviewDeleteProAction();
			forward = action.execute(request, response);
			
		} else if(command.equals("/ReviewModifyForm.re")) {
			System.out.println("리뷰 수정 폼 작업");
			action = new ReviewModifyFormAction();
			forward = action.execute(request, response);
			
		} else if(command.equals("/ReviewModifyPro.re")) {
			System.out.println("리뷰 수정 프로 작업");
			action = new ReviewModifyProAction();
			forward = action.execute(request, response);
			
		} else if(command.equals("/ReviewLikeUpdate.re")) {
			System.out.println("리뷰 좋아요 작업");
			action = new ReviewLikeUpdateAction();
			forward = action.execute(request, response);
		}	
		
		//=================================================================================================
		//
		// 1. ActionForward 객체가 null이 아닐 경우
		if(forward != null) {
			// 2.
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
		System.out.println("doProcess() 메서드 끝");
		
	} // doProcess() 메서드 끝(응답데이터 전송)
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
