package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.NoticeModifyProService;
import vo.ActionForward;
import vo.NoticeBean;

public class NoticeModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		String realPath = "";
		String deleteFileName = "";
		
		try {
		String uploadPath = "upload";
		realPath = request.getServletContext().getRealPath(uploadPath);
		
		File f = new File(realPath);
		
		if(!f.exists()) {
			f.mkdir();
		}
		int fileSize = 1024 * 1024 * 10;
		
		MultipartRequest multi = new MultipartRequest(request, realPath, fileSize,"UTF-8",new DefaultFileRenamePolicy());
		
		NoticeBean notice = new NoticeBean();
		
		notice.setNotice_idx(Integer.parseInt(multi.getParameter("notice_idx")));
		notice.setNotice_subject(multi.getParameter("notice_subject"));
		notice.setNotice_content(multi.getParameter("notice_content"));
		notice.setNotice_file(multi.getOriginalFileName("notice_file"));
		notice.setNotice_real_file(multi.getFilesystemName("notice_file"));
		
		NoticeModifyProService service = new NoticeModifyProService();
		boolean isNoticeWriter = service.isNoticeWriter(notice);
		
		if(!isNoticeWriter) { // 수정 권한 없음
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
			deleteFileName = notice.getNotice_real_file();
		} else {
			boolean isModifySuccess = service.modifyNotice(notice);
			
			if(!isModifySuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정 실패!')");
				out.println("history.back()");
				out.println("</script>");
				
				deleteFileName = notice.getNotice_real_file();
			} else { // 수정 성공 시
				forward = new ActionForward();
				forward.setPath("NoticeDetail.ad?notice_idx=" + notice.getNotice_idx() + "&pageNum=" + multi.getParameter("pageNum"));
				forward.setRedirect(true);
				
				if(notice.getNotice_file() != null) {
					deleteFileName = multi.getParameter("notice_real_file");
				}
			}
		}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 예외가 발생하더라도 파일 삭제는 무조건 수행하도록 finally 블록 작성
			// File 객체 생성(파라미터로 디렉토리명, 파일명 전달)
			File f = new File(realPath, deleteFileName);
			
			// 해당 디렉토리 및 파일 존재 여부 판별
			if(f.exists()) { // 존재할 경우
				// File 객체의 delete() 메서드를 호출하여 해당 파일 삭제
				f.delete();
			}
			
		}
		return forward;
	}

}
