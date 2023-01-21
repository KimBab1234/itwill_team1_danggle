package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberListService;
import vo.ActionForward;
import vo.MemberBean;
import vo.MemberPageInfo;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		// ------------------- 회원목록 페이지 처리 작업 -----------------------
		// 페이지 번호
		int listLimit = 5;
		int pageNum = 1;
		if(request.getParameter("pageNum") != null){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}

		// 행번호
		int startRow = (pageNum - 1) * listLimit;

		// 검색기능 통합
		String keyword = request.getParameter("keyword");
		if(keyword == null){
			keyword = "";
		}
		
		// 페이징 처리에 사용될 게시물 목록 개수 조회
		MemberListService service = new MemberListService();
		int listCount = service.getMemberListCount(keyword);
		
		int pageListLimit = 5;
		
		int maxPage = listCount/listLimit + (listCount%listLimit == 0 ? 0 : 1);
		
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		
		int endPage = startPage + pageListLimit - 1;
		
		if(endPage > maxPage){
			endPage = maxPage;
		}
		
		// 페이지정보 저장
		request.setAttribute("pageNum", pageNum);
		MemberPageInfo memberPageInfo = new MemberPageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		request.setAttribute("memberPageInfo", memberPageInfo);
		
		// ---------------------------------------------------------------------
		
		
		// ---------------------- 회원목록 조회 작업 ---------------------------
		
		HttpSession session = request.getSession();
		String sId = (String)session.getAttribute("sId");
		
		try {
			if(sId != null && sId.equals("admin")) {
				List<MemberBean> memberList = service.getMemberList(keyword, startRow, listLimit);
				
				request.setAttribute("memberList", memberList);
				forward = new ActionForward();
				forward.setPath("member/member_list.jsp");
				forward.setRedirect(false);
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 접근입니다!')");
				out.println("history.back()");
				out.println("</script>");			
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ---------------------------------------------------------------------
		
		return forward;
	}

}