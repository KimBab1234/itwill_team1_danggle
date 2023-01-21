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
			
			// 찾은 아이디의 일부만 표시하기 위한 작업
			char[] idArr = findId.toCharArray();
			idArr[1] = '*';
			idArr[2] = '*';
			findId = String.valueOf(idArr);
			
			out.print(findId);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}