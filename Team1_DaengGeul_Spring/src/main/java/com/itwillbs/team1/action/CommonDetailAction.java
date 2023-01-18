package com.itwillbs.team1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.team1.svc.CommonDetailService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.CommonBean;

public class CommonDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		int common_idx = Integer.parseInt(request.getParameter("common_idx"));
		String common_subject = request.getParameter("common_subject");
		String common_content = request.getParameter("common_content");
		
		System.out.println("디테일 글 번호 : " + common_idx);
		
		CommonDetailService service = new CommonDetailService();
		CommonBean common = service.getCommon(common_idx);
		
		request.setAttribute("common", common);
		
		forward = new ActionForward();
		forward.setPath("customer/common_view.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
