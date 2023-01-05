package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CommonListService;
import vo.ActionForward;
import vo.CommonBean;

public class CommonListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		CommonBean common = new CommonBean();
		
		common.setCommon_subject(request.getParameter("common_subject"));
		common.setCommon_content(request.getParameter("common_content"));
		
		CommonListService service = new CommonListService();
		
		List<CommonBean> commonList = service.getCommonList(common);
		request.setAttribute("commonList", commonList);
		
		forward = new ActionForward();
		forward.setPath("customer/common_list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
