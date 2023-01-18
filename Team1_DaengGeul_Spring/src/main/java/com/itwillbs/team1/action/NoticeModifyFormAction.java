package com.itwillbs.team1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.team1.svc.NoticeDetailService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.NoticeBean;

public class NoticeModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = null;
		
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
		try {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("sId") == null || !session.getAttribute("sId").equals("admin")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근입니다!')");
			out.println("history.back()");
			
			out.println("</script>");
		}else {
		
		NoticeDetailService service = new NoticeDetailService();
		NoticeBean notice = service.getNotice(notice_idx, false);
		
		request.setAttribute("notice", notice);
		
		forward = new ActionForward();
		forward.setPath("notice/qna_notice_modify.jsp");
		forward.setRedirect(false);
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return forward;
	}

}
