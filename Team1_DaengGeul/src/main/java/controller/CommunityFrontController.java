package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.CommunityDeleteAction;
import action.CommunityDetailAction;
import action.CommunityListAction;
import action.CommunityReplyAction;
import action.CommunityWriteAction;
import action.MemberLoginProAction;
import vo.ActionForward;

@WebServlet("*.cu") 
public class CommunityFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();

		Action action = null;
		ActionForward forward = null;

		// 커뮤니티 글작성 Controller
		if(command.equals("/CommunityWrite0.cu")) {
			System.out.println("회원들의 추천목록 작성");
			forward = new ActionForward();
			forward.setPath("community/CommunityWriteForm0.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/CommunityWrite1.cu")) {
			System.out.println("독후감 작성");
			forward = new ActionForward();
			forward.setPath("community/CommunityWriteForm1.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/CommunityWritePro.cu")) {
			System.out.println("글쓰기 작업");
			action = new CommunityWriteAction();
			forward = action.execute(request, response);
		} else if(command.equals("/Community0.cu")) {
			System.out.println("회원들의 추천목록 페이지");
			action = new CommunityListAction();
			forward = action.execute(request, response);
		} else if(command.equals("/Community1.cu")) {
			System.out.println("독후감 페이지");
			action = new CommunityListAction();
			forward = action.execute(request, response);
		} else if(command.equals("/CommunityDetail.cu")) {
			System.out.println("커뮤니티 디테일 페이지");
			action = new CommunityDetailAction();
			forward = action.execute(request, response);
		} else if(command.equals("/Community_ReplyPro.cu")) {
			System.out.println("커뮤니티 댓글 작성");
			action = new CommunityReplyAction();
			forward = action.execute(request, response);
		} else if(command.equals("/Community_DeletePro.cu")) {
			System.out.println("커뮤니티 글삭제");
			action = new CommunityDeleteAction();
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