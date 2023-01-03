package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class RecommendBookAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("RecommendBookAction(추천 도서 등록 액션 페이지)");
		ActionForward forward = null;
		
		forward = new ActionForward();
		forward.setPath("product/recommend_book.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
