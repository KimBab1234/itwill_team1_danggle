package com.itwillbs.team1.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.itwillbs.team1.svc.ProductEditProService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.ProductBean;

public class ProductEditProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ProductEditProAction - 상품 수정 처리 액션 페이지");
		ActionForward forward = null;
		boolean isNewFile = false;
		int updateCount = 0;
		int pageNum = 0;
		String deleteImg = "";
		String deleteDetailImg = "";
		try {
			String uploadPath = "upload";
			String realPath = request.getServletContext().getRealPath(uploadPath);
			int fileSize = 1024 * 1024 * 10;
			MultipartRequest multi = new MultipartRequest(
					request, 
					realPath,
					fileSize,
					"UTF-8",
					new DefaultFileRenamePolicy()
			);
			
			String product_idx = multi.getParameter("product_idx");
			
			if(product_idx.substring(0, 1).equals("B")) { // 책 수정 처리
			pageNum = Integer.parseInt(multi.getParameter("pageNum"));
			
			ProductBean product = new ProductBean();
			
			product.setProduct_idx(product_idx);
			product.setName(multi.getParameter("name"));
			product.setBook_genre(multi.getParameter("book_genre"));
			product.setBook_writer(multi.getParameter("book_writer"));
			product.setBook_publisher(multi.getParameter("book_publisher"));
			LocalDate date = LocalDate.parse(multi.getParameter("book_date"));
			product.setBook_date(java.sql.Date.valueOf(date));
			product.setPrice(Integer.parseInt(multi.getParameter("price")) );
			product.setDiscount(Integer.parseInt(multi.getParameter("discount")));
			product.setQuantity(Integer.parseInt(multi.getParameter("quantity")));
			product.setDetail(multi.getParameter("detail"));
			product.setImg(multi.getOriginalFileName("img"));
			product.setDetail_img(multi.getOriginalFileName("detail_img"));
			
			// 대표 이미지 수정하지 않을 경우
				if(product.getImg() == null) {
					product.setImg(multi.getParameter("old_img"));
				} else {
					isNewFile = true;
				}
				// 상세 이미지 수정하지 않을 경우
				if(product.getDetail_img() == null) {
					product.setDetail_img(multi.getParameter("old_detail_img"));
				} else {
					isNewFile = true;
				}
				
				deleteImg = product.getImg();
				deleteDetailImg = product.getDetail_img();
				// 업데이트 서비스 호출
				ProductEditProService service = new ProductEditProService();
				updateCount = service.updateBook(product);
				
			}else if(product_idx.substring(0, 1).equals("G")) { // 굿즈 수정 처리
				ProductBean product = new ProductBean();
				
				product.setProduct_idx(product_idx);
				product.setName(multi.getParameter("name"));
				product.setPrice(Integer.parseInt(multi.getParameter("price")));
				product.setDiscount(Integer.parseInt(multi.getParameter("discount")));
				product.setQuantity(Integer.parseInt(multi.getParameter("quantity")));
				product.setImg(multi.getParameter("img"));
				product.setDetail(multi.getParameter("detail"));
				product.setDetail_img(multi.getParameter("detail_img"));
				
				// 굿즈 옵션
				String[] strOptName = multi.getParameterValues("option_name");
				String[] strOptNum = multi.getParameterValues("option_qauntity");
				// string[] -> int[] 변환
				
				if(strOptName != null && strOptNum != null) {
					List<String> option_name = new ArrayList<>(List.of(strOptName));
					int[] optQauArr = Stream.of(strOptNum).mapToInt(Integer::parseInt).toArray();
					List<Integer> option_qauntity = Arrays.stream(optQauArr).boxed().collect(Collectors.toList());
					product.setOption_name(option_name);
					product.setOption_qauntity(option_qauntity);
				}
				
				// 대표 이미지 수정하지 않을 경우
				if(product.getImg() == null) {
					product.setImg(multi.getParameter("old_img"));
				} else {
					isNewFile = true;
				}
				// 상세 이미지 수정하지 않을 경우
				if(product.getDetail_img() == null) {
					product.setDetail_img(multi.getParameter("old_detail_img"));
				} else {
					isNewFile = true;
				}
				deleteImg = product.getImg();
				deleteDetailImg = product.getDetail_img();
				// 업데이트 서비스 호출
				ProductEditProService service = new ProductEditProService();
				updateCount = service.updateGoods(product);
			}
			
			if(updateCount > 0) { // 수정 성공 시
				
				
				if(isNewFile) {
					// 수정 성공 시 기존 파일 삭제
					System.out.println("확인용 : " + multi.getParameter("old_img"));
					File f = new File(realPath, multi.getParameter("old_img"));
					File f2 = new File(realPath, multi.getParameter("old_detail_img"));
					if(f.exists()) {
						f.delete();
					}
					if(f2.exists()) {
						f.delete();
					}
				}
				
				if(product_idx.substring(0, 1).equals("B")) { // 책은 pageNum 함께 넘겨줘야 함!
					forward = new ActionForward();
					forward.setPath("ProductEditDetail.ad?product_idx="+product_idx+"&pageNum="+pageNum);
					forward.setRedirect(true);
				}else if(product_idx.substring(0, 1).equals("G")) {
					forward = new ActionForward();
					forward.setPath("ProductEditDetail.ad?product_idx="+product_idx);
					forward.setRedirect(true);
				}
				
			}else { // 수정 실패 시
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('굿즈 등록 수정 실패')");
				out.println("histroy.back()");
				out.println("</script>");
				
				if(isNewFile) { 
				// 수정 실패 시 새 파일 삭제
					File f = new File(realPath, deleteImg);
					File f2 = new File(realPath, deleteDetailImg);
					
					if(f.exists()) {
						f.delete();
					}
					if(f2.exists()) {
						f2.delete();
					}
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return forward;
	}

}
