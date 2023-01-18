package com.itwillbs.team1.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.itwillbs.team1.svc.ProductResiService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.ProductBean;

public class ProductRegiProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ProductRegiProAction - 상품 등록 처리 Action");
		ActionForward forward = null;
		

		try {
			String uploadPath = "upload";
			String realPath = request.getServletContext().getRealPath(uploadPath);
			int filesize = 1024 * 1024 * 10;
			MultipartRequest multi = new MultipartRequest(
					request, 
					realPath,
					filesize,
					"UTF-8",
					new DefaultFileRenamePolicy()
			);
			
			if(multi.getParameter("group").equals("book")) {
			// 책 주문 처리 페이지
				ProductBean book = new ProductBean();
				book.setName(multi.getParameter("name"));
				book.setPrice(Integer.parseInt(multi.getParameter("price")));
				book.setDiscount(Integer.parseInt(multi.getParameter("discount")));
				book.setQuantity(Integer.parseInt(multi.getParameter("quantity")));
				book.setDetail(multi.getParameter("detail"));
				book.setImg(multi.getOriginalFileName("img"));
				book.setDetail_img(multi.getOriginalFileName("detail_img"));
				book.setBook_genre(multi.getParameter("book_genre"));
				book.setBook_writer(multi.getParameter("book_writer"));
				book.setBook_publisher(multi.getParameter("book_publisher"));
				// String -> Date 변환 후 setDate 저장
				LocalDate date = LocalDate.parse(multi.getParameter("book_date"));
				book.setBook_date(java.sql.Date.valueOf(date));
				
				// 상세설명 이미지는 선택사항으로 파일명이 null일 경우 널스트링으로 교체
				if(book.getDetail_img() == null) {
					book.setDetail_img("");
				}
				ProductResiService service = new ProductResiService();
//				BookRegiService service = new BookRegiService();
				int insertCount = service.bookregistration(book);
				
				if(insertCount > 0) { // 상품 등록 성공
					forward = new ActionForward();
					forward.setPath("ProductList.ad");
					forward.setRedirect(true);
				} else { // 상품 등록 실패 시 파일 삭제 후 알림창
					File f = new File(realPath, book.getImg());
					File f2 = new File(realPath, book.getDetail_img());
					
					if(f.exists()) {
						f.delete();
					}
					if(f2.exists()) {
						f2.delete();
					}
					
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('상품 등록 실패!')");
					out.println("history.back()");
					out.println("</script>");
					
				}
				

			} else { 
			// 굿즈 주문 페이지	
				ProductBean goods = new ProductBean();
				goods.setName(multi.getParameter("name"));
				goods.setPrice(Integer.parseInt(multi.getParameter("price")));
				goods.setDiscount(Integer.parseInt(multi.getParameter("discount")));
				goods.setQuantity(Integer.parseInt(multi.getParameter("quantity")));
				goods.setDetail(multi.getParameter("detail"));
				goods.setImg(multi.getOriginalFileName("img"));
				goods.setDetail_img(multi.getOriginalFileName("detail_img"));
				// 굿즈 옵션
				String[] strOptName = multi.getParameterValues("option_name");
				String[] strOptNum = multi.getParameterValues("option_qauntity");
				// string[] -> int[] 변환
				
				if(strOptName != null && strOptNum != null) {
					List<String> option_name = new ArrayList<>(List.of(strOptName));
					int[] optQauArr = Stream.of(strOptNum).mapToInt(Integer::parseInt).toArray();
					List<Integer> option_qauntity = Arrays.stream(optQauArr).boxed().collect(Collectors.toList());
					goods.setOption_name(option_name);
					goods.setOption_qauntity(option_qauntity);
				}
				
				if(goods.getDetail_img() == null) {
					goods.setDetail_img("");
				} 
				
				ProductResiService service = new ProductResiService();
				int insertCount = service.goodsregistration(goods);
			
				if(insertCount > 0) { // 상품 등록 성공
					forward = new ActionForward();
					forward.setPath("ProductList.ad?product=" + "G");
					forward.setRedirect(true);
				
				}else { // 상품 등록 실패 시 파일 삭제 후 알림창
					File f = new File(realPath, goods.getImg());
					File f2 = new File(realPath, goods.getDetail_img());
				
					if(f.exists()) {
						f.delete();
					}
				
					if(f2.exists()) {
						f2.delete();
					}
				
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('상품 등록 실패!')");
					out.println("history.back()");
					out.println("</script>");
				}
			}
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return forward;
	}

}
