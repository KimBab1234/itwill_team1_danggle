package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CommunityReplyDeleteService;
import vo.ActionForward;

public class CommunityReplyDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;

		int board_idx = Integer.parseInt(request.getParameter("board_idx"));
		int reply_idx = Integer.parseInt(request.getParameter("reply_idx"));

		CommunityReplyDeleteService service = new CommunityReplyDeleteService();

		boolean deleteCount = service.replyDeleteCount(board_idx,reply_idx);
		
			try {
				if(!deleteCount) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('댓글삭제 실패!')");
					out.println("history.back()");
					out.println("</script>");
				} else {
					forward = new ActionForward();
					forward.setPath("CommunityDetail.co?board_idx="+board_idx);
					forward.setRedirect(true);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return forward;
	}

}