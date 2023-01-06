package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.CommonModifyProService;
import vo.ActionForward;
import vo.CommonBean;

public class CommonModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		try {
			CommonBean common = new CommonBean();
			common.setCommon_idx(Integer.parseInt(request.getParameter("common_idx")));
			HttpSession session = request.getSession();
			String sId = (String)session.getAttribute("sId");
			common.setCommon_subject(request.getParameter("common_subject"));
			common.setCommon_content(request.getParameter("common_content"));
			
			System.out.println("프로액션  : " + common);
			
			CommonModifyProService service = new CommonModifyProService();
			boolean isCommonWriter = service.isCommonWriter(common, sId);
			
			if(!isCommonWriter) { // 수정 권한 없음
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정 권한이 없습니다!')");
				out.println("history.back()");
				out.println("</script>");
				
			
			} else { // 수정 권한 있음
				boolean isModifySuccess = service.modifyCommon(common);
				
				if(!isModifySuccess) { // 수정 실패 시 
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('수정 실패!')");
					out.println("history.back()");
					out.println("</script>");
					}
				forward = new ActionForward();
				forward.setPath("CommonDetail.cu?common_idx=" + common.getCommon_idx());
				forward.setRedirect(true);
				
				System.out.println(common.getCommon_subject());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return forward;
	}

}
