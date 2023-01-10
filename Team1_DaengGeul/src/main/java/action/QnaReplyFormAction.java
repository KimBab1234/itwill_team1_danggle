package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.QnaDetailService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		int qna_idx = Integer.parseInt(request.getParameter("qna_idx"));
		String pageNum = request.getParameter("pageNum");
		HttpSession session = request.getSession();
		String sId = (String)session.getAttribute("sId");
		
		
		QnaDetailService service = new QnaDetailService();
		QnaBean qna = service.getQna(qna_idx);
		request.setAttribute("sId", sId);
		request.setAttribute("qna", qna);
		request.setAttribute("pageNum", pageNum);
		
		forward = new ActionForward();
		forward.setPath("customer/qna_reply.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
