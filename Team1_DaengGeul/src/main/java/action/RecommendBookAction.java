package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.RecommendBookService;
import vo.ActionForward;
import vo.ProductBean;

public class RecommendBookAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("RecommendBookAction(추천 도서 등록 액션 페이지)");
		ActionForward forward = null;
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			String[] idx = request.getParameterValues("recoCheck");
			ProductBean book = new ProductBean();
			
			book.setReco_idx(idx);
			
			RecommendBookService service = new RecommendBookService();
			int insertCount = service.recommendBook(book);
			
			if(insertCount > 0) { // 등록 성공!
				
				out.println("<script>");
				out.println("alert('추천 도서에 등록되었습니다')");
				out.println("location.href='ProductList.ad'");
				out.println("</script>");
				
				
			}else { // 등록 실패!
				out.println("<script>");
				out.println("alert('이미 등록된 도서입니다')");
				out.println("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
