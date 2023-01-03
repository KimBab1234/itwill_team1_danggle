package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductEditService;
import vo.ActionForward;
import vo.ProductBean;

public class ProductEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ProductEditAction -  상품 수정 폼 액션 페이지");
		ActionForward forward = null;
		
		String product_idx = request.getParameter("product_idx");
		
		if(product_idx.substring(0, 1).equals("B")) { // 책 수정
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
		forward.setPath("product/product_edit_form.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
