package com.itwillbs.team1_final.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

import com.itwillbs.team1_final.svc.AccService;
import com.itwillbs.team1_final.vo.AccVO;

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
	public String accList() {
		System.out.println("거래처리스트");
		return "acc/acc_list";
	}
	
	// 거래처 리스트 작업 수행 요청
	@ResponseBody
	@GetMapping("/SearchAccList")
	public void searchAccList(@RequestParam(defaultValue = "") String searchType,
			@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "1") int pageNum,
			Model model,
			HttpServletResponse response) {
		// 페이징 처리를 위한 변수 선언
		int listLimit = 10; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산

		List<AccVO> accList = service.getAccList();
		// 자바 데이터를 JSON 형식으로 변환하기
				// => org.json 패키지의 JSONObject 클래스를 활용하여 JSON 객체 1개를 생성하고
				//    JSONArray 클래스를 활용하여 JSONObject 객체 복수개에 대한 배열 생성
				// 0. JSONObject 객체 복수개를 저장할 JSONArray 클래스 인스턴스 생성
				JSONArray jsonArray = new JSONArray();
				
				// 1. List 객체 크기만큼 반복
				for(AccVO acc : accList) {
					// 2. JSONObject 클래스 인스턴스 생성
					// => 파라미터 : VO(Bean) 객체(멤버변수 및 Getter/Setter, 기본생성자 포함)
					JSONObject jsonObject = new JSONObject(acc);
					System.out.println(jsonObject);
					
					// 참고. 저장되어 있는 JSON 데이터를 꺼낼 수도 있다! - get() 메서드 활용
//					System.out.println(jsonObject.get("TEL"));
					
					// 3. JSONArray 객체의 put() 메서드를 호출하여 JSONObject 객체 추가
					jsonArray.put(jsonObject);
				}
				
				System.out.println(jsonArray);
				// => JSONObject 복수개가 배열 형태로 JSONArray 객체에 저장되어 있음
				// 또한, JSONArray 객체에서 JSONObject 객체를 꺼낼 수도 있다!
//				JSONObject jsonObject = (JSONObject)jsonArray.get(0); // 첫번째 배열에서 꺼내기
				// => 이 때, 리턴타입이 Object 타입이므로 JSONObject 타입 형변환 필요
//				System.out.println(jsonObject.get("board_pass"));
				
				try {
					// 생성된 JSON 객체를 활용하여 응답 데이터를 직접 생성 후 웹페이지에 출력
					// response 객체의 setCharacterEncoding() 메서드로 출력 데이터 인코딩 지정 후
					// response 객체의 getWriter() 메서드로 PrintWriter 객체를 리턴받아
					// PrintWriter 객체의 print() 메서드를 호출하여 응답데이터 출력
					response.setCharacterEncoding("UTF-8");
					response.getWriter().print(jsonArray); // toString() 생략됨
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
	
		// 거래처 상세보기
		@GetMapping("/AccView")
		public String accView(Model model, @RequestParam String BUSINESS_NO) {
			
			AccVO acc = service.accDetail(BUSINESS_NO);

			model.addAttribute("acc",acc);
			
			return "acc/acc_detail";
		}
		
		// 거래처 수정
		@GetMapping("/AccModify")
		public String accModify(@ModelAttribute AccVO acc, Model model ) {
			
			
//			int updateCount = service.modifyMemberInfo();

//			// 수정 성공/실패에 따른 포워딩 작업 수행
//			if(updateCount > 0) {
//				return "redirect:/";	
//			} else {
//				model.addAttribute("msg", "회원수정 실패!");
//				return "fail_back";
//			}
			
			return "acc/acc_modify";
		}
	
}













