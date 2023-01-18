package com.itwillbs.team1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.itwillbs.team1.svc.CommunityWriteService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.CommunityBean;

public class CommunityWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;

		try {
			String uploadPath = "upload"; 
			String realPath = request.getServletContext().getRealPath(uploadPath);
			int fileSize = 1024 * 1024 * 10;
			MultipartRequest multi = new MultipartRequest(request, 
					realPath, 
					fileSize,
					"UTF-8",
					new DefaultFileRenamePolicy());

			CommunityBean community = new CommunityBean();
			// board_type 회원들의 추천목록 = 0, 독후감 = 1 
			community.setBoard_type(Integer.parseInt(multi.getParameter("board_type")));
			community.setBoard_subject(multi.getParameter("board_subject"));
			community.setBoard_content(multi.getParameter("board_content"));
			community.setMember_id(multi.getParameter("member_id"));
			community.setBoard_file(multi.getOriginalFileName("board_file"));
			community.setBoard_real_file(multi.getFilesystemName("board_file"));

			CommunityWriteService service = new CommunityWriteService();
			boolean isWriteSucces = service.registBoard(community);

			if(isWriteSucces) {
				forward = new ActionForward();

				if(community.getBoard_type() == 0) {
					forward.setPath("Community0.co?board_type=0");
				} else {
					forward.setPath("Community1.co?board_type=1");
				}

				forward.setRedirect(true);
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('글작성 실패!')");
				out.println("history back()");
				out.println("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return forward;
	}

}
