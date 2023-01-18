package com.itwillbs.team1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.team1.svc.NoticeDeleteProService;
import com.itwillbs.team1.vo.ActionForward;

public class NoticeDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
		
		NoticeDeleteProService service = new NoticeDeleteProService();
		
		try {
		boolean isNoticeWriter = service.isNoticeWriter(notice_idx);
		
		if(!isNoticeWriter) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			boolean isDeleteSuccess = service.removeNotice(notice_idx);
			
			if(!isDeleteSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setPath("NoticeList.ad?pageNum=" + request.getParameter("pageNum"));
				forward.setRedirect(true);
			}
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
		return forward;
	}

}
