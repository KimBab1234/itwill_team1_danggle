package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReviewDAO;
import svc.ReviewLikeUpdateService;
import vo.ActionForward;

public class ReviewLikeUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = null;
		try {
			
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();

			int review_idx = Integer.parseInt(request.getParameter("review_idx"));
			String member_id = (String)session.getAttribute("sId");
			String review_like_done = request.getParameter("review_like_done");
//			System.out.println("review_like_done:" + review_like_done);
			
			ReviewLikeUpdateService service = new ReviewLikeUpdateService();
			
			boolean isReviewLike = service.isReviewLike(review_idx, member_id, review_like_done);
//			System.out.println(isReviewLike);
				
			forward = new ActionForward();
			if(review_like_done.equals("Y")) {
				review_like_done = "N";
				forward.setPath("ReviewDetail.re?review_idx="+review_idx+"&pageNum="+request.getParameter("pageNum")+"&review_like_done=" + review_like_done);
			}else if(review_like_done.equals("N")) {
				review_like_done = "Y";
				forward.setPath("ReviewDetail.re?review_idx="+review_idx+"&pageNum="+request.getParameter("pageNum")+"&review_like_done=" + review_like_done);
			}
			forward.setRedirect(true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return forward;
	}

}
