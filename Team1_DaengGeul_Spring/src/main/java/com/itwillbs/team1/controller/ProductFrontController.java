package com.itwillbs.team1.controller;

import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.team1.svc.ProductService;
import com.itwillbs.team1.svc.ReviewListService;
import com.itwillbs.team1.vo.PageInfo;
import com.itwillbs.team1.vo.ProductBean;
import com.itwillbs.team1.vo.ProductListBean;
import com.itwillbs.team1.vo.ReviewBean;

@Controller
public class ProductFrontController {

	////연결된 클래스의 객체를 필요할때마다 자동으로 객체 생성해서 주입해줌
	@Autowired
	private ProductService service;
	
	@GetMapping(value = "/ProductDetail")
	public String ProductDetail(@RequestParam String product_idx, Model model, HttpSession session) {
		System.out.println("책 or 굿즈 상세페이지");

		////로그인 컨버전 완료될때까지 임시로 session에 hong111 저장
		session.setAttribute("sId", "hong111");
		
		ProductBean product = service.getProduct(product_idx); //상품 정보 가져오기
		
		String recentImg = "\\img\\product\\" + product.getImg();
		product.setDetail_img("\\img\\product_detail\\" + product.getDetail_img());
		
		model.addAttribute("product", product); //request에 저장하기 
		model.addAttribute("img", recentImg);

		///최근 본 상품 목록 추가 시작

		if(session.getAttribute("recentImgList")==null) {
			session.setAttribute("recentImgList", new LinkedHashSet<>());
			session.setAttribute("recentIdxList", new LinkedHashSet<>());
		}

		@SuppressWarnings("unchecked")
		LinkedHashSet<String> recentImgList = (LinkedHashSet<String>)session.getAttribute("recentImgList");
		@SuppressWarnings("unchecked")
		LinkedHashSet<String> recentIdxList = (LinkedHashSet<String>)session.getAttribute("recentIdxList");

		if(recentImgList.contains(recentImg)) {
			recentImgList.remove(recentImg);
			recentIdxList.remove(product_idx);
		}
		recentImgList.add(recentImg);
		recentIdxList.add(product_idx);
		///최근 본 상품 목록 추가 끝


//		////리뷰 목록 가져오기
//		int listLimit = 10, pageListLimit=10; // 한 페이지에서 표시할 게시물 목록 10개 제한
//		int pageNum = 1;
//		ReviewListService rlService = new ReviewListService();
//		int listCount = rlService.getReviewdListCount("", "", product_idx);
//		int maxPage = listCount / listLimit + (listCount % listLimit == 0 ? 0 : 1);
//		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
//		int endPage = startPage + pageListLimit - 1 ;
//		if(endPage > maxPage){
//			endPage = maxPage;
//		}
//		List<ReviewBean> reviewList = rlService.getReviewList("", "", product_idx, 0, 0);
//		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
//		
//		model.addAttribute("reviewList", reviewList);
//		model.addAttribute("pageInfo", pageInfo);

		return "order/product_detail";
	}

	@GetMapping(value = "/ProductList")
	public String ProductList(@ModelAttribute ProductListBean listSort, Model model) {
		System.out.println("상품 목록");


		////책인지 굿즈인지 구분 필요함
		String type = listSort.getType();
		String keyword = listSort.getKeyword(); //장르, 검색시 들어옴
		String[] typeArr= type.split("_");
		String product_type = typeArr[0]; ///첫글자는 책인지 굿즈인지 구분
		String order = typeArr.length>1 ? typeArr[1]:"new"; //혹시 type에 한글자만 들어왔을 경우 defulat로 new
		String sort = "";

		//장르, 검색일때만 keyword 있고 이때만 type split이 3개로 됨
		if(keyword!=null) {
			sort=typeArr[2];
		} else if(type.equals("B_recomm")) {
			sort="recomm";
		} else if(type.equals("B_disc")) {
			sort="disc";
		}

		//장르, 검색할때만 갯수가 변하니까 sort로 지정
		int totalProduct = service.getProductListCount(product_type, sort, keyword); 

		///갯수가 0개면 후속 작업 없이 바로 리턴
		if(totalProduct==0) {
			return "order/product_list";
		}

		////페이징 처리
		int pageNum = 1;
		if(listSort.getPageNum()!=null) {
			pageNum = Integer.parseInt(listSort.getPageNum());
		}

		int pageListLimit = 3;
		int startPage = (int)((pageNum-1)/pageListLimit)*pageListLimit+1;
		int pageProductCount = 12; ///1페이지당 출력할 상품갯수
		int maxPage = (totalProduct-1)/pageProductCount+1;
		int pageStartRow = (pageNum-1) * pageProductCount;

		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageListLimit(pageListLimit); //화면 밑에 보여줄 페이지 번호 갯수
		pageInfo.setStartPage(startPage);  //보여줄 페이지 번호 첫번호
		pageInfo.setEndPage(startPage+pageListLimit-1>maxPage? maxPage : startPage+pageListLimit-1); //보여줄 페이지 번호 끝번호
		pageInfo.setMaxPage(maxPage);


		List<ProductBean> productList = service.getProductList(product_type, order+"_"+sort, keyword, pageStartRow,pageProductCount);

		model.addAttribute("productList", productList);
		model.addAttribute("pageInfo", pageInfo);

		String title="";

		if(type.contains("recomm")) {
			title="운영자 추천 도서";
		} else if(type.contains("genre")) {

			switch (keyword) {
			case "humanities":
				title = "인문";
				break;
			case "novel":
				title = "소설";
				break;
			case "poem":
				title = "시";
				break;
			case "history":
				title = "역사";
				break;
			case "religion":
				title = "종교";
				break;
			case "society":
				title = "사회";
				break;
			case "science":
				title = "과학";
				break;
			case "self_improvement":
				title = "자기계발";
				break;
			case "kids":
				title = "어린이";
				break;
			case "health":
				title = "건강";
				break;
			case "reference":
				title = "참고서";
				break;
			}

			title="장르 : "+title;

		} else if(type.contains("disc")) {
			title="할인 중인 도서";
		} else if(type.substring(0,1).equals("G")) {
			title="굿즈샵";
		}  else if(typeArr.length==2) {
			if(type.contains("B_best") )
				title="베스트셀러";
		} else if(type.contains("search")) {
			title="검색 결과 : " + totalProduct +"건";
		} 
		model.addAttribute("title", title);
		model.addAttribute("type", type);
		model.addAttribute("pageNum", pageNum);

		return "order/product_list";
	}

}
