package action;


import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductDetailService;
import svc.ReviewListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBean;
import vo.ReviewBean;

public class ProductDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===========================");
		System.out.println("ProductDetailAction 진입");
		ActionForward forward=null;

		ProductDetailService service = new ProductDetailService();

		String product_idx = request.getParameter("product_idx");

		ProductBean product = service.getProduct(product_idx); //상품 정보 가져오기
		String recentImg = request.getContextPath()+ "\\img\\product\\" + product.getImg();
		product.setDetail_img(request.getContextPath()+ "\\img\\product_detail\\" + product.getDetail_img());
		request.setAttribute("product", product); //request에 저장하기 
		request.setAttribute("img", recentImg);

		///최근 본 상품 목록 추가 시작

		if(request.getSession().getAttribute("recentImgList")==null) {
			request.getSession().setAttribute("recentImgList", new LinkedHashSet<>());
			request.getSession().setAttribute("recentIdxList", new LinkedHashSet<>());
		}

		@SuppressWarnings("unchecked")
		LinkedHashSet<String> recentImgList = (LinkedHashSet<String>)request.getSession().getAttribute("recentImgList");
		@SuppressWarnings("unchecked")
		LinkedHashSet<String> recentIdxList = (LinkedHashSet<String>)request.getSession().getAttribute("recentIdxList");

		if(recentImgList.contains(recentImg)) {
			recentImgList.remove(recentImg);
			recentIdxList.remove(product_idx);
		}
		recentImgList.add(recentImg);
		recentIdxList.add(product_idx);
		///최근 본 상품 목록 추가 끝


		////리뷰 목록 가져오기
		int listLimit = 10, pageListLimit=10; // 한 페이지에서 표시할 게시물 목록 10개 제한
		int pageNum = 1;
		ReviewListService rlService = new ReviewListService();
		int listCount = rlService.getReviewdListCount("", "", product_idx);
		int maxPage = listCount / listLimit + (listCount % listLimit == 0 ? 0 : 1);
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		int endPage = startPage + pageListLimit - 1 ;
		if(endPage > maxPage){
			endPage = maxPage;
		}
		List<ReviewBean> reviewList = rlService.getReviewList("", "", product_idx, 0, 0);
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("pageInfo", pageInfo);

		forward=new ActionForward();
		forward.setPath("order/product_detail.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
