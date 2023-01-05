package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.QnaModifyProService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		String realPath = "";
		// 수정 작업 결과에 따라 삭제할 파일이 달라지므로 파일명을 저장할 변수 선언
		String deleteFileName = "";
		
		try {
			String uploadPath = "upload"; // 업로드 가상 디렉토리(이클립스)
			realPath = request.getServletContext().getRealPath(uploadPath);
//			System.out.println("실제 업로드 경로 : " + realPath);
			// D:\Shared\JSP\workspace_jsp5\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MVC_Board\ upload
			
			// 만약, 해당 디렉토리가 존재하지 않을 경우 디렉토리 생성
			// => java.io.File 클래스 인스턴스 생성(파라미터로 해당 디렉토리 전달)
			File f = new File(realPath);
			// => 단, File 객체가 생성되더라도 해당 디렉토리 또는 파일을 직접 생성 X
			// 실제 경로에 대상 존재 여부 판별
			if(!f.exists()) { // 해당 경로가 존재하지 않을 경우
				// File 객체의 mkdir() 메서드를 호출하여 경로 생성 
				f.mkdir();
			}
			
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
			
			HttpSession session = request.getSession();
			String sId = (String)session.getAttribute("sId");
			qna.setQna_idx(Integer.parseInt(multi.getParameter("qna_idx")));
			qna.setQna_id(sId);
			qna.setQna_subject(multi.getParameter("qna_subject"));
			qna.setQna_content(multi.getParameter("qna_content"));
			qna.setQna_file(multi.getOriginalFileName("qna_file"));
			qna.setQna_original_file(multi.getFilesystemName("qna_file"));
			
			System.out.println(qna);
			QnaModifyProService service = new QnaModifyProService();
			boolean isQnaWriter = service.isQnaWriter(qna);
			
			if(!isQnaWriter) { // 수정 권한 없음
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정 권한이 없습니다!')");
				out.println("history.back()");
				out.println("</script>");
				
				// 삭제할 파일명을 새 파일의 실제 파일명으로 지정
				deleteFileName = qna.getQna_original_file();
			} else { // 수정 권한 있음
				boolean isModifySuccess = service.modifyQna(qna);
				
				if(!isModifySuccess) { // 수정 실패 시 
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('수정 실패!')");
					out.println("history.back()");
					out.println("</script>");
					
					deleteFileName = qna.getQna_original_file();
					forward = new ActionForward();
					forward.setPath("QnaDetail.cu?qna_idx=" + qna.getQna_idx() + "&pageNum=" + multi.getParameter("pageNum"));
					forward.setRedirect(true);
					
					if(qna.getQna_file() != null) {
						deleteFileName = multi.getParameter("qna_original_file");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			File f = new File(realPath, deleteFileName);
			
			if(f.exists()) { // 존재할 경우
				f.delete();
			}
		}
		
		return forward;
	}

}
