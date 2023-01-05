package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.CommonWriteService;
import vo.ActionForward;
import vo.CommonBean;

public class CommonWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String sId = (String)session.getAttribute("sId");
		
		try {
		
			CommonBean common = new CommonBean();
			
			common.setCommon_subject(request.getParameter("common_subject"));
			common.setCommon_content(request.getParameter("common_content"));
			
			System.out.println("writepro : " + common);
			
			CommonWriteService service = new CommonWriteService();
			boolean isWriteSuccess = service.registerCommon(common,sId);
			
			if(!isWriteSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('게시물 등록 실패')");
				out.println("history.back()");
				out.println("</script>");
			}else {
				forward = new ActionForward();
				forward.setPath("CommonList.co");
				forward.setRedirect(true);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return forward;
	}

}
