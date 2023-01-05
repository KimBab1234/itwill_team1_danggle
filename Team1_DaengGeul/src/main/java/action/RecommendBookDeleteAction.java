package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.RecommendBookDeleteService;
import vo.ActionForward;

public class RecommendBookDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("RecommendBookDeleteAction - 추천 도서 삭제 액션 페이지");
		ActionForward forward = null;
		
		String product_idx = request.getParameter("product_idx");

		RecommendBookDeleteService service = new RecommendBookDeleteService();
		int deleteCount = service.deleteRecommendBook(product_idx);
		
		if(deleteCount > 0) {
			forward = new ActionForward();
			forward.setPath("RecommendBookList.ad");
			forward.setRedirect(true);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('추천 도서 삭제 실패')");
				out.println("history.back()");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return forward;
	}

}
