package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CommunityDeleteService;
import vo.ActionForward;

public class CommunityDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null; 
		
		// 지울 글 번호와 삭제후 돌아갈 게시판 타입 번호, 삭제할 진짜 파일 명
		int board_idx = Integer.parseInt(request.getParameter("board_idx"));
		int board_type = Integer.parseInt(request.getParameter("board_type"));
		String board_real_file = request.getParameter("board_real_file");
		
		System.out.println("파일이름명 : " + board_real_file);
		
		CommunityDeleteService service = new CommunityDeleteService();
		boolean deleteBoard = service.deletBoard(board_idx);
		
		try {
			if(!deleteBoard) { // 삭제 실패
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else { // 삭제 성공
				String uploadPath = "upload";
				
				String realPath = request.getServletContext().getRealPath(uploadPath);
				
				// 업로드된 실제 파일
				File f = new File(realPath,board_real_file);

				if(f.exists()) { // 존재할 경우			
				// File 객체의 delete() 메서드 호출하여 해당 파일 삭제
				f.delete();
				}
				
				forward = new ActionForward();
				forward.setPath("Community"+board_type+".cu?board_type="+board_type);
				forward.setRedirect(true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
