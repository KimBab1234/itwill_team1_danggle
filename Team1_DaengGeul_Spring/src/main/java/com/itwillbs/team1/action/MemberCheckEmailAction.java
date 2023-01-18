package com.itwillbs.team1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.team1.svc.MemberCheckEmailService;
import com.itwillbs.team1.vo.ActionForward;

public class MemberCheckEmailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String email = request.getParameter("email1")+ "@" + request.getParameter("email2");
		
		MemberCheckEmailService service = new MemberCheckEmailService();
		boolean isExistEmail = service.checkEmail(email);
		
		// 중복 이메일 존재
		if(isExistEmail) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("true");
			} catch (IOException e) {
				e.printStackTrace();
			}
		// 중복 이메일 미존재
		} else {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("false");
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		return forward;
	}

}
