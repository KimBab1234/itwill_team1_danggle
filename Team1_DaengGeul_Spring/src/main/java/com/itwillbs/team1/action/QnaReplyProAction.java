package com.itwillbs.team1.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.itwillbs.team1.svc.QnaDetailService;
import com.itwillbs.team1.svc.QnaModifyProService;
import com.itwillbs.team1.svc.QnaReplyProService;
import com.itwillbs.team1.svc.QnaWriteProService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.QnaBean;

public class QnaReplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = null;
		
		
		
	
			
			QnaBean qna = new QnaBean();
			String pageNum = request.getParameter("pageNum");
			HttpSession session = request.getSession();
			String sId = (String)session.getAttribute("sId");
			
//			int qna_idx = Integer.parseInt(request.getParameter("qna_idx"));
			
			qna.setQna_idx(Integer.parseInt(request.getParameter("qna_idx")));
			qna.setMember_id(sId);
			qna.setQna_content(request.getParameter("qna_content"));
			qna.setQna_subject(request.getParameter("qna_subject"));
			qna.setQna_re_ref(Integer.parseInt(request.getParameter("qna_re_ref")));
			qna.setQna_re_lev(Integer.parseInt(request.getParameter("qna_re_lev")));
			qna.setQna_re_seq(Integer.parseInt(request.getParameter("qna_re_seq")));
			System.out.println("gd"+request.getParameter("qna_re_ref")); 
			System.out.println(request.getParameter("qna_re_lev")); 
			System.out.println(request.getParameter("qna_re_seq")); 
			QnaReplyProService service = new QnaReplyProService();
			boolean isWriteSuccess = service.registReplyQna(qna);
			
//			request.setAttribute("qna", qna);
			
			
			System.out.println(qna);
			
		
			
		
			try {
				
				if(!isWriteSuccess) { // 수정 권한 없음
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('답글 쓰기 실패!')");
					out.println("history.back()");
					out.println("</script>");
					
					
				}else { // 수정 권한 있음
					forward = new ActionForward();
					forward.setPath("QnaList.cu?pageNum=" + request.getParameter("pageNum"));
					forward.setRedirect(true);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		return forward;
	}

}
