package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ReviewListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ReviewBean;

public class ReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ReviewListAction");
		ActionForward forward = null;
		
		// RevieListService 객체를 통해 게시물 목록 조회 후
		// 조회 결과를 request 객체를 통해 review_list.jsp 페이지로 전달
		//============================================================================================
		//페이징 처리를 위한 계산 작업
		//1. 한 페이지에서 표시할 게시물 목록 수와 페이지 목록 수 설정
		int listLimit = 10; // 한 페이지에서 표시할 게시물 목록 10개 제한

		//2. 현재 페이지 번호 설정(pageNum 파라미터 사용)
		//=> pageNum 파라미터가 존재하면 해당 값을 저장하고, 아니면 기본값1 사용
		int pageNum = 1;

			
		if(request.getParameter("pageNum") != null){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}

		//System.out.println(pageNum);

		//3. 현재 페이지에서 목록으로 표시할 첫 게시물의 행(레코드) 번호 계산
		//
		int startRow = (pageNum -1) * listLimit; // 조회 시작 행 번호 계산

		//파라미터로 전달받은 검색어 keyword를 가져와서 변수에 저장

		String keyword = request.getParameter("keyword");
		String id = (String)request.getSession().getAttribute("sId");
		String product_idx = request.getParameter("product_idx");
		
		//만약 전달받은 검색어가 null이면 널스트링으로 변경
		if(keyword == null){
			keyword = "";
		}
		// ---------------------------------------------------------
		// BoardListService 클래스 인스턴스 생성
		ReviewListService service = new ReviewListService();
		// BoardListService 객체의 getBoardList() 메서드를 호출하여 게시물 목록 조회
		// => 파라미터 : 검색어, 시작행번호, 목록갯수   리턴타입 : List<BoardBean>(boardList)
		List<ReviewBean> reviewList = service.getReviewList(keyword, id, product_idx, startRow, listLimit);
		
		// ---------------------------------------------------------
		// 페이징 처리
		// 한 페이지에서 표시할 페이지 목록(번호) 갯수 계산
		// 1. BoardListService - selectBoardListCount() 메서드를 호출하여 전체 게시물 수 조회(페이지 목록 계산에 사용)
		// => 파라미터 : 검색어   리턴타입 : int(listCount)
		int listCount = service.getReviewdListCount(keyword, id, product_idx);
//							System.out.println("총 게시물 수 : " + listCount);
		
		// 2. 한 페이지에서 표시할 페이지 목록 갯수 설정
		int pageListLimit = 10; // 한 페이지에서 표시할 페이지 목록 3개 제한
		
		// 3. 전체 페이지 목록 수 계산
		// => 전체 게시물 수를 페이지 당 게시물 목록 수로 나눈 몫 계산
//				 			int maxPage = listCount / listLimit;
		
//				 			if(listCount % listLimit > 0){
//				 				maxPage++;
//				 			}
//				 			System.out.println("전체 페이지 수 : " + maxPage);
		
		// 삼항 연산자(=조건연산자)를 활용하여 동일한 계산 수행
		// (조건식 ? 값1 : 값2)
		// => 나눗셈 결과에 추가로 1 페이지를 더할지 말지 판별하여 다른 값 덧셈
		int maxPage = listCount / listLimit + (listCount % listLimit == 0 ? 0 : 1);
//				 			System.out.println("전체 페이지 수 : " + maxPage);

		// 4. 시작 페이지 번호 계산
		// => (현재페이지번호 -1) / 페이지목록갯수 * 페이지목록갯수 + 1
		// ex) 현재페이지 : 1 => 시작페이지 = (1 - 1) / 10 * 10 + 1 = 1페이지
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		
		// 5. 끝 페이지 번호 계산
		// 시작페이지 + 페이지목록갯수 -1
		int endPage = startPage + pageListLimit - 1 ;
		
		// 6. 만약, 끝 페이지 번호(endPage)가 전체(최대) 페이지 번호(maxPage) 보다
		// 클 경우, 끝 페이지 번호를 최대 페이지 번호로 교체
		
		if(endPage > maxPage){
			endPage = maxPage;
		}

		// PageInfo 객체 생성 후 페이징 처리 정보 저장
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		// -------------------------------------------------------------------------
		// 글목록과 페이징정보처리를 request 객체에 저장 - setAttribute()
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("pageInfo", pageInfo);

		// ActionForward 객체 생성 후 board/qna_board_list.jsp 페이지 포워딩 설정
		
		forward = new ActionForward();
		if(product_idx!=null) {
			forward.setPath("review/review_list_detail.jsp");
		} else {
			forward.setPath("review/review_list.jsp");
		}
		forward.setRedirect(false);

		return forward;
	}

}
