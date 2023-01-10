package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.CommunityListService;
import vo.ActionForward;
import vo.CommunityBean;
import vo.PageInfo;

public class CommunityListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		// 페이징 처리
		int type = Integer.parseInt(request.getParameter("board_type"));

		int listLimit = 10;
		int pageNum = 1;
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}

		int startRow = (pageNum - 1) * listLimit;
		
		String keyword = request.getParameter("keyword");

		if(keyword == null){
			keyword = "";
		}
		
		// communityList service 
		CommunityListService service = new CommunityListService();
		List<CommunityBean> community = service.getList(type, keyword, startRow, listLimit);

		int listCount = service.getBoardListCount(keyword, type);
		int pageListLimit = 10;
		
		int maxPage = listCount / listLimit 
				+ (listCount % listLimit == 0 ? 0 : 1); 
		
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		
		int endPage = startPage + pageListLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		// communityList에 페이징 처리, 리스트목록, 추천 갯수 넘기기
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("board", community);
		
		forward = new ActionForward();
		
		if(type == 0) {
			forward.setPath("community/Community_List0.jsp");
		} else {
			forward.setPath("community/Community_List1.jsp");
		}

		return forward;
	}
}
