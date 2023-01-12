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
		String pageNum = request.getParameter("pageNum");
		String product_idx = request.getParameter("product_idx");
		// 이미지 파일명 조회
		ProductBean product = service.selectFileName(product_idx);
		String product_type = request.getParameter("product_idx");
		System.out.println("상품 타입 : " + product_type.substring(0, 1));
		int deleteCount = service.removeProduct(product_idx);
		
		if(deleteCount > 0) {
			String uploadPath = "upload";
			String realPath = request.getServletContext().getRealPath(uploadPath);
			System.out.println("이미지 이름 확인 : " + product.getImg());
			File f = new File(realPath, product.getImg());
			File f2 = new File(realPath, product.getDetail_img());
			
			if(f.exists()) {
				f.delete();
			}
			
			if(f2.exists()) {
				f2.delete();
			}
			if(product_type.substring(0, 1).equals("B")) {
				forward = new ActionForward();
				forward.setPath("ProductList.ad?pageNum="+pageNum);
				forward.setRedirect(true);
			}else if(product_type.substring(0, 1).equals("G")) {
			// 굿즈 삭제 후 굿즈 목록 페이지로 이동하기 위해 forward 나눴음
				forward = new ActionForward();
				forward.setPath("ProductList.ad?product=" + "G");
				forward.setRedirect(true);
			}
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
