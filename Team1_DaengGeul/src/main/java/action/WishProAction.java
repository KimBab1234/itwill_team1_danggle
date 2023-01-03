package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.WishProService;
import svc.WishlistService;
import vo.ActionForward;

public class WishProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward= null;
		
		// 찜 등록을 위한 파라미터
		String product_idx = request.getParameter("product_idx");		
		HttpSession session = request.getSession();
		String sId = (String)session.getAttribute("sId");
		

		// 로그인 했을 경우
		if(sId != null && !sId.equals("")) {
			WishProService service = new WishProService();
			boolean registWish = service.registWish(product_idx, sId);
			
			// 찜등록 성공 시, 찜 개수 증가
			if(registWish) {
				String keyword = "";
				
				// 회원 찜목록 개수 조회
				WishlistService service2 = new WishlistService();
				int wishlistCount = service2.getWishlistCount(sId, keyword);
				session.setAttribute("wishlistCount", wishlistCount);
				
				// 찜목록 개수 전달
				try {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.print(wishlistCount);
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		return forward;
	}

}