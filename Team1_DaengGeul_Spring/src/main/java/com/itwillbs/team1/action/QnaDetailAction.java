package com.itwillbs.team1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.team1.svc.QnaDetailService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.QnaBean;

public class QnaDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		int qna_idx = Integer.parseInt(request.getParameter("qna_idx"));
		HttpSession session = request.getSession();
		String sId = (String)session.getAttribute("sId");
		
		
		
		QnaDetailService service = new QnaDetailService();
		QnaBean qna = service.getQna(qna_idx);
		
		request.setAttribute("qna", qna);
		
		forward = new ActionForward();
		forward.setPath("customer/qna_view.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
