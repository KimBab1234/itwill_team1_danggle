package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import svc.ReviewDetailService;
import svc.ReviewWriteFormService;
import vo.ActionForward;
import vo.ReviewBean;

public class ReviewDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		
		// 상세정보 조회에 필요한 글번호 가져오기
		int review_idx= Integer.parseInt(request.getParameter("review_idx"));
		String member_id = (String)session.getAttribute("sId");
		String product_idx = request.getParameter("product_idx");
		
//		System.out.println("sId: " + sId);
//		System.out.println("review_idx = " + review_idx);
		
		ReviewDetailService service = new ReviewDetailService();
		ReviewBean review =  service.getReview(review_idx, true, member_id);
		ReviewWriteFormService service1 = new ReviewWriteFormService();
		ReviewBean review1 = service1.getReview(product_idx);
//		System.out.println(board);
		
		// 뷰페이지로 데이터 전달을 위해 requrest 객체에 전달
		
		request.setAttribute("review", review);
		request.setAttribute("review1", review1);
		
		//4. 좋아요 버튼 눌렀을때 액션에서 review_like_done 파라미터 가져와서 Y, N 확인하고 
		//서비스로 넘기고 서비스에서 dao로 넘기고 (동일)
		//dao에서 Y면 delete, N이면 insert 하기
		
		if(review.getReview_like_done().equals("N")) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('좋아요 실패')");
				out.println("history.back()");
				out.println("</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			
			// ActionForward 객체를 통해 qna_board_view.jsp 페이지 포워딩 설정
			// URL 유지 및 request 객체 유지를 위해 Dispatch 방식 포워딩
			
			forward = new ActionForward();
			forward.setPath("review/review_view.jsp");
			forward.setRedirect(false);
			
			
		}
		
		
		
		return forward;
	}

}
