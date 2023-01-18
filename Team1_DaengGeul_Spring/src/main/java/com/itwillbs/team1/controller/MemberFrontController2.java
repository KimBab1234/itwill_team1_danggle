package com.itwillbs.team1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.team1.action.Action;
import com.itwillbs.team1.action.MemberCheckCertProAction;
import com.itwillbs.team1.action.MemberCheckEmailAction;
import com.itwillbs.team1.action.MemberCheckIdAction;
import com.itwillbs.team1.action.MemberDeleteProAction;
import com.itwillbs.team1.action.MemberInfoAction;
import com.itwillbs.team1.action.MemberJoinProAction;
import com.itwillbs.team1.action.MemberJoinResultAction;
import com.itwillbs.team1.action.MemberListAction;
import com.itwillbs.team1.action.MemberLoginProAction;
import com.itwillbs.team1.action.MemberLogoutProAction;
import com.itwillbs.team1.action.MemberSearchIdAction;
import com.itwillbs.team1.action.MemberSearchPasswdAction;
import com.itwillbs.team1.action.MemberSendCertProAction;
import com.itwillbs.team1.action.MemberUpdateProAction;
import com.itwillbs.team1.vo.ActionForward;

@WebServlet("*.me") 
public class MemberFrontController2 extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();
		
		Action action = null;
		ActionForward forward = null;
		
		
		// 회원가입 Controller
		if(command.equals("/MemberJoinForm.me")) {
			forward = new ActionForward();
			forward.setPath("member/member_join_form.jsp");
			forward.setRedirect(false);
		// 중복 아이디 확인
		} else if(command.equals("/MemberCheckId.me")) {
			action = new MemberCheckIdAction();
			forward = action.execute(request, response);
		// 중복 이메일 확인
		} else if(command.equals("/MemberCheckEmail.me")) {
			action = new MemberCheckEmailAction();
			forward = action.execute(request, response);
		} else if(command.equals("/MemberJoinPro.me")) {
			System.out.println("회원가입!");
			action = new MemberJoinProAction();
			forward = action.execute(request, response);
		
			
		// 로그인 Controller
		} else if(command.equals("/MemberLoginForm.me")) {
			forward = new ActionForward();
			forward.setPath("member/member_login_form.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/MemberLoginPro.me")) {
			System.out.println("로그인!");
			action = new MemberLoginProAction();
			forward = action.execute(request, response);
		
			
		// 로그아웃 Controller
		} else if(command.equals("/MemberLogoutPro.me")) {
			System.out.println("로그아웃!");
			action = new MemberLogoutProAction();
			forward = action.execute(request, response);
		
			
		// 일반사용자(회원정보)/관리자(회원목록) Controller
		} else if(command.equals("/MemberInfo.me")) {
			System.out.println("마이페이지!");
			action = new MemberInfoAction();
			forward = action.execute(request, response);
		} else if(command.equals("/MemberList.me")) {
			System.out.println("관리자페이지!");
			action = new MemberListAction();
			forward = action.execute(request, response);
		
			
		// 회원 수정 Controller
		} else if(command.equals("/MemberUpdatePro.me")) {
			System.out.println("마이페이지!");
			action = new MemberUpdateProAction();
			forward = action.execute(request, response);
			
		
		// 회원탈퇴 Controller
		} else if(command.equals("/MemberDeleteForm.me")) {
			forward = new ActionForward();
			forward.setPath("member/member_delete.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/MemberDeletePro.me")) {
			System.out.println("회원탈퇴!");
			action = new MemberDeleteProAction();
			forward = action.execute(request, response);
		
			
		// 회원가입/비밀번호 찾기 인증메일 전송
		} else if(command.equals("/MemberSendCertPro.me")) {
			System.out.println("인증메일전송!");
			action = new MemberSendCertProAction();
			forward = action.execute(request, response);
		} else if(command.equals("/MemberCheckCertPro.me")) {
			System.out.println("인증코드확인!");
			action = new MemberCheckCertProAction();
			forward = action.execute(request, response);
			
			
		// 회원 아이디/비밀번호 찾기 Controller
		} else if(command.equals("/MemberInfoSearchForm.me")) {
			forward = new ActionForward();
			forward.setPath("member/member_info_search_form.jsp");
			forward.setRedirect(false);
		// 회원 아이디 찾기
		} else if(command.equals("/MemberSearchId.me")) {
			System.out.println("아이디 찾기!");
			action = new MemberSearchIdAction();
			forward = action.execute(request, response);
		// 회원 비밀번호 찾기
		} else if(command.equals("/MemberSearchPasswd.me")) {
			System.out.println("비밀번호 찾기!");
			action = new MemberSearchPasswdAction();
			forward = action.execute(request, response);
		} else if(command.equals("/MemberJoinResult.me")) {
			System.out.println("회원가입 완료!");
			action = new MemberJoinResultAction();
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