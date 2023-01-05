package action;

import java.awt.Checkbox;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductEditListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBean;

public class ProductEditListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ProductEditListAction - 상품 목록 액션 페이지");
		ActionForward forward = null;
		// 페이징 처리
		int listLimit = 10;
		int pageNum = 1;
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		int startRow = (pageNum - 1) * listLimit;
		
		ProductEditListService service = new ProductEditListService();
		ArrayList<ProductBean> ProductList = service.getProductList(startRow, listLimit);
		
		// 페이징 처리
		int listCount = service.getProductListCount();
		int pageListLimit = 10;
		int maxPage = listCount / listLimit + (listCount % listLimit == 0 ? 0 : 1);
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		int endPage = startPage + pageListLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);

		request.setAttribute("productList", ProductList);
		request.setAttribute("pageInfo", pageInfo);
        
		forward = new ActionForward();
		forward.setPath("product/productList.jsp");
		forward.setRedirect(false);
			
		return forward;
	}

}
