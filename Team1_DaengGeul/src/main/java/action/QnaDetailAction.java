package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QnaDetailService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		int qna_idx = Integer.parseInt(request.getParameter("qna_idx"));
		
		QnaDetailService service = new QnaDetailService();
		QnaBean qna = service.getQna(qna_idx);
		
		request.setAttribute("qna", qna);
		
		forward = new ActionForward();
		forward.setPath("customer/qna_view.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
