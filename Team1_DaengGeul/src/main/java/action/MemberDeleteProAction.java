package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import encrypt.MyMessageDigest;
import svc.MemberDeleteProService;
import vo.ActionForward;

public class MemberDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String id = request.getParameter("id");		
		// ----------------- 패스워드 암호화(해싱) 기능 추가 ----------------------
		MyMessageDigest md = new MyMessageDigest("SHA-256");
		String passwd = md.hashing(request.getParameter("passwd"));
		System.out.println(passwd);
		// ------------------------------------------------------------------------
		
		MemberDeleteProService service = new MemberDeleteProService();
		int memberDeleteCount = service.deleteMember(id, passwd);

		try {
			if(memberDeleteCount > 0) {
				forward = new ActionForward();
				forward.setPath("member/member_delete_result.jsp");
				forward.setRedirect(false);
				
				HttpSession session = request.getSession();
				session.removeAttribute("sId");
				
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호를 확인하세요!')");
				out.println("history.back()");
				out.println("</script>");			
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}