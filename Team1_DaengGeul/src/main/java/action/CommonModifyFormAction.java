package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CommonDeleteProService;
import svc.CommonDetailService;
import vo.ActionForward;
import vo.CommonBean;

public class CommonModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		int common_idx = Integer.parseInt(request.getParameter("common_idx"));
		String common_subject = request.getParameter("common_subject");
		System.out.println("수정 폼 글 번호 : " + common_idx);
		
		CommonDetailService service = new CommonDetailService();
		CommonBean common = service.getCommon(common_idx);
		
		request.setAttribute("common", common);
		
		forward = new ActionForward();
		forward.setPath("customer/common_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
