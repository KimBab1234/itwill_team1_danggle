package com.itwillbs.team1.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.itwillbs.team1.action.Action;
import com.itwillbs.team1.action.NoticeDeleteProAction;
import com.itwillbs.team1.action.NoticeDetailAction;
import com.itwillbs.team1.action.NoticeListAction;
import com.itwillbs.team1.action.NoticeModifyFormAction;
import com.itwillbs.team1.action.NoticeModifyProAction;
import com.itwillbs.team1.action.NoticeWriteProAction;
import com.itwillbs.team1.action.ProductDeleteAction;
import com.itwillbs.team1.action.ProductEditAction;
import com.itwillbs.team1.action.ProductEditDetailAction;
import com.itwillbs.team1.action.ProductEditListAction;
import com.itwillbs.team1.action.ProductEditProAction;
import com.itwillbs.team1.action.ProductRegiProAction;
import com.itwillbs.team1.action.RecommendBookAction;
import com.itwillbs.team1.action.RecommendBookDeleteAction;
import com.itwillbs.team1.action.RecommendBookListAction;
import com.itwillbs.team1.svc.ProductService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.ProductBean;


@Controller
public class BookFrontController extends HttpServlet {
	
	@Autowired
	ProductService service;
	
	@GetMapping(value = "/ProductRegiForm.ad")
	public String ProductRegistration(Model model, HttpSession session) {
		System.out.println("상품 등록 폼 페이지");
		
		// 관리자 아니면 쫒아내기 추가하기
		
		return "product/product_registration_form";
	}
	
	@PostMapping(value = "/ProductRegiPro.ad")
	public String registrationPro(Model model, HttpSession session, @ModelAttribute ProductBean product) {
		System.out.println("상품 등록 처리");
		
		String uploadDir = "/resources/upload"; // 가상 업로드 경로
		String saveDir = session.getServletContext().getRealPath(uploadDir);
//		System.out.println("실제 업로드 경로 : " + saveDir);
		
		Path path = Paths.get(saveDir);
		try {
			Files.createDirectories(path);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		MultipartFile[] mFiles = product.getFiles();
		
		String originalFileNames = "";
		
		for(MultipartFile mFile : mFiles) {
			String originalFileName = mFile.getOriginalFilename();
//			System.out.println("파일명 확인 : " + originalFileName);
			
			if(!originalFileName.equals("")) {
				originalFileNames += originalFileName + "/";
			}else {
				originalFileNames += "/";
			}
		}
		
		String[] arrFile = originalFileNames.split("/");
//		System.out.println("배열 길이 확인 : " + arrFile.length);
		
		if(arrFile.length == 1) {
			product.setImg(arrFile[0]);
			product.setDetail_img("");
		}else {
			product.setImg(arrFile[0]);
			product.setDetail_img(arrFile[1]);
		}
		
		
//		System.out.println("대표이미지 확인 : " + product.getImg());
//		System.out.println("상세이미지 확인 : " + product.getDetail_img());
		
		int insertCount = service.productRegistration(product);
		
		return "";
	}
	

	@GetMapping(value = "/ProductList.ad")
	public String ProductList() {
		System.out.println("상품 목록");
		
		return "product/productList";
	}
	
	@GetMapping(value = "/RecommendBookList.ad")
	public String recommendBookList() {
		System.out.println("추천 도서 목록");
		
		return "product/recommend_bookList";
	}
	
	

}
