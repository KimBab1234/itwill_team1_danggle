package com.itwillbs.team1_final.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

		////dummy데이터
		whList.add(0, new WhVO());
		
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
	
	@GetMapping(value="/WhRegistForm")
	public String regist(HttpSession session, Model model) {
//		String sId = (String)session.getAttribute("sId");
//		if(sId == null || sId.equals("")) {
//			model.addAttribute("msg", "로그인 필수");
//			return "fail_back";
//		}
		
		return "wh/regist";
		
	}
	
	@ResponseBody
	@PostMapping(value ="/codeCheck")
	public void codeCheck(String WH_CD, HttpServletResponse response) {
		
		System.out.println("창고코드 : " + WH_CD);
		if(WH_CD != null) {
			int count = service.codeCheck(WH_CD);
			try {
				response.getWriter().print(count);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	@PostMapping(value="/WhRegistPro")
	public String registPro(@ModelAttribute WhVO wh, Model model, HttpSession session) {
//		String sId = (String)session.getAttribute("sId");
//		if(sId == null || sId.equals("")) {
//			model.addAttribute("msg","로그인 필수!");
//			return "fail_back";
//		}
		
		int inserCount = service.registWh(wh);
		return "redirect:/WhList";
	}
	
	@GetMapping(value="/WhDetail")
	public String detail(Model model, @RequestParam String WH_CD) {
		WhVO wh = service.getWh(WH_CD);
		
		model.addAttribute("wh", wh);
		return "wh/view";
	}
	
	@GetMapping(value = "/WhDeleteForm")
	public String delete() {
		
		return "wh/delete";
	}
	
	@PostMapping(value="/WhDeletePro")
	public String deletePro(@ModelAttribute WhVO wh,@RequestParam(defaultValue = "1")int pageNum,
								Model model, HttpSession session) {
		int deleteCount = service.removeWh(wh.getWH_CD());
		if(deleteCount > 0) {
			
			return "redirect:/WhList?pageNum="+pageNum;
		} else {
			model.addAttribute("msg", "게시물 삭제 실패!");
			return "fail_back";
			
		}
	}
	
	@GetMapping(value="/WhModifyForm")
	public String modify(@ModelAttribute WhVO wh, Model model, HttpSession session, @RequestParam String WH_CD) {
//		String sId = (String)session.getAttribute("sId");
//		if(sId == null || sId.equals("")) {
//			model.addAttribute("msg","로그인 필수!");
//			return "fail_back";
//		}
		wh = service.getWh(WH_CD);
		
		model.addAttribute("wh", wh);
		
		
		return "wh/modify";
	}
	
	@PostMapping(value = "/WhModifyPro")
	public String modifyPro(@ModelAttribute WhVO wh,
							Model model, HttpSession session) {
		
		System.out.println(wh);
		int modifyCount = service.modifyWh(wh);
		if(modifyCount > 0) {
			
			return "redirect:/WhDetail?WH_CD="+wh.getWH_CD();
		} else {
			model.addAttribute("msg", "게시물 수정 실패!");
			return "fail_back";
			
		}
		
	}
	
	@GetMapping(value = "/WhAreaRegist")
	public String WhAreaRegist(Model model, HttpSession session, WhVO wh,@RequestParam String WH_CD) {
//								,@RequestParam String WH_AREA,@RequestParam int WH_AREA_CD) {
		
//		String sId = (String)session.getAttribute("sId");
//		if(sId == null || sId.equals("")) {
//			model.addAttribute("msg", "로그인 필수");
//			return "fail_back";
//		}
		//select 하는 거 만들기 
		List<WhVO> WhAreaList = service.selectWhArea(WH_CD);
		
		return "wh/area_regist";
	}
	
	@PostMapping(value = "/WhAreaRegistPro")
	public String WhAreaRegistPro(@ModelAttribute WhVO wh, Model model, HttpSession session) {
//		String sId = (String)session.getAttribute("sId");
//		if(sId == null || sId.equals("")) {
//			model.addAttribute("msg", "로그인 필수");
//			return "fail_back";
//		}	
		
		int insertCount = service.registWhArea(wh);
		
		
		return "redirect:/WhList";
	}
	
	@GetMapping(value = "/WhLocationRegist")
	public String WhLocationRegist(Model model, HttpSession session, WhVO wh, @RequestParam int WH_AREA_CD) {
//									@RequestParam String WH_LOC_IN_AREA,@RequestParam String WH_LOC_IN_AREA_CD) {
//		String sId = (String)session.getAttribute("sId");
//		if(sId == null || sId.equals("")) {
//			model.addAttribute("msg", "로그인 필수");
//			return "fail_back";
//		}
		
		List<WhVO> WhLocationList =service.getWhLocationList(WH_AREA_CD);
		
		return "wh/location_regist";
	}
	
	@PostMapping(value = "/WhLocationRegistPro")
	public String WhLocationRegistPro(@ModelAttribute WhVO wh, Model model, HttpSession session) {
		
//		String sId = (String)session.getAttribute("sId");
//		if(sId == null || sId.equals("")) {
//			model.addAttribute("msg", "로그인 필수");
//			return "fail_back";
//		}
		
		int insertCount = service.registWhLocation(wh);
		
		return "redirect:/WhList";
	}
	
}
