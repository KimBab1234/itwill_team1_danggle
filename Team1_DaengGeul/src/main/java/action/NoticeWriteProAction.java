package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.x.Notice;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.NoticeWriteProService;
import vo.ActionForward;

import vo.NoticeBean;

public class NoticeWriteProAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = null;
		
		try {
			String uploadPath = "upload";
			String realPath = request.getServletContext().getRealPath(uploadPath);
			System.out.println("실제 업로드 경로 : " + realPath);
			int fileSize = 1024*1024*10;
			
			String writerIpAddr = request.getRemoteAddr();
			
			MultipartRequest multi = new MultipartRequest(request, realPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			
			NoticeBean notice = new NoticeBean();
			
			notice.setNotice_content(multi.getParameter("notice_content"));
			notice.setNotice_file(multi.getOriginalFileName("notice_file"));
			notice.setNotice_real_file(multi.getFilesystemName("notice_file"));
			notice.setNotice_subject(multi.getParameter("notice_subject"));
			
			System.out.println(notice);
			NoticeWriteProService service = new NoticeWriteProService();
			boolean isWriteSuccess = service.registerNotice(notice);
			
			if(!isWriteSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('공지 등록 실패')");
				out.println("history.back()");
				out.println("</script>");
			}else {
				forward = new ActionForward();
				forward.setPath("NoticeList.ad");
				forward.setRedirect(true);
			}
	} catch (IOException e) {
		e.printStackTrace();
	}
	
			
		return forward;
	
	}
}
