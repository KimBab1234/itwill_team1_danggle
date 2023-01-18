package com.itwillbs.team1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.itwillbs.team1.svc.QnaWriteProService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.QnaBean;

public class QnaWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String sId = (String)session.getAttribute("sId");
//		System.out.println("pro" + sId);
		
		try {
			String uploadPath = "upload";
			String realPath = request.getServletContext().getRealPath(uploadPath);
//			System.out.println("실제 업로드 경로 : " + realPath);
			int fileSize = 1024*1024*10;
			
			String writerIpAddr = request.getRemoteAddr();
			
			MultipartRequest multi = new MultipartRequest(request, realPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			
			QnaBean qna = new QnaBean();
			
			
			qna.setMember_id(sId);
//			qna.setQna_idx(Integer.parseInt(multi.getParameter("qna_idx")));
			qna.setQna_content(multi.getParameter("qna_content"));
			qna.setQna_file(multi.getOriginalFileName("qna_file"));
			qna.setQna_original_file(multi.getFilesystemName("qna_file"));
			qna.setQna_subject(multi.getParameter("qna_subject"));
			
			
			System.out.println(qna);
			QnaWriteProService service = new QnaWriteProService();
			boolean isWriteSuccess = service.registerQna(qna , sId);
			
			if(!isWriteSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('문의 등록 실패')");
				out.println("history.back()");
				out.println("</script>");
			}else {
				forward = new ActionForward();
				forward.setPath("QnaList.cu");
				forward.setRedirect(true);
			}
	} catch (IOException e) {
		e.printStackTrace();
	}
		
		return forward;
	}

}
