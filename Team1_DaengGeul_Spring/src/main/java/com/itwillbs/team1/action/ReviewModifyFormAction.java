package com.itwillbs.team1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.team1.svc.ReviewDetailService;
import com.itwillbs.team1.svc.ReviewWriteFormService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.ReviewBean;

public class ReviewModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		
		// 상세정보 조회에 필요한 글번호 가져오기
		int review_idx = Integer.parseInt(request.getParameter("review_idx"));
		String member_id = (String)session.getAttribute("sId");
		String product_idx = request.getParameter("product_idx");
//		System.out.println("review_idx = " + review_idx);
		
		ReviewDetailService service = new ReviewDetailService();
		ReviewBean review =  service.getReview(review_idx,false, member_id);
		ReviewWriteFormService service1 = new ReviewWriteFormService();
		ReviewBean review1 = service1.getReview(product_idx);
//		System.out.println(review);
		
		// 뷰페이지로 데이터 전달을 위해 requrest 객체에 전달
		request.setAttribute("review", review);
		request.setAttribute("review1", review1);
		
		// ActionForward 객체를 통해 reveiw_view.jsp 페이지 포워딩 설정
		// URL 유지 및 request 객체 유지를 위해 Dispatch 방식 포워딩
		
		forward = new ActionForward();
		forward.setPath("review/review_modify.jsp");
		forward.setRedirect(false);
		
		
		
		
		return forward;
	}

}
