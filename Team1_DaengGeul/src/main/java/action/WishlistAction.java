package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.WishlistService;
import vo.ActionForward;
import vo.MemberPageInfo;
import vo.WishlistBean;

public class WishlistAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String sId = (String)session.getAttribute("sId");
		
		// -------------------- 찜목록 페이지 처리 작업 ------------------------
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
		WishlistService service = new WishlistService();
		int wishlistCount = service.getWishlistCount(sId, keyword);
		
		int pageListLimit = 5;
		
		int maxPage = wishlistCount/listLimit + (wishlistCount%listLimit == 0 ? 0 : 1);
		
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		
		int endPage = startPage + pageListLimit - 1;
		
		if(endPage > maxPage){
			endPage = maxPage;
		}
		
		// 페이지정보 저장
		request.setAttribute("pageNum", pageNum);
		MemberPageInfo memberPageInfo = new MemberPageInfo(wishlistCount, pageListLimit, maxPage, startPage, endPage);
		request.setAttribute("memberPageInfo", memberPageInfo);
		
		// ---------------------------------------------------------------------
		
		
		// ------------------------ 찜목록 조회 작업 ---------------------------
		List<WishlistBean> wishlist = service.wishlist(sId, keyword, startRow, listLimit);
		
		request.setAttribute("wishlist", wishlist);
		forward = new ActionForward();
		forward.setPath("wish/wishlist.jsp");
		forward.setRedirect(false);
		// ---------------------------------------------------------------------

		
		return forward;
	}

}