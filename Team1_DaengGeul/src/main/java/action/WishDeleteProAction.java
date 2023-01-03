package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.WishDeleteProService;
import svc.WishlistService;
import vo.ActionForward;

public class WishDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String sId = (String)session.getAttribute("sId");
		String[] listArr = request.getParameterValues("listArr");

		// 찜목록 삭제 시
		WishDeleteProService service = new WishDeleteProService();
		boolean isDeletewish = service.deleteWish(sId, listArr);
		
		if(isDeletewish) {
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
			
			forward = new ActionForward();
			forward.setPath("Wishlist.ws");
			forward.setRedirect(true);			
		}
		
		return forward;
	}

}
