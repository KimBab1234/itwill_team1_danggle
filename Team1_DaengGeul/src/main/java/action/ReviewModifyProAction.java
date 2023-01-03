package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewModifyProService;
import vo.ActionForward;
import vo.ReviewBean;

public class ReviewModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ReviewModifyProAction");
		ActionForward forward = null;
		
		try {
			// 전달받은 파라미터 데이터를 ReviewBean 클래스 인스턴스 생성 후 저장
			ReviewBean review = new ReviewBean();
			HttpSession session = request.getSession();
			
			request.setCharacterEncoding("UTF-8");
			review.setReview_idx(Integer.parseInt(request.getParameter("review_idx")));
//			review.setProduct_idx(request.getParameter("product_idx"));
			review.setMember_id((String)session.getAttribute("sId"));
			review.setReview_subject(request.getParameter("review_subject"));
			review.setReview_passwd(request.getParameter("review_passwd"));
			review.setReview_score(Integer.parseInt(request.getParameter("review_score")));
			review.setReview_content(request.getParameter("review_content"));
			
//			System.out.println(review);
			
			ReviewModifyProService service = new ReviewModifyProService();
			boolean isReviewWriter = service.isReviewWriter(review);
			
			if(!isReviewWriter) { // 수정권한 없음
				response.setContentType("text/html; charset=UTF-8");
				
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('수정 권한 없습니다')");
				out.println("history.back()");
				out.println("</script>");
				
			} else { // 수정권한 있음
				
				boolean isModifySuccess = service.modifyReview(review);
				
				if(!isModifySuccess) { // 수정 실패시
					response.setContentType("text/html; charset=UTF-8");
					
					PrintWriter out = response.getWriter();
					
					out.println("<script>");
					out.println("alert('수정 실패')");
					out.println("history.back()");
					out.println("</script>");
					
				}else { // 수정 성공시
					forward = new ActionForward();
					forward.setPath("ReviewDetail.re?review_idx=" + review.getReview_idx() + "&pageNum=" + request.getParameter("pageNum"));
					forward.setRedirect(true);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return forward;
	}

}
