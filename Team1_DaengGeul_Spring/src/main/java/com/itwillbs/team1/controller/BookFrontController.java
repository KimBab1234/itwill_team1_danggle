package com.itwillbs.team1.controller;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
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

import com.itwillbs.team1.svc.ProductService;
import com.itwillbs.team1.vo.PageInfo;
import com.itwillbs.team1.vo.ProductBean;
import com.itwillbs.team1.svc.S3Service;


@Controller
public class BookFrontController {

	@Autowired
	ProductService service;

	@Autowired
	private S3Service s3Service;


	@GetMapping(value = "/ProductRegiForm.ad")
	public String ProductRegistration(Model model, HttpSession session) {
		System.out.println("상품 등록 폼 페이지");

		String sId = (String)session.getAttribute("sId");
		if(sId == null || sId.equals("") || !sId.equals("admin")) {
			model.addAttribute("msg", "잘못된 접근입니다");
			return "fail_back";
		}

		return "product/product_registration_form";
	}

	@PostMapping(value = "/ProductRegiPro.ad")
	public String registrationPro(Model model, HttpSession session, @ModelAttribute ProductBean product) {
		System.out.println("상품 등록 처리");

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
						s3Service.upload(mFile1, product.getImg(), "product"); ///썸네일 이미지 저장
					}
				}else { // 대표 이미지 + 상세이미지 등록할 경우 사진 저장
					MultipartFile mFile1 = product.getFiles()[0];
					MultipartFile mFile2 = product.getFiles()[1];

					if(!mFile1.getOriginalFilename().equals("") && !mFile2.getOriginalFilename().equals("")) {

						s3Service.upload(mFile1, product.getImg(), "product"); ///썸네일 이미지 저장
						s3Service.upload(mFile2, product.getDetail_img(), "product_detail"); ///상세 이미지 저장

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

		String sId = (String)session.getAttribute("sId");
		if(sId == null || sId.equals("") || !sId.equals("admin")) {
			model.addAttribute("msg", "잘못된 접근입니다");
			return "fail_back";
		}


		int listLimit = 10; 
		int startRow = (pageNum - 1) * listLimit;




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

		if(goodsEndPage > goodsMaxPage) {
			goodsEndPage = goodsMaxPage;
		}
		int GoodsstartRow = (pageNum - 1) * listLimit;
		if(pageNum > goodsEndPage) {
			GoodsstartRow = (goodsEndPage - 1) * listLimit; 
		}

		List<ProductBean> bookList = service.getBookList(searchType, keyword, startRow, listLimit);
		List<ProductBean> goodsList = service.getGoodsList(searchType, keyword, GoodsstartRow, listLimit);

		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		PageInfo goodsPageInfo = new PageInfo(goodslistCount, pageListLimit, goodsMaxPage, startPage, goodsEndPage);

		System.out.println("굿즈목록 확인 : " + goodsList);

		bookList.addAll(goodsList);


		model.addAttribute("goodsPageInfo", goodsPageInfo);
		model.addAttribute("productList", bookList);
		model.addAttribute("pageInfo", pageInfo);

		return "product/productList";


	}

	@GetMapping(value = "/ProductEditForm.ad")
	public String productEdit(HttpSession session, Model model, @RequestParam String product_idx) {
		System.out.println("상품 수정 폼 페이지");

		String sId = (String)session.getAttribute("sId");
		if(sId == null || sId.equals("") || !sId.equals("admin")) {
			model.addAttribute("msg", "잘못된 접근입니다");
			return "fail_back";
		}

		ProductBean product = service.getProduct(product_idx);

		if(product.getProduct_idx().substring(0, 1).equals("B")) {
			model.addAttribute("productType", "book");
		}else {
			model.addAttribute("productType", "goods");
		}
		model.addAttribute("product", product);

		return"product/product_edit_form";
	}

	@GetMapping(value = "//ProductDelete.ad")
	public String deleteProduct(HttpSession session, Model model, @RequestParam String product_idx, @RequestParam(defaultValue = "1") int pageNum,
			@ModelAttribute ProductBean product) throws IOException {
		System.out.println("상품 삭제");
		String sId = (String)session.getAttribute("sId");
		if(sId == null || sId.equals("") || !sId.equals("admin")) {
			model.addAttribute("msg", "잘못된 접근입니다");
			return "fail_back";
		}

		ProductBean fileName = service.selectFileName(product.getProduct_idx());

		int deleteCount = service.removeProduct(product_idx);

		if(deleteCount > 0) {

			s3Service.delete(fileName.getImg(),"product"); // 대표이미지는 무조건 삭제

			if(!fileName.getDetail_img().equals("")) { 
				s3Service.delete(fileName.getDetail_img(),"product_detail");// 상세이미지는 있을 때만 삭제
			}

			if(product_idx.substring(0, 1).equals("B")) {
				return "redirect:/ProductList.ad?pageNum"+pageNum;	
			}else {
				return "redirect:/ProductList.ad?product=G";
			}


		}else {
			model.addAttribute("msg", "상품 삭제 실패!");
			return "fail_back";
		}

	}


	@GetMapping(value = "/RecommendBookList.ad")
	public String recommendBookList(Model model, HttpSession session) {
		System.out.println("추천 도서 목록");

		String sId = (String)session.getAttribute("sId");
		if(sId == null || sId.equals("") || !sId.equals("admin")) {
			model.addAttribute("msg", "잘못된 접근입니다");
			return "fail_back";
		}

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

		String sId = (String)session.getAttribute("sId");
		if(sId == null || sId.equals("") || !sId.equals("admin")) {
			model.addAttribute("msg", "잘못된 접근입니다");
			return "fail_back";
		}

		int deleteCount = service.deleteRocoBook(product_idx);

		if(deleteCount > 0) {
			return "redirect:/RecommendBookList.ad";
		}else {
			model.addAttribute("msg", "상품 등록 실패!");
			return "fail_back";
		}


	}

	static public FTPClient ftpControl(FTPClient ftp, String dir) throws SocketException, IOException {
		ftp.setControlEncoding("UTF-8");
		ftp.connect("iup.cdn1.cafe24.com",21);
		ftp.login("itwillbs3", "itwillbs8030909");
		ftp.changeWorkingDirectory("/www/img/" + dir);
		ftp.setSoTimeout(1000);
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		return ftp;

	}

	@PostMapping(value = "/ProductEditPro.ad")
	public String editPro(Model model, HttpServletRequest request, @RequestParam(defaultValue = "1") int pageNum, @ModelAttribute ProductBean product) {
		System.out.println("상품 수정 처리");

		String product_idx = request.getParameter("product_idx");
		System.out.println("상품 번호 확인 : " + product_idx);

		int updateCount = 0;

		if(product_idx.substring(0, 1).equals("B")) {
			updateCount = service.updateBook(product);
		}else {
			updateCount = service.updateGoods(product);
		}

		if(updateCount > 0) {

			if(product_idx.substring(0, 1).equals("B")) {
				return "redirect:/ProductList.ad?pageNum="+pageNum;
			}else {
				return "redirect:/ProductList.ad?product=G";
			}


		}else{
			model.addAttribute("msg", "상품 수정 실패!");
			return "fail_back";
		}




	}




}
