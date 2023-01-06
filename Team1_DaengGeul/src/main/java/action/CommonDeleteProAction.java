package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.CommonDeleteProService;
import vo.ActionForward;

public class CommonDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		int common_idx = Integer.parseInt(request.getParameter("common_idx"));
		String common_subject = request.getParameter("common_subject");
		HttpSession session = request.getSession();
		String sId = (String)session.getAttribute("sId");
		System.out.println("삭제프로액션: " + common_idx);
		CommonDeleteProService service = new CommonDeleteProService();
		
		try {
			boolean isCommonWriter = service.isCommonWriter(common_idx,sId);
			
			if(!isCommonWriter) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 권한이 없습니다!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				boolean isDeleteSuccess = service.removeCommon(common_idx);
				
				if(!isDeleteSuccess) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('삭제 실패!')");
					out.println("history.back()");
					out.println("</script>");
				}else {
					forward = new ActionForward();
					forward.setPath("CommonList.cu");
					forward.setRedirect(true);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward;
	}

}
