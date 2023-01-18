package com.itwillbs.team1.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.team1.svc.CommunityDetailService;
import com.itwillbs.team1.svc.CommunityDuplicationLikeService;
import com.itwillbs.team1.svc.CommunityLikeCountService;
import com.itwillbs.team1.svc.CommunityReplyListService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.CommunityBean;
import com.itwillbs.team1.vo.Like_community;
import com.itwillbs.team1.vo.Like_reply;
import com.itwillbs.team1.vo.Reply;

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
		// ------------------------------- 추천 갯수 ----------------------------------------
		CommunityLikeCountService service2 = new CommunityLikeCountService();
		int likeCount = service2.likeCountService(idx);
		request.setAttribute("likeCount", likeCount);
		
		// ------------------------------------------------------------------------------------
		// ------------------------------- 추천 중복체크 ----------------------------------------
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

		replyList = service4.communityReplyList(idx, sId);
		request.setAttribute("replyList", replyList);

		forward = new ActionForward();
		forward.setPath("community/CommunityDetail.jsp?board_idx"+idx);
		forward.setRedirect(false);
		
		// ------------------------------------------------------------------------------------
		// ------------------------------ 댓글 추천 중복 체크 ------------------------------------

		
		// ------------------------------------------------------------------------------------
		// ------------------------------ 댓글 추천 취소 ------------------------------------
		return forward;
	}

}
