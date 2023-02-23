package com.itwillbs.team1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.team1.svc.OrderService;
import com.itwillbs.team1.svc.ProductService;
import com.itwillbs.team1.svc.ReviewService;
import com.itwillbs.team1.vo.PageInfo;
import com.itwillbs.team1.vo.ProductBean;
import com.itwillbs.team1.vo.ReviewBean;

@Controller
public class ReviewFrontController {

	@Autowired
	ReviewService service;
	
	@Autowired
	ProductService service2;
	
	@Autowired
	OrderService service3;
	
	// ===============================================================================
	// 리뷰쓰기 폼
	@GetMapping(value = "ReviewWriteForm" )
	public String review_write(String product_idx, Model model){
		System.out.println("리뷰쓰기 폼");

		ProductBean product = service2.getProduct(product_idx);

		model.addAttribute("product", product);

		return "review/review_write";
	}

	@PostMapping(value = "ReviewWritePro")
	public String review_writePro(@ModelAttribute ReviewBean review, Model model, HttpSession session) {
		System.out.println("리뷰쓰기 작업");
		
		int insertCount = service.registReview(review);
		System.out.println("리뷰 제목 : " + review.getReview_subject());
		// 등록 성공/실패에 따른 포워딩 작업 수행
		if(insertCount > 0) { // 성공
			
			session.setAttribute("point", (int)session.getAttribute("point")+500);	
			
			return "redirect:ReviewList";
		} else { // 실패
			// "msg" 속성명으로 "글 쓰기 실패!" 메세지 전달 후 fail_back 포워딩
			model.addAttribute("msg", "리뷰 등록 실패!");
			return "fail_back";
		}
		
	}
	
	// ===============================================================================
	// 리뷰 목록
	@RequestMapping(value = "/ReviewList", method = {RequestMethod.GET, RequestMethod.POST})
	public String review_list(HttpSession session, 
								@RequestParam(defaultValue = "") String product_idx,
								@RequestParam(defaultValue = "") String member_id,
								@RequestParam(defaultValue = "") String keyword,
								@RequestParam(defaultValue = "1") int pageNum, 
								Model model
								) {
		System.out.println("리뷰 목록");
		member_id = (String)session.getAttribute("sId");
		
		// 페이징 처리를 위한 변수 선언
		int listLimit = 5; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산
//					System.out.println("startRow = " + startRow);

		List<ReviewBean> reviewList = service.listReview(member_id, product_idx, keyword, startRow, listLimit);
		// 페이징 처리
			// 한 페이지에서 표시할 페이지 목록(번호) 갯수 계산
			// 1. Service 객체의 selectBoardListCount() 메서드를 호출하여 전체 게시물 수 조회
			// => 파라미터 : 검색타입, 검색어   리턴타입 : int(listCount)
			int listCount = service.reviewListCount(member_id, keyword, product_idx);
//					System.out.println("총 게시물 수 : " + listCount);
			
			// 2. 한 페이지에서 표시할 페이지 목록 갯수 설정
			int pageListLimit = 5; // 한 페이지에서 표시할 페이지 목록을 3개로 제한
			
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
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("keyword", keyword);
			model.addAttribute("reviewList", reviewList);
			model.addAttribute("pageInfo", pageInfo);
		
			if(!product_idx.equals("")) {
				return "review/review_list_detail";
			} else {
				return "review/review_list";
			}
	}
	
	// ===============================================================================
	// 리뷰 조회
	@RequestMapping(value = "/ReviewDetail", method = {RequestMethod.GET, RequestMethod.POST})
	public String review_detail(@RequestParam int review_idx,
								@RequestParam(defaultValue = "") String product_idx,
								@RequestParam(defaultValue = "") String member_id,
								@RequestParam(defaultValue = "N") String mine,
								HttpSession session, HttpServletRequest request,
								Model model) {
		System.out.println("리뷰 조회");
		// 상세정보 조회에 필요한 글번호 가져오기
		review_idx= Integer.parseInt(request.getParameter("review_idx"));
		member_id = (String)session.getAttribute("sId");
		
		ReviewBean review =  service.getReview(review_idx, member_id);
		
		review.setReview_like_done(service.selectReviewLike(review_idx, member_id));

		ProductBean product = null;
		
		if(product_idx!=null && product_idx.length()>0) {
			product = service2.getProduct(product_idx);
		}
		
//		System.out.println("review : " + review);
//		System.out.println("product : " + product);
		
		if(review != null) {
			service.increaseReadcount(review_idx);
			
			review.setReview_readcount(review.getReview_readcount() + 1);
		}
		
		model.addAttribute("review", review);
		model.addAttribute("product", product);
		
		if(mine.equals("Y")) { //내가 쓴 리뷰에서 넘어온거면
			return "review/review_view";
		} else {
			return "review/review_view_detail";
		}
	}
	
	// ===============================================================================
	// 리뷰 삭제 폼
	@GetMapping(value = "/ReviewDeleteForm")
	public String review_delete() {
		System.out.println("리뷰 삭제 폼");
		return "review/review_delete";
	}
	
	@PostMapping(value = "/ReviewDeletePro")
	public String review_deletePro( @RequestParam int review_idx,
							@RequestParam(defaultValue = "") String member_id,
							@RequestParam(defaultValue = "") String review_passwd,
							@RequestParam(defaultValue = "1") int pageNum, 
							Model model, 
							HttpSession session) {
		if(service.isReviewWriter(review_idx, review_passwd) != null) { // 일치
			
			int deleteCount = service.removeReview(review_idx);
			
			if(deleteCount > 0) { // 삭제 성공
				
				return "redirect:/ReviewList?pageNum=" + pageNum;
			} else { // 삭제 실패
				model.addAttribute("msg", "게시물 삭제 실패!");
				return "fail_back";
			}
			
		} else { // 불일치
			model.addAttribute("msg", "삭제 권한이 없습니다!");
			return "fail_back";
		}
		
	}
	
	// ===============================================================================
	// 리뷰 수정
	@GetMapping("/ReviewModifyForm")
	public String review_modify(@RequestParam int review_idx,
						@RequestParam(defaultValue = "") String member_id,
						@RequestParam(defaultValue = "") String product_idx,
						Model model, HttpSession session, HttpServletRequest request) {
		System.out.println("리뷰 수정 폼 작업");
		
		review_idx= Integer.parseInt(request.getParameter("review_idx"));
		member_id = (String)session.getAttribute("sId");
		product_idx = request.getParameter("product_idx");
		
		ReviewBean review =  service.getReview(review_idx, member_id);
		ProductBean product = null;
		
		if(product_idx!=null && product_idx.length()>0) {
			product = service2.getProduct(product_idx);
		}
		
		model.addAttribute("review", review);
		model.addAttribute("product", product);
		
		return "review/review_modify";
	}
	
	@PostMapping(value = "/ReviewModifyPro")
	public String review_modifyPro(@ModelAttribute ReviewBean review, 
								@RequestParam(defaultValue = "1") int pageNum,
								@RequestParam(defaultValue = "") String product_idx,
								Model model, HttpSession session, HttpServletRequest request) {
		product_idx = request.getParameter("product_idx");
		if(service.isReviewWriter(review.getReview_idx(), review.getReview_passwd()) != null) {
			int updateCount = service.modifyReview(review);
			
			// 수정 실패 시 "게시물 수정 실패!" 출력 후 이전페이지로 돌아가기
			if(updateCount > 0) {
				return "redirect:/ReviewDetail?review_idx=" + review.getReview_idx() 
				+ "&pageNum=" + pageNum + "&product_idx=" + product_idx;
			} else {
				model.addAttribute("msg", "게시물 수정 실패!");
				return "fail_back";
			}
		} else {
			// 패스워드 일치하지 않을 경우
			// "수정 권한이 없습니다!" 메세지 출력 후 이전페이지로 돌아가기
			model.addAttribute("msg", "수정 권한이 없습니다!");
			return "fail_back";
		}
		
	}
	
	// ===============================================================================
	// 리뷰 좋아요
	@PostMapping(value = "/ReviewLikeUpdate")
	@ResponseBody
	public int review_Like(@RequestParam int review_idx,
	                       @RequestParam(defaultValue = "") String member_id,
	                       @RequestParam(defaultValue = "") String review_like_done,
	                       HttpSession session, 
	                       HttpServletRequest request) {
		
	    System.out.println("리뷰 좋아요");
	    
	    review_idx = Integer.parseInt(request.getParameter("review_idx"));
	    member_id = (String)session.getAttribute("sId");
	    review_like_done = request.getParameter("review_like_done");

	    System.out.println("review_like_done : " + review_like_done);

	    int review_like_count = -1;

	    int insertCount = 0;

	    // review_like_done으로 추가, 삭제 판별
	    if(review_like_done.equals("N")) {
	        insertCount = service.insertReviewLike(review_idx, member_id, review_like_done);
	    }else if(review_like_done.equals("Y")) {
	        insertCount = service.deleteReviewLike(review_idx, member_id, review_like_done);
	    }

	    // 추가, 삭제 등 있으면 업데이트
	    if(insertCount > 0) {
	        int updateCount = service.updateReviewLike(review_like_count, review_idx);
	        if(updateCount > 0) {
	            review_like_count = service.getReviewLikeCount(review_idx);
	        } 
	    }

	    System.out.println("review_like_count : " + review_like_count);
	    return review_like_count;
	}

	

}
