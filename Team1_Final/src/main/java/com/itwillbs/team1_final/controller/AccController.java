package com.itwillbs.team1_final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.team1_final.svc.AccService;
import com.itwillbs.team1_final.vo.AccVO;
import com.itwillbs.team1_final.vo.PageInfo;

@Controller
public class AccController {

	@Autowired
	private AccService service;

	// 거래처 등록 폼 요청
	@GetMapping(value = "/AccRegist")
	public String accRegist() {
		System.out.println("거래처 등록");
		return "acc/acc_regist";
	}

	// 거래처 등록 작업 수행 요청
	@PostMapping(value = "/accregistPro")
	public String accRegistPro(@ModelAttribute AccVO acc, Model model) {
		System.out.println(acc);

		int insertCount = service.insertAcc(acc);

		if(insertCount > 0) {

			return "redirect:/";
		} else {
			model.addAttribute("msg", "거래처 등록 실패!");
			return "fail_back";
		}

	}

	// 거래처 리스트 요청
	@GetMapping("/AccList")
	public String accList(@RequestParam(defaultValue = "") String searchType,
		@RequestParam(defaultValue = "") String keyword,
		@RequestParam(defaultValue = "1") int pageNum,
		Model model) {
		
		System.out.println("거래처리스트");
		int listLimit = 10; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산
		System.out.println(startRow);
		
		List<AccVO> acc = service.getAccList(searchType, keyword, startRow, listLimit);
		// -------------------- 페이징 처리 ------------------------------
		int listCount = service.getAccListCount(searchType, keyword);
		int pageListLimit = 10; // 한 페이지에서 표시할 페이지 목록을 3개로 제한
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
		model.addAttribute("acc", acc);
		model.addAttribute("pageInfo", pageInfo);
		return "acc/acc_list";
	}

	// 거래처 리스트 작업 수행 요청
	//	@ResponseBody
	//	@GetMapping("/SearchAccList")
	//	public void searchAccList(@RequestParam(defaultValue = "") String searchType,
	//			@RequestParam(defaultValue = "") String keyword,
	//			@RequestParam(defaultValue = "1") int pageNum,
	//			Model model,
	//			HttpServletResponse response) {
	//
	//		List<AccVO> accList = service.getAccList();
	//				JSONArray jsonArray = new JSONArray();
	//				
	//				// 1. List 객체 크기만큼 반복
	//				for(AccVO acc : accList) {
	//					JSONObject jsonObject = new JSONObject(acc);
	//					
	//					jsonArray.put(jsonObject);
	//					
	//					System.out.println(jsonObject.get("TEL"));
	//				}
	//				
	//				System.out.println(jsonArray);
	//				
	//				try {
	//					response.setCharacterEncoding("UTF-8");
	//					response.getWriter().print(jsonArray); // toString() 생략됨
	//				} catch (IOException e) {
	//					e.printStackTrace();
	//				}
	//			}

	// 거래처 상세보기
	@GetMapping("/AccView")
	public String accView(Model model, @RequestParam String BUSINESS_NO) {

		AccVO acc = service.accDetail(BUSINESS_NO);
		// 주소 분리해서 넣어주기
		acc.setADDR1(acc.getADDR().split(",")[0]);
		acc.setADDR2(acc.getADDR().split(",")[1]);
		acc.setBUSINESS_NO1(acc.getBUSINESS_NO().split("-")[0]);
		acc.setBUSINESS_NO2(acc.getBUSINESS_NO().split("-")[1]);
		acc.setBUSINESS_NO3(acc.getBUSINESS_NO().split("-")[2]);
		
		if(!acc.getEMAIL().equals("") && acc.getEMAIL().contains("@")) {
			acc.setEMAIL1(acc.getEMAIL().split("@")[0]);
			acc.setEMAIL2(acc.getEMAIL().split("@")[1]);
		}
		if(!acc.getTEL().equals("")) {
			acc.setTEL1(acc.getTEL().split("-")[0]);
			acc.setTEL2(acc.getTEL().split("-")[1]);
			acc.setTEL3(acc.getTEL().split("-")[2]);
		}
		if(!acc.getMOBILE_NO().equals("")) {
			acc.setMOBILE_NO1(acc.getMOBILE_NO().split("-")[0]);
			acc.setMOBILE_NO2(acc.getMOBILE_NO().split("-")[1]);
			acc.setMOBILE_NO3(acc.getMOBILE_NO().split("-")[2]);
		}
		if(!acc.getMAN_EMAIL().equals("")) {
			acc.setMAN_EMAIL1(acc.getMAN_EMAIL().split("@")[0]);
			acc.setMAN_EMAIL2(acc.getMAN_EMAIL().split("@")[1]);
		}
		if(!acc.getMAN_TEL().equals("")) {
			acc.setMAN_TEL1(acc.getMAN_TEL().split("-")[0]);
			acc.setMAN_TEL2(acc.getMAN_TEL().split("-")[1]);
			acc.setMAN_TEL3(acc.getMAN_TEL().split("-")[2]);
		}
		
		model.addAttribute("acc",acc);

		return "acc/acc_detail";
	}

	// 거래처 수정 폼 요청
	@GetMapping("/AccModify")
	public String accModify(@ModelAttribute AccVO acc, Model model,@RequestParam String BUSINESS_NO ) {

		acc = service.accDetail(BUSINESS_NO);
		// 주소 분리해서 넣어주기
		acc.setADDR1(acc.getADDR().split(",")[0]);
		acc.setADDR2(acc.getADDR().split(",")[1]);
		acc.setBUSINESS_NO1(acc.getBUSINESS_NO().split("-")[0]);
		acc.setBUSINESS_NO2(acc.getBUSINESS_NO().split("-")[1]);
		acc.setBUSINESS_NO3(acc.getBUSINESS_NO().split("-")[2]);
		
		if(!acc.getEMAIL().equals("")) {
			acc.setEMAIL1(acc.getEMAIL().split("@")[0]);
			acc.setEMAIL2(acc.getEMAIL().split("@")[1]);
		}
		if(!acc.getTEL().equals("")) {
			acc.setTEL1(acc.getTEL().split("-")[0]);
			acc.setTEL2(acc.getTEL().split("-")[1]);
			acc.setTEL3(acc.getTEL().split("-")[2]);
		}
		if(!acc.getMOBILE_NO().equals("")) {
			acc.setMOBILE_NO1(acc.getMOBILE_NO().split("-")[0]);
			acc.setMOBILE_NO2(acc.getMOBILE_NO().split("-")[1]);
			acc.setMOBILE_NO3(acc.getMOBILE_NO().split("-")[2]);
		}
		if(!acc.getMAN_EMAIL().equals("")) {
			acc.setMAN_EMAIL1(acc.getMAN_EMAIL().split("@")[0]);
			acc.setMAN_EMAIL2(acc.getMAN_EMAIL().split("@")[1]);
		}
		if(!acc.getMAN_TEL().equals("")) {
			acc.setMAN_TEL1(acc.getMAN_TEL().split("-")[0]);
			acc.setMAN_TEL2(acc.getMAN_TEL().split("-")[1]);
			acc.setMAN_TEL3(acc.getMAN_TEL().split("-")[2]);
		}
		model.addAttribute("acc",acc);

		return "acc/acc_modify";
	}

	// 거래처 수정 작업 요청
	@PostMapping("/AccModifyPro")
	public String AccModifyPro(@ModelAttribute AccVO acc, Model model) {

		int accModifyCount = service.accModify(acc);

		
		
		if(accModifyCount > 0) {
			return "redirect:/AccView?BUSINESS_NO="+ acc.getBUSINESS_NO();	
		} else {
			model.addAttribute("msg", "거래처삭제 실패!");
			return "fail_back";
		}
	}

	// 거래처 삭제
	@GetMapping("/AccDeletePro")
	public String accDelete(@ModelAttribute AccVO acc, Model model) {

		int deleteCount = service.accDelete(acc);

		if(deleteCount > 0) {
			return "redirect:/AccList";	
		} else {
			model.addAttribute("msg", "거래처삭제 실패!");
			return "fail_back";
		}

	}
}













