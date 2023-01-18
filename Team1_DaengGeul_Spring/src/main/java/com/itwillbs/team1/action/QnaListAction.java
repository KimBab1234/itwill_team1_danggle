package com.itwillbs.team1.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.team1.svc.QnaListService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.PageInfo;
import com.itwillbs.team1.vo.QnaBean;

public class QnaListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String sId = (String)session.getAttribute("sId");
		response.setContentType("text/html; charset=UTF-8");
		try {
			PrintWriter out;
			out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근입니다!')");
			out.println("history.back()");
			out.println("</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//페이징 처리
		int listLimit = 10;
		int pageNum = 1;
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
//		System.out.println("페이지" + pageNum);
		
		int startRow = (pageNum-1)*listLimit;
		
		String keyword = request.getParameter("keyword");
		if(keyword == null) {
			keyword="";
		}
		
	
		
		QnaListService service = new QnaListService();
		
		//QnaListService 객체 통해서 게시물 목록 조회
		//getQnaList 메서드 호출하여 게시물 목록 조회
		List<QnaBean> qnaList=service.getQnaList(sId,keyword,startRow,listLimit);
		
		int listCount = service.getQnaListCount(sId,keyword);
		
		
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
		
//		try {
//			if(sId != null&& sId != "" &&sId == "admin") {
//				// 관리자는 모든 문의를 다 보여준다
//				forward = new ActionForward();
//				forward.setPath("customer/qna_list.jsp");
//				forward.setRedirect(false);
//				
//			} else if(sId != null && sId != "") {
//				// 일반회원은 본인 것이랑 관리자 것만 보여준다 
//				
//				
//			} else {
//				// sId 가 없을 시에 잘못된 접근입니다 뒤로보내기
//				response.setContentType("text/html; charset=UTF-8");
//				PrintWriter out = response.getWriter();
//				out.println("<script>");
//				out.println("alert('잘못된 접근입니다!')");
//				out.println("history.back()");
//				out.println("</script>");
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		forward = new ActionForward();
		forward.setPath("customer/qna_list.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}
}
