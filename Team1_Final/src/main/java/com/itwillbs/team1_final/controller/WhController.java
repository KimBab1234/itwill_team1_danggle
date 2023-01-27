package com.itwillbs.team1_final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.team1_final.svc.WhService;
import com.itwillbs.team1_final.vo.PageInfo;
import com.itwillbs.team1_final.vo.WhVO;

@Controller
public class WhController {
	
	@Autowired
	private WhService service;
	
	@GetMapping(value="/WhList")
	public String list(Model model,
				@RequestParam(defaultValue = "") String searchType,
				@RequestParam(defaultValue = "") String keyword,
				@RequestParam(defaultValue = "1") int pageNum){
		
		int listLimit = 10;
		int startRow = (pageNum -1) * listLimit;
		
		List<WhVO> whList = service.getWhList(searchType,keyword,startRow,listLimit);
		
		int listCount = service.getWhListCount(searchType,keyword);
		
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
		// 게시물 목록 객체와 페이징 정보 객치를 Model객체에 저장
		model.addAttribute("whList", whList);
		model.addAttribute("pageInfo", pageInfo);
		
		
		return "wh/list";
	}
}
