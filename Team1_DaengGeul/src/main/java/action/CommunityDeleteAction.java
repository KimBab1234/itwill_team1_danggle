package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;
import vo.CommunityBean;

public class CommunityDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null; 
		
		int board_idx = Integer.parseInt(request.getParameter("board_idx"));
		
		return forward;
	}

}
