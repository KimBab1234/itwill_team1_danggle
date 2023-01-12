package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.QnaDetailService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String sId = (String)session.getAttribute("sId");
		
		int qna_idx = Integer.parseInt(request.getParameter("qna_idx"));
//		System.out.println("글번호:" + request.getParameter("qna_idx"));
		QnaDetailService service = new QnaDetailService();
		QnaBean qna = service.getQna(qna_idx);
		
		request.setAttribute("qna", qna);
		forward = new ActionForward();
		forward.setPath("customer/qna_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
