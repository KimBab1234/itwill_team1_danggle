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
import action.CommunityLikeBoardAction;
import action.CommunityLikeDeleteAction;
import action.CommunityListAction;
import action.CommunityModifyAction;
import action.CommunityReplyAction;
import action.CommunityReplyDeleteAction;
import action.CommunityWriteAction;
import action.ReplyLikeAction;
import vo.ActionForward;

@WebServlet("*.co") 
public class CommunityFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();

		Action action = null;
		ActionForward forward = null;

		// 커뮤니티 글작성 Controller
		if(command.equals("/CommunityWrite0.co")) {
			System.out.println("회원들의 추천목록 작성");
			forward = new ActionForward();
			forward.setPath("community/CommunityWriteForm0.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/CommunityWrite1.co")) {
			System.out.println("독후감 작성");
			forward = new ActionForward();
			forward.setPath("community/CommunityWriteForm1.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/CommunityWritePro.co")) {
			System.out.println("글쓰기 작업");
			action = new CommunityWriteAction();
			forward = action.execute(request, response);
		} else if(command.equals("/Community0.co")) {
			System.out.println("회원들의 추천목록 페이지");
			action = new CommunityListAction();
			forward = action.execute(request, response);
		} else if(command.equals("/Community1.co")) {
			System.out.println("독후감 페이지");
			action = new CommunityListAction();
			forward = action.execute(request, response);
		} else if(command.equals("/CommunityDetail.co")) {
			System.out.println("커뮤니티 디테일 페이지");
			action = new CommunityDetailAction();
			forward = action.execute(request, response);
		} else if(command.equals("/Community_ReplyPro.co")) {
			System.out.println("커뮤니티 댓글 작성");
			action = new CommunityReplyAction();
			forward = action.execute(request, response);
		} else if(command.equals("/Community_DeletePro.co")) {
			System.out.println("커뮤니티 글삭제");
			action = new CommunityDeleteAction();
			forward = action.execute(request, response);
		} else if(command.equals("/CommunityReplyDeletePro.co")) {
			System.out.println("커뮤니티 댓글삭제");
			action = new CommunityReplyDeleteAction();
			forward = action.execute(request, response);
		} else if(command.equals("/CommunityModifyPro.co")) {
			System.out.println("커뮤니티 글수정프로");
			action = new CommunityModifyAction();
			forward = action.execute(request, response);
		} else if(command.equals("/CommunityModify.co")) {
			System.out.println("커뮤니티 글수정");
			forward = new ActionForward();
			forward.setPath("community/CommunityModify.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/CommuniteLikeBoard.co")) {
			System.out.println("커뮤니티 추천");
			action = new CommunityLikeBoardAction();
			forward = action.execute(request, response);
		} else if(command.equals("/CommunityLikeDelete.co")) {
			System.out.println("커뮤니티 추천취소");
			action = new CommunityLikeDeleteAction();
			forward = action.execute(request, response);
		} else if(command.equals("/ReplyLike.co")) {
			System.out.println("댓글 추천");
			action = new ReplyLikeAction();
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