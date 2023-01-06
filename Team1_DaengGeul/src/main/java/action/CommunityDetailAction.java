package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CommunityDetailService;
import svc.CommunityReplyListService;
import vo.ActionForward;
import vo.CommunityBean;
import vo.Reply;

public class CommunityDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;

		int idx = Integer.parseInt(request.getParameter("board_idx"));

		System.out.println("Community board_idx = " + idx);

		CommunityDetailService service = new CommunityDetailService();

		CommunityBean communityBoardDetail = service.communityDetail(idx);

		request.setAttribute("board", communityBoardDetail);

		// ------------------------------------------------------------------------------------
		// ------------------------------ 댓글 목록 리스트 ------------------------------------
		CommunityReplyListService service2 = new CommunityReplyListService();

		ArrayList<Reply> replyList = null;

		replyList = service2.communityReplyList(idx);

		request.setAttribute("replyList", replyList);

		forward = new ActionForward();
		forward.setPath("community/CommunityDetail.jsp?board_idx"+idx);
		forward.setRedirect(false);
		
		return forward;
	}

}
