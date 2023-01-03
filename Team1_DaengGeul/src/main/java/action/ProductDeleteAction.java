package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductDeleteService;
import vo.ActionForward;
import vo.ProductBean;

public class ProductDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ProductDeleteAction - 상품 삭제");
		ActionForward forward = null;
		
		ProductDeleteService service = new ProductDeleteService();
		String product_idx = request.getParameter("product_idx");
		
		// 대표 이미지와 상세 이미지 두 개를 모두 삭제해야 하기 때문에 배열로 이름 두 개 받아옴
		ProductBean product = service.selectFileName(product_idx);

		int deleteCount = service.removeProduct(product_idx);
		
		if(deleteCount > 0) {
			String uploadPath = "upload";
			String realPath = request.getServletContext().getRealPath(uploadPath);
			
			File f = new File(realPath, product.getImg());
			File f2 = new File(realPath, product.getDetail_img());
			
			if(f.exists()) {
				f.delete();
			}
			
			if(f2.exists()) {
				f2.delete();
			}
			
			forward = new ActionForward();
			forward.setPath("ProductList.ad");
			forward.setRedirect(true);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('굿즈 삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
		return forward;
	}

}
