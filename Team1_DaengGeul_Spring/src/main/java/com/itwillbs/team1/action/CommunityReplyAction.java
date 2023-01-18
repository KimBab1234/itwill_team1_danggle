package com.itwillbs.team1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.team1.svc.CommunityReplyWriteService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.Reply;

public class CommunityReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		int idx = Integer.parseInt(request.getParameter("board_idx"));
	
		Reply reply = new Reply();
		reply.setBoard_idx(Integer.parseInt(request.getParameter("board_idx")));
		reply.setBoard_type(Integer.parseInt(request.getParameter("board_type")));
		reply.setReply_content(request.getParameter("reply_content"));
		reply.setMember_id(request.getParameter("member_id"));
		
		
		CommunityReplyWriteService service = new CommunityReplyWriteService();
		
		boolean successReply = service.registReply(reply);
		
		try {
			if(successReply) {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("CommunityDetail.co?board_idx="+idx);
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('댓글작성 실패!')");
				out.println("history back()");
				out.println("</script>");
			}
		} catch (IOException e) {
			System.out.println("댓글달기오류!");
			e.printStackTrace();
		}
		return forward;
	}

}
