package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewDeleteProService;
import svc.ReviewDetailService;
import vo.ActionForward;
import vo.ReviewBean;

public class ReviewDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("ReviewDeleteProAction");
		
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		// 상세정보 조회에 필요한 글번호 가져오기
		int review_idx = Integer.parseInt(request.getParameter("review_idx"));
		String review_passwd = request.getParameter("review_passwd");
		String member_id = (String)session.getAttribute("sId");
//		System.out.println("review_idx = " + review_idx + "review_passwd = " + review_passwd );
		
		try {
			ReviewDeleteProService service = new ReviewDeleteProService();
			
			boolean isReviewWriter =  service.isReviewWriter(review_idx, review_passwd);
			System.out.println(isReviewWriter);
			
			if(!isReviewWriter) {
				// 자바스크립트 사용하여 글쓰기 실패 출력하고 이전페이지로 돌아가기
				// 1.
				response.setContentType("text/html; charset=UTF-8");
				
				// 2.
				PrintWriter out = response.getWriter();
				
				// 3.
				out.println("<script>");
				out.println("alert('삭제 권한 없습니다')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				
				// ReviewDetailService 객체의 getReview() 메서드 호출하여 삭제할 파일명 조회
				ReviewDetailService service2 = new ReviewDetailService();
				ReviewBean review = service2.getReview(review_idx, false, member_id);
				// 주의 레코드 삭제 전 정보 조회 먼저 수행해야함
				
				boolean isDeleteSuccess = service.removeReview(review_idx);
				if(!isDeleteSuccess) {
					// 자바스크립트 사용하여 글쓰기 실패 출력하고 이전페이지로 돌아가기
					// 1.
					response.setContentType("text/html; charset=UTF-8");
					
					// 2.
					PrintWriter out = response.getWriter();
					
					// 3.
					out.println("<script>");
					out.println("alert('삭제 실패')");
					out.println("history.back()");
					out.println("</script>");
				}else { // 삭제 성공시
					
					forward = new ActionForward();
					forward.setPath("ReviewList.re?pageNum=" + request.getParameter("pageNum"));
					forward.setRedirect(true);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
