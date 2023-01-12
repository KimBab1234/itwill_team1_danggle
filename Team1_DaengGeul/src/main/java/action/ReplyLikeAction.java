package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.CommunityLikeService;
import svc.ReplyLikeService;
import vo.ActionForward;
import vo.Like_community;
import vo.Like_reply;

public class ReplyLikeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		try {
			Like_reply like = new Like_reply();
			like.setBoard_idx(Integer.parseInt(request.getParameter("board_idx")));
			like.setReply_idx(Integer.parseInt(request.getParameter("reply_idx")));
			like.setMember_id((String)session.getAttribute("sId"));
			
			ReplyLikeService service = new ReplyLikeService();
			boolean likeSuccess = service.likeReply(like);
			
			if(!likeSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("추천 실패!");
				out.println("history.back()");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setPath("CommunityDetail.co?board_idx="+like.getBoard_idx());
				forward.setRedirect(true);
			}
		}  catch (IOException e) {
			System.out.println("action오류");
			e.printStackTrace();
		}
		
		return forward;
	}

}
