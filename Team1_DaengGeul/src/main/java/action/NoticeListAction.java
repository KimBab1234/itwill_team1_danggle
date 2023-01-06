package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.NoticeListService;
import vo.ActionForward;
import vo.NoticeBean;
import vo.PageInfo;

public class NoticeListAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int listLimit = 10;
		
		int pageNum = 1;
		if(request.getParameter("pageNum") != null) {
			pageNum=Integer.parseInt(request.getParameter("pageNum"));
			System.out.println(pageNum);
		} 
		int startRow = (pageNum-1)*listLimit;

		String keyword = request.getParameter("keyword");

		if(keyword == null){
			keyword="";
		}
		
		
		
		NoticeListService service = new NoticeListService();
		
		List<NoticeBean> noticeList=service.getNoticeList(keyword,startRow,listLimit);
		
		int listCount = service.getNoticeListCount(keyword);
		
		
		int pageListLimit = 10;
		int maxPage = listCount/listLimit + (listCount % listLimit == 0? 0 : 1);

//		System.out.println("전체 페이지 수 : " + maxPage);
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		int endPage = startPage + pageListLimit - 1;
		if(endPage > maxPage){
		endPage = maxPage;
		}
	
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		request.setAttribute("noticeList",noticeList);
		request.setAttribute("pageInfo",pageInfo);
	
		forward = new ActionForward();
		forward.setPath("notice/qna_notice_list.jsp");
		forward.setRedirect(false);
	
//		System.out.println("listCount : " + listCount);
		
		return forward;
	}
}
