package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CommunityListService;
import vo.ActionForward;
import vo.CommunityBean;

public class CommunityListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;

		int type = Integer.parseInt(request.getParameter("board_type"));

		CommunityListService service = new CommunityListService();
		List<CommunityBean> community = service.getList(type);

		request.setAttribute("Board", community);
		
		forward = new ActionForward();
		
		if(type == 0) {
			forward.setPath("community/Community_List0.jsp");
		} else {
			forward.setPath("community/Community_List1.jsp");
		}

		return forward;
	}

}
