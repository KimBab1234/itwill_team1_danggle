package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberSearchIdService;
import vo.ActionForward;

public class MemberSearchIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String email = request.getParameter("email1")+ "@" + request.getParameter("email2");
		
		MemberSearchIdService service = new MemberSearchIdService();
		String findId = service.searchId(email);

		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(findId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
