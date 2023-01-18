package com.itwillbs.team1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.team1.vo.ActionForward;

public class MemberLogoutProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		session.removeAttribute("sId");
		
		forward = new ActionForward();
		forward.setPath("./");
		forward.setRedirect(true);

		return forward;
	}

}