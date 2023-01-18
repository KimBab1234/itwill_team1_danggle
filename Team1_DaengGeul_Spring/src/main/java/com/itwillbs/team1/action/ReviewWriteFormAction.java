package com.itwillbs.team1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.team1.svc.ReviewWriteFormService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.ReviewBean;

public class ReviewWriteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("ReviewWriteFormAction");
		
		ActionForward forward = null;
		
		String product_idx = request.getParameter("product_idx");
		System.out.println("product_idx" + product_idx);
		
		ReviewWriteFormService service = new ReviewWriteFormService();
		
		ReviewBean review = service.getReview(product_idx);
		
		request.setAttribute("review", review);
		
		forward = new ActionForward();
		forward.setPath("review/review_write.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
