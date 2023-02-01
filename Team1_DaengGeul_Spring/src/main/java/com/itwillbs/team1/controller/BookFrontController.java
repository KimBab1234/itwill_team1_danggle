package com.itwillbs.team1.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.itwillbs.team1.vo.PageInfo;
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
				String uuid = UUID.randomUUID().toString();
				originalFileNames += uuid + "_" + originalFileName + "/";
				
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
		
		int insertCount = service.productRegistration(product);
		
		if(insertCount > 0) {
			try {
				if(arrFile.length == 1) { // 대표 이미지만 등록할 경우 사진 저장
					MultipartFile mFile1 = product.getFiles()[0];
					
					if(!mFile1.getOriginalFilename().equals("")) {
						mFile1.transferTo(new File(saveDir, product.getImg()));
					}
				}else { // 대표 이미지 + 상세이미지 등록할 경우 사진 저장
					MultipartFile mFile1 = product.getFiles()[0];
					MultipartFile mFile2 = product.getFiles()[1];
					
					if(!mFile1.getOriginalFilename().equals("") && !mFile2.getOriginalFilename().equals("")) {
						mFile1.transferTo(new File(saveDir, product.getImg()));
						mFile2.transferTo(new File(saveDir, product.getDetail_img()));
					}
				}
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "redirect:/ProductList.ad";
		}else {
			model.addAttribute("msg", "상품 등록 실패!");
			return "fail_back";
		}

	}
	

	@GetMapping(value = "/ProductList.ad")
	public String ProductList(@RequestParam(defaultValue = "") String searchType,
			@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "1") int pageNum,
			Model model, HttpServletResponse response, HttpSession session) {
		System.out.println("상품 목록");
		
		// 관리자 외 쫒아내기 추가 필요
		
		
		int listLimit = 10; 
		int startRow = (pageNum - 1) * listLimit;

		List<ProductBean> bookList = service.getBookList(searchType, keyword, startRow, listLimit);
		List<ProductBean> goodsList = service.getGoodsList(searchType, keyword, startRow, listLimit);
		
		int goodslistCount = service.getGoodsListCount(searchType, keyword); // 굿즈 전체 게시물 조회
		int listCount = service.getBookListCount(searchType, keyword); // 책 전체 게시물 조회
		
        int pageListLimit = 10; 
        
        // 책 페이징 처리
        int maxPage = listCount / listLimit 
        			+ (listCount % listLimit == 0 ? 0 : 1); 

        int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;

        int endPage = startPage + pageListLimit - 1;
        
        int goodsEndPage = startPage + pageListLimit - 1;
        
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 굿즈 페이징처리
		
		int goodsMaxPage = goodslistCount / listLimit 
    			+ (goodslistCount % listLimit == 0 ? 0 : 1); 
		System.out.println("마지막 페이지 확인 : " + goodsMaxPage);
		System.out.println("end 페이지 확인 : " + goodsEndPage);
	
		if(goodsEndPage > goodsMaxPage) {
			goodsEndPage = goodsMaxPage;
		}
		System.out.println("굿즈 end 페이지 : " + goodsEndPage);
//		PageInfo goodsPageInfo = new PageInfo(goodslistCount, pageListLimit, goodsMaxPage, startPage, goodsEndPage, endPage, maxPage);
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		PageInfo goodsPageInfo = new PageInfo(goodslistCount, pageListLimit, goodsMaxPage, startPage, goodsEndPage);
		
		bookList.addAll(goodsList);
		
		model.addAttribute("goodsPageInfo", goodsPageInfo);
		model.addAttribute("productList", bookList);
		model.addAttribute("pageInfo", pageInfo);
		
		return "product/productList";
		

	}

	
	@GetMapping(value = "/RecommendBookList.ad")
	public String recommendBookList() {
		System.out.println("추천 도서 목록");
		
		// 관리자 외 쫓아내기 추가
		
		return "product/recommend_bookList";
	}
	
	
	@ResponseBody 
	@GetMapping(value = "/RecommendJson")
	public String listJson(Model model, HttpServletResponse response) {
		
		
		ArrayList<ProductBean> bookList = service.getrecoBookList();
		JSONArray jsonArray = new JSONArray();
		
		for(ProductBean book : bookList) {
			JSONObject jsonObject = new JSONObject(book);

			jsonArray.put(jsonObject);
		}
		
//		System.out.println(jsonArray);
		
		return jsonArray.toString();
		
	}
	
	@PostMapping(value = "/RecommendBook.ad")
	public String recoBookRegi(Model model, HttpSession session, @ModelAttribute ProductBean product, HttpServletRequest request) {
		System.out.println("추천 도서 등록");
		
		String[] idxArr = request.getParameterValues("recoCheck");
		
		product.setReco_idx(idxArr);
		
		int insertCount = service.recoBookregi(product);
		
		if(insertCount > 0) {
			
			model.addAttribute("result", true);
			return "fail_back";
			
		}else {
			model.addAttribute("msg", "상품 등록 실패!");
			return "fail_back";
		}
		
	}
	
	@GetMapping(value = "/RecommendBookDelete.ad")
	public String recoBookDelete(@RequestParam String product_idx, Model model, HttpSession session) {
		System.out.println("추천 도서 삭제");
		
		// 관리자 외 쫓아내기 추가 필요
		
		int deleteCount = service.deleteRocoBook(product_idx);
		
		if(deleteCount > 0) {
			return "redirect:/RecommendBookList.ad";
		}else {
			model.addAttribute("msg", "상품 등록 실패!");
			return "fail_back";
		}
		
		
	}

}
