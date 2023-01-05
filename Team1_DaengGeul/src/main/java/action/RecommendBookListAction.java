package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.RecommendBookListService;
import vo.ActionForward;
import vo.ProductBean;

public class RecommendBookListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		RecommendBookListService service = new RecommendBookListService();
		ArrayList<ProductBean> recoList = null;
		recoList = service.selectRecommendBook();
		
		request.setAttribute("recoList", recoList);
		
		forward = new ActionForward();
		forward.setPath("product/recommend_bookList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
