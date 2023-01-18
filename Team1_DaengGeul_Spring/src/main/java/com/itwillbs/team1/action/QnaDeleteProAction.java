package com.itwillbs.team1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.team1.svc.QnaDeleteProService;
import com.itwillbs.team1.vo.ActionForward;

public class QnaDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		System.out.println("action 글번호" +request.getParameter("qna_idx"));
		int qna_idx = Integer.parseInt(request.getParameter("qna_idx"));
		
		
		HttpSession session = request.getSession();
		String sId = (String)session.getAttribute("sId");
		System.out.println(qna_idx + "," + sId);
		QnaDeleteProService service = new QnaDeleteProService();
		try {
		boolean isQnaWriter = service.isQnaWriter(qna_idx,sId);
		 
		if(!isQnaWriter) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			boolean isDeleteSuccess = service.removeQna(qna_idx);
			
			if(!isDeleteSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			}else {
				forward = new ActionForward();
				forward.setPath("QnaList.cu?pageNum=" + 1);
				forward.setRedirect(true);
			}
			
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return forward;
	}

}
