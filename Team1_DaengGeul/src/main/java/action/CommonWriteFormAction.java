package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;

public class CommonWriteFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		try {
			
			
			HttpSession session = request.getSession();
			String sId = (String)session.getAttribute("sId");
			
			
			if(sId == "admin") {
				forward = new ActionForward();
				forward.setPath("CommonWritePro.cu");
				forward.setRedirect(true);
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out;
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 접근입니다!')");
				out.println("history.back()");
				out.println("</script>");
			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return forward;
	}

}
