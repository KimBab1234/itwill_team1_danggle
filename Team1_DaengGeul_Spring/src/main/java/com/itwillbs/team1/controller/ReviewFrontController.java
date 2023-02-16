package com.itwillbs.team1.controller;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.team1.action.Action;
import com.itwillbs.team1.action.ReviewDeleteProAction;
import com.itwillbs.team1.action.ReviewDetailAction;
import com.itwillbs.team1.action.ReviewLikeUpdateAction;
import com.itwillbs.team1.action.ReviewListAction;
import com.itwillbs.team1.action.ReviewModifyFormAction;
import com.itwillbs.team1.action.ReviewModifyProAction;
import com.itwillbs.team1.action.ReviewWriteFormAction;
import com.itwillbs.team1.action.ReviewWriteProAction;
import com.itwillbs.team1.svc.ProductService;
import com.itwillbs.team1.svc.ReviewService;
import com.itwillbs.team1.vo.ActionForward;
import com.itwillbs.team1.vo.PageInfo;
import com.itwillbs.team1.vo.ProductBean;
import com.itwillbs.team1.vo.ReviewBean;

@Controller
public class ReviewFrontController {

	@Autowired
	ReviewService service;
	
	@Autowired
	ProductService service2;
	
	// ===============================================================================
	// 리뷰쓰기 폼
	@GetMapping(value = "ReviewWriteForm" )
	public String review_write(String product_idx, Model model){
		System.out.println("리뷰쓰기 폼");

		ProductBean product = service2.getProduct(product_idx);

		model.addAttribute("product", product);

		return "review/review_write";
	}

	@PostMapping(value = "ReviewWritePro.re")
	public String review_writePro(@ModelAttribute ReviewBean review, Model model, HttpSession session) {
		System.out.println("리뷰쓰기 작업");
		
		int insertCount = service.registReview(review);
		System.out.println("리뷰 제목 : " + review.getReview_subject());
		// 등록 성공/실패에 따른 포워딩 작업 수행
		if(insertCount > 0) { // 성공

			return "redirect:ReviewList.re";
		} else { // 실패
			// "msg" 속성명으로 "글 쓰기 실패!" 메세지 전달 후 fail_back 포워딩
			model.addAttribute("msg", "리뷰 등록 실패!");
			return "fail_back";
		}
		
	}
	
	@GetMapping(value = "/ReviewList.re")
	public String review_list(
								@RequestParam(defaultValue = "") String product_idx,
								@RequestParam(defaultValue = "") String member_id,
								@RequestParam(defaultValue = "") String keyword,
								@RequestParam(defaultValue = "1") int pageNum, 
								Model model) {
		System.out.println("리뷰 조회");
		
		// 페이징 처리를 위한 변수 선언
		int listLimit = 10; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산
//					System.out.println("startRow = " + startRow);
		
		List<ReviewBean> reviewList = service.listReview(member_id, product_idx, keyword, startRow, listLimit);
		
		// 페이징 처리
			// 한 페이지에서 표시할 페이지 목록(번호) 갯수 계산
			// 1. Service 객체의 selectBoardListCount() 메서드를 호출하여 전체 게시물 수 조회
			// => 파라미터 : 검색타입, 검색어   리턴타입 : int(listCount)
			int listCount = service.reviewListCount(keyword);
//					System.out.println("총 게시물 수 : " + listCount);
			
			// 2. 한 페이지에서 표시할 페이지 목록 갯수 설정
			int pageListLimit = 10; // 한 페이지에서 표시할 페이지 목록을 3개로 제한
			
			// 3. 전체 페이지 목록 수 계산
			int maxPage = listCount / listLimit 
							+ (listCount % listLimit == 0 ? 0 : 1); 
			
			// 4. 시작 페이지 번호 계산
			int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
			
			// 5. 끝 페이지 번호 계산
			int endPage = startPage + pageListLimit - 1;
			
			// 6. 만약, 끝 페이지 번호(endPage)가 전체(최대) 페이지 번호(maxPage) 보다
			//    클 경우, 끝 페이지 번호를 최대 페이지 번호로 교체
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			
			// PageInfo 객체 생성 후 페이징 처리 정보 저장
			PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
			// ---------------------------------------------------------------------------
			// 게시물 목록 객체(boardList) 와 페이징 정보 객체(pageInfo)를 Model 객체에 저장
			model.addAttribute("reviewList", reviewList);
			model.addAttribute("pageInfo", pageInfo);
		
		return "review/review_list";
	}
	
	
//	if(command.equals("/ReviewWriteForm.re")) {
//		System.out.println("리뷰쓰기 폼");
//		action= new ReviewWriteFormAction();
//		forward = action.execute(request, response);	
//
//	}else if(command.equals("/ReviewWritePro.re")) {
//		System.out.println("리뷰쓰기 작업");
//		action= new ReviewWriteProAction();
//		forward = action.execute(request, response);
//
//	}else if(command.equals("/ReviewList.re")) {
//		System.out.println("리뷰 목록 작업");
//		action = new ReviewListAction();
//		forward = action.execute(request, response);
//
//	}else if(command.equals("/ReviewDetail.re")) {
//		System.out.println("리뷰 디테일 작업");
//		action = new ReviewDetailAction();
//		forward = action.execute(request, response);
//
//	}else if(command.equals("/ReviewDeleteForm.re")) {
//		System.out.println("리뷰 삭제 폼 작업");
//		forward = new ActionForward();
//		forward.setPath("review/review_delete.jsp");
//		forward.setRedirect(false);
//
//	}else if(command.equals("/ReviewDeletePro.re")) {
//		System.out.println("리뷰 삭제 프로 작업");
//		action = new ReviewDeleteProAction();
//		forward = action.execute(request, response);
//
//	} else if(command.equals("/ReviewModifyForm.re")) {
//		System.out.println("리뷰 수정 폼 작업");
//		action = new ReviewModifyFormAction();
//		forward = action.execute(request, response);
//
//	} else if(command.equals("/ReviewModifyPro.re")) {
//		System.out.println("리뷰 수정 프로 작업");
//		action = new ReviewModifyProAction();
//		forward = action.execute(request, response);
//
//	} else if(command.equals("/ReviewLikeUpdate.re")) {
//		System.out.println("리뷰 좋아요 작업");
//		action = new ReviewLikeUpdateAction();
//		forward = action.execute(request, response);
//	}	




}
