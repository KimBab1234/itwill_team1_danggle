package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.WishlistService;
import vo.ActionForward;

public class WishlistCountAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;

		HttpSession session = request.getSession();
		String sId = (String)session.getAttribute("sId");
		String keyword = "";
		
		WishlistService service = new WishlistService();
		int wishlistCount = service.getWishlistCount(sId, keyword);

		try {
			if(wishlistCount > 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print(wishlistCount);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
