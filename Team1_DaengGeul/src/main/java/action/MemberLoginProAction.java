package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import encrypt.MyMessageDigest;
import svc.MemberLoginProService;
import svc.WishlistService;
import vo.ActionForward;

public class MemberLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		// ----------------- 패스워드 암호화(해싱) 기능 추가 ----------------------
		String id = request.getParameter("id");
		MyMessageDigest md = new MyMessageDigest("SHA-256");
		String passwd = md.hashing(request.getParameter("passwd"));
		// ------------------------------------------------------------------------
		
		// 사용자 가입유무 확인
		MemberLoginProService service = new MemberLoginProService();
		boolean isLoginSuccess = service.loginMember(id, passwd);
		
		try {
			if(isLoginSuccess) {
				HttpSession session = request.getSession();
				String keyword = "";
				
				// 로그인하면 찜목록 개수 가져와서 반영
				WishlistService service2 = new WishlistService();
				int wishlistCount = service2.getWishlistCount(id, keyword);
				
				session.setAttribute("sId", id);
				session.setAttribute("wishlistCount", wishlistCount);
				
				forward = new ActionForward();
				forward.setPath("./");
				forward.setRedirect(true);
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('로그인 실패!')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}