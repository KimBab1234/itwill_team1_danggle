package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.QnaListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.QnaBean;

public class QnaListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String sId = (String)session.getAttribute("sId");
		
		
		//페이징 처리
		int listLimit = 10;
		int pageNum = 1;
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		System.out.println("페이지" + pageNum);
		
		int startRow = (pageNum-1)*listLimit;
		
		String keyword = request.getParameter("keyword");
		if(keyword == null) {
			keyword="";
		}
		
	
		
		QnaListService service = new QnaListService();
		
		//QnaListService 객체 통해서 게시물 목록 조회
		//getQnaList 메서드 호출하여 게시물 목록 조회
		List<QnaBean> qnaList=service.getQnaList(keyword,startRow,listLimit);
		
		int listCount = service.getQnaListCount(keyword);
		
		
		int pageListLimit = 10;
		int maxPage = listCount/listLimit + (listCount % listLimit == 0? 0 : 1);

//		System.out.println("전체 페이지 수 : " + maxPage);
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		int endPage = startPage + pageListLimit - 1;
		if(endPage > maxPage){
		endPage = maxPage;
		}
	
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		request.setAttribute("qnaList",qnaList);
		request.setAttribute("pageInfo",pageInfo);
	
//		System.out.println(qnaList);
//		System.out.println(pageInfo);
		forward = new ActionForward();
		forward.setPath("customer/qna_list.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}
}
