package com.itwillbs.team1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.team1.svc.CommunityLikeDeleteService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.Like_community;

public class CommunityLikeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		int board_idx = Integer.parseInt(request.getParameter("board_idx"));
		try {
			Like_community like = new Like_community();
			like.setBoard_idx(board_idx);
			like.setMember_id((String)session.getAttribute("sId"));
			
			CommunityLikeDeleteService service = new CommunityLikeDeleteService();
			int likeDeleteSuccess = service.likeDeleteCommunity(like, board_idx);
			
			if(likeDeleteSuccess > 0) {
				forward = new ActionForward();
				forward.setPath("CommunityDetail.co?board_idx="+like.getBoard_idx());
				forward.setRedirect(true);
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("좋아요취소 실패!");
				out.println("history.back()");
				out.println("</script>");
			}
		}  catch (IOException e) {
			System.out.println("likeDeleteAction오류");
			e.printStackTrace();
		}
		
		return forward;
	}
}
