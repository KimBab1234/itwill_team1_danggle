package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.CommunityDetailService;
import svc.CommunityDuplicationLikeService;
import svc.CommunityLikeCountService;
import svc.CommunityReplyListService;
import vo.ActionForward;
import vo.CommunityBean;
import vo.Like_community;
import vo.Reply;

public class CommunityDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		String sId = (String)session.getAttribute("sId");
		int idx = Integer.parseInt(request.getParameter("board_idx"));

		CommunityDetailService service = new CommunityDetailService();
		CommunityBean communityBoardDetail = service.communityDetail(idx,true);

		request.setAttribute("board", communityBoardDetail);

		// ------------------------------------------------------------------------------------
		// ------------------------------- 좋아요 갯수 ----------------------------------------
		CommunityLikeCountService service2 = new CommunityLikeCountService();
		int likeCount = service2.likeCountService(idx);
		request.setAttribute("likeCount", likeCount);
		
		// ------------------------------------------------------------------------------------
		// ------------------------------- 좋아요 중복체크 ----------------------------------------
		Like_community like = new Like_community();
		like.setBoard_idx(idx);
		like.setMember_id(sId);
		
		CommunityDuplicationLikeService service3 = new CommunityDuplicationLikeService();
		boolean duplicateLike = service3.duplicateLike(like);
		request.setAttribute("duplicateLike", duplicateLike);
		
		// ------------------------------------------------------------------------------------
		// ------------------------------ 댓글 목록 리스트 ------------------------------------
		CommunityReplyListService service4 = new CommunityReplyListService();
		ArrayList<Reply> replyList = null;

		replyList = service4.communityReplyList(idx);
		request.setAttribute("replyList", replyList);

		forward = new ActionForward();
		forward.setPath("community/CommunityDetail.jsp?board_idx"+idx);
		forward.setRedirect(false);
		
		return forward;
	}

}
