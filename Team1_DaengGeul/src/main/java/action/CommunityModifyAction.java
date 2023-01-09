package action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.CommunityModifyService;
import vo.ActionForward;
import vo.CommunityBean;

public class CommunityModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		System.out.println("CommunityModifyAction");
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

			MultipartRequest multi = new MultipartRequest(request,
					realPath, 
					fileSize, 
					"UTF-8", 
					new DefaultFileRenamePolicy());

			CommunityBean community = new CommunityBean();
			community.setBoard_type(Integer.parseInt(multi.getParameter("board_type")));
			community.setBoard_idx(Integer.parseInt(multi.getParameter("board_idx")));
			community.setBoard_subject(multi.getParameter("board_subject"));
			community.setBoard_content(multi.getParameter("board_content"));
			community.setMember_id(multi.getParameter("member_id"));
			community.setBoard_file(multi.getOriginalFileName("board_file"));
			community.setBoard_real_file(multi.getFilesystemName("board_file"));

			CommunityModifyService service = new CommunityModifyService();
			boolean successModify = service.communityModify(community);

			if(!successModify) { // 삭제에 실패할 경우
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");

				deleteFileName = community.getBoard_real_file();
			} else { // 삭제에 성공한 경우
				forward = new ActionForward();
				forward.setPath("CommunityDetail.co?board_idx="+community.getBoard_idx());
				forward.setRedirect(true);
			}
		}	catch (Exception e) {
		} finally {
			File f = new File(realPath, deleteFileName);
			if(f.exists()) { 
				f.delete();
			}
		}
		return forward;
	}

}
