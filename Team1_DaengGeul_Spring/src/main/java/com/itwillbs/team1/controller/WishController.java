package com.itwillbs.team1.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.team1.svc.WishService;
import com.itwillbs.team1.vo.MemberPageInfo;
import com.itwillbs.team1.vo.WishlistVO;

@Controller
public class WishController {

	@Autowired
	private WishService service;
	
	
	// 찜하기
	@PostMapping(value = "/WishPro.ws")
	@ResponseBody
	public void wishPro(HttpSession session, HttpServletResponse response, String product_idx) {
		String sId = (String)session.getAttribute("sId");
		String searchType = "";
		String keyword = "";
		
		if(sId != null && !sId.equals("")) {
			int registWishCount = service.registWish(product_idx, sId);	
			
			if(registWishCount > 0) {
				int wishlistCount = service.getWishlistCount(sId, searchType, keyword);
				
				session.setAttribute("wishlistCount", wishlistCount);
				try {
					response.getWriter().print(wishlistCount);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} else {
			System.out.println("찜등록 실패");
		}
		
	}
	
	
	// 찜목록
	@GetMapping(value = "/Wishlist.ws")
	public String wishList(HttpSession session, Model model,
			@RequestParam(defaultValue = "") String searchType,
			@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "1") int pageNum) {
		String sId = (String)session.getAttribute("sId");
		
		// ---------------------------------------------------------------------------
		// 페이징 처리를 위한 변수 선언
		int listLimit = 5;
		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산
		// ---------------------------------------------------------------------------
		List<WishlistVO> wishlist = service.wishlist(sId, searchType, keyword, startRow, listLimit);
		// ---------------------------------------------------------------------------
		// 1. 한 페이지에서 표시할 페이지 목록(번호) 개수 계산
		int listCount = service.getWishlistCount(sId, searchType, keyword);
		
		// 2. 한 페이지에서 표시할 페이지 목록 개수 설정
		int pageListLimit = 5;
		
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
		
		MemberPageInfo memberPageInfo = new MemberPageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		// ---------------------------------------------------------------------------
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		model.addAttribute("wishlist", wishlist);
		model.addAttribute("memberPageInfo", memberPageInfo);
		
		
		return "wish/wishlist";
	}
	
	
	// 찜취소
	@PostMapping(value = "/WishDeletePro.ws")
	public String deleteWish(HttpSession session, HttpServletResponse response, Model model,
			@RequestParam(defaultValue = "") String searchType,
			@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "1") int pageNum,
			String[] listArr) {
		String sId = (String)session.getAttribute("sId");
		
		int cancelCount = service.cancelWish(sId, listArr);
		
		if(cancelCount > 0) {
			
			int wishlistCount = service.getWishlistCount(sId, searchType, keyword);
			
			session.setAttribute("wishlistCount", wishlistCount);
			try {
				response.getWriter().print(wishlistCount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return "wish/wishlist";
		
	}
	
}
