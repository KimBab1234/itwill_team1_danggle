package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.QnaModifyProService;
import svc.QnaReplyProService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaReplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = null;
		
		
		
		try {
			String uploadPath = "upload"; // 업로드 가상 디렉토리(이클립스)
			String realPath = request.getServletContext().getRealPath(uploadPath);
//			System.out.println("실제 업로드 경로 : " + realPath);
			// D:\Shared\JSP\workspace_jsp5\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MVC_Board\ upload
			
			// 만약, 해당 디렉토리가 존재하지 않을 경우 디렉토리 생성
			// => java.io.File 클래스 인스턴스 생성(파라미터로 해당 디렉토리 전달)
			
			// => 단, File 객체가 생성되더라도 해당 디렉토리 또는 파일을 직접 생성 X
			// 실제 경로에 대상 존재 여부 판별
			
			int fileSize = 1024 * 1024 * 10;
			// --------------------------------------------------------------------
			// 파일 업로드 처리(enctype="mutlipart/form-data") 를 위해
			// MultipartRequest 객체 생성 => cos.jar 라이브러리 필요
			MultipartRequest multi = new MultipartRequest(
					request,  // 1) 실제 요청 정보(파라미터)가 포함된 request 객체
					realPath, // 2) 실제 업로드 경로
					fileSize, // 3) 업로드 파일 최대 사이즈
					"UTF-8",  // 4) 한글 파일명 처리 위한 인코딩 방식
					new DefaultFileRenamePolicy() // 5) 중복 파일명을 처리할 객체
			);
			
			
			QnaBean qna = new QnaBean();
			String pageNum = request.getParameter("pageNum");
			HttpSession session = request.getSession();
			String sId = (String)session.getAttribute("sId");
			qna.setQna_idx(Integer.parseInt(multi.getParameter("qna_idx")));
			qna.setQna_id(sId);
			qna.setQna_subject(multi.getParameter("qna_subject"));
			qna.setQna_content(multi.getParameter("qna_content"));
			qna.setQna_file(multi.getOriginalFileName("qna_file"));
			qna.setQna_original_file(multi.getFilesystemName("qna_file"));
			qna.setQna_re_ref(Integer.parseInt(multi.getParameter("qna_re_ref")));
			qna.setQna_re_lev(Integer.parseInt(multi.getParameter("qna_re_lev")));
			qna.setQna_re_seq(Integer.parseInt(multi.getParameter("qna_re_seq")));
			
			
			System.out.println(qna);
			
			if(qna.getQna_file() == null) {
				qna.setQna_file("");
				qna.setQna_original_file("");
			}
			
			QnaReplyProService service = new QnaReplyProService();
			boolean isWriteSuccess = service.registReplyQna(qna);
			System.out.println(isWriteSuccess);
			if(!isWriteSuccess) { // 수정 권한 없음
				
				File f = new File(realPath, qna.getQna_original_file());
				
				// 해당 디렉토리 및 파일 존재 여부 판별
				if(f.exists()) { // 존재할 경우
					// File 객체의 delete() 메서드를 호출하여 해당 파일 삭제
					f.delete();
				
				} response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('답글 쓰기 실패!')");
				out.println("history.back()");
				out.println("</script>");
				
				
			}else { // 수정 권한 있음
				forward = new ActionForward();
				forward.setPath("QnaList.cu?pageNum=" + multi.getParameter("pageNum"));
				forward.setRedirect(true);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
