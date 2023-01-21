package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReplyLikeDeleteService;
import vo.ActionForward;
import vo.Like_reply;

public class ReplyLikeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		try {
		Like_reply like_reply = new Like_reply();
		
		like_reply.setMember_id((String)session.getAttribute("sId"));
		like_reply.setReply_idx(Integer.parseInt(request.getParameter("reply_idx")));
		like_reply.setBoard_idx(Integer.parseInt(request.getParameter("board_idx")));
		
		ReplyLikeDeleteService service = new ReplyLikeDeleteService();
		int likeDeleteSuccess = service.replyLikeDelete(like_reply);
				
		if(likeDeleteSuccess > 0) {
			forward = new ActionForward();
			forward.setPath("CommunityDetail.co?board_idx="+like_reply.getBoard_idx());
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
