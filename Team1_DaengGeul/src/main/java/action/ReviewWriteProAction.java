package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewWriteProService;
import vo.ActionForward;
import vo.ReviewBean;

public class ReviewWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ReviewWriteProAction");
		
		ActionForward forward = null;
		
		try {
			// 전달받은 파라미터 데이터를 reviewBean 클래스 인스턴스 생성 후 저장
			ReviewBean review = new ReviewBean();
			HttpSession session = request.getSession();
			
			request.setCharacterEncoding("UTF-8");
			review.setProduct_idx(request.getParameter("product_idx"));
			review.setOrder_idx(request.getParameter("order_idx"));
			review.setMember_id((String)session.getAttribute("sId"));
			review.setReview_subject(request.getParameter("review_subject"));
			review.setReview_passwd(request.getParameter("review_passwd"));
			review.setReview_score(Integer.parseInt(request.getParameter("review_score")));
			review.setReview_content(request.getParameter("review_content"));
//			review.setProduct_img(request.getParameter("product_img"));
//			review.setProduct_name(request.getParameter("product_name"));
			
//			System.out.println(review);
			
			ReviewWriteProService service = new ReviewWriteProService();
			boolean isWriteSuccess = service.registReview(review);
			
			if(!isWriteSuccess) { // 실패시
				
				response.setContentType("text/html; charset=UTF-8");
				
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('글쓰기 실패')");
				out.println("history.back()");
				out.println("</script>");
				
			} else { // 성공시
				
				forward = new ActionForward();
				forward.setPath("ReviewList.re");
				forward.setRedirect(true);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward; 
	}

}
