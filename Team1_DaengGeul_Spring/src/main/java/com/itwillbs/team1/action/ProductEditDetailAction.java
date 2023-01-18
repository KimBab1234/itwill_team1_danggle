package com.itwillbs.team1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.team1.svc.ProductEditService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.ProductBean;

public class ProductEditDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ProductEditDetailAction");
		ActionForward forward = null;
		String pageNum = request.getParameter("pageNum");
		String product_idx = request.getParameter("product_idx");
		
		if(product_idx.substring(0, 1).equals("B")) {
			ProductEditService service = new ProductEditService();
			ProductBean book = service.getBook(product_idx);
			
			request.setAttribute("product", book);
			request.setAttribute("productType", "book");
			
		} else { // 굿즈 수정
			ProductEditService service = new ProductEditService();
			ProductBean goods = service.getGoods(product_idx);
			
			request.setAttribute("productType", "goods");
			request.setAttribute("product", goods);
		}
		
		forward = new ActionForward();
		forward.setPath("product/product_edit_detail.jsp?pageNum="+pageNum);
		forward.setRedirect(false);
		
		return forward;
	}

}
