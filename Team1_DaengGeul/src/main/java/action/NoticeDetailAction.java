package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.NoticeDetailService;
import vo.ActionForward;
import vo.NoticeBean;

public class NoticeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
		
		NoticeDetailService service = new NoticeDetailService();
		NoticeBean notice = service.getNotice(notice_idx,false);
		
		request.setAttribute("notice", notice);
		
		forward = new ActionForward();
		forward.setPath("notice/qna_notice_view.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
