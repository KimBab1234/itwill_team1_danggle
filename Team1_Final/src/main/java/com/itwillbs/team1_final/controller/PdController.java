package com.itwillbs.team1_final.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
import org.springframework.web.multipart.MultipartFile;

import com.itwillbs.team1_final.svc.PdService;
import com.itwillbs.team1_final.vo.PdVO;


@Controller
public class PdController {

	@Autowired
	private PdService service;
	
	// ===============================================================================
	// 품목 등록
	@GetMapping(value = "/PdRegist")
	public String pdRegist() {
		System.out.println("품목 등록");
		return "pd/pd_regist";
	}
	
	@PostMapping(value = "/PdRegistPro")
	public String pdRegistPro(@ModelAttribute PdVO product, Model model, HttpSession session) {
		System.out.println(product);
	
		String uploadDir = "/resources/upload"; 
		String saveDir = session.getServletContext().getRealPath(uploadDir);
		
		Path path = Paths.get(saveDir);
		try {
			Files.createDirectories(path);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		MultipartFile mFile = product.getFile();
		
		// MultipartFile 객체의 getOriginalFilename() 메서드를 통해 파일명 꺼내기
		String originalFileName = mFile.getOriginalFilename();
			// 가져온 파일이 있을 경우에만 중복 방지 대책 수행하기
			if(!originalFileName.equals("")) {
				// 파일명 중복 방지 대책
				// 시스템에서 랜덤ID 값을 추출하여 파일명 앞에 붙이기("랜덤ID값_파일명" 형식)
				// => 랜덤ID 값 추출은 UUID 클래스 활용(범용 고유 식별자)
				String uuid = UUID.randomUUID().toString();
	//			System.out.println("업로드 될 파일명 : " + uuid + "_" + originalFileName);
				
				// 파일명을 결합하여 보관할 변수에 하나의 파일 문자열 결합
				originalFileName += uuid + "_" + originalFileName + "/";
				
			} 
		
			product.setPRODUCT_IMAGE(originalFileName);
		// => 실제로는 UUID 를 결합한 파일명만 사용하여 원본파일명과 실제파일명 모두 처리 가능
		// => 실제파일명에서 가장 먼저 만나는 "_" 기호를 기준으로 분리하면
		//    두번째 배열 인덱스 데이터가 원본 파일명이 된다!
		// --------------------------------------------------------------------
		// Service 객체의 registBoard() 메서드를 호출하여 게시물 등록 작업 요청
		// => 파라미터 : BoardVO 객체    리턴타입 : int(insertCount)
		int insertCount = service.registPd(product);
		
		// 등록 성공/실패에 따른 포워딩 작업 수행
		if(insertCount > 0) { // 성공
			
			return "redirect:/PdInquiry";
		} else { // 실패
			// "msg" 속성명으로 "글 쓰기 실패!" 메세지 전달 후 fail_back 포워딩
			model.addAttribute("msg", "품목 등록 실패!");
			return "fail_back";
		}
		
	}
	
		// ===============================================================================
		// 품목 목록 조회
		
		@GetMapping(value = "/PdInquiry")
		public String pdInquiry() {
			System.out.println("품목 조회");
			return "pd/pd_inquiry";
		}
		
		@ResponseBody
		@GetMapping("/PdInquiryJson")
		public void pdInquiryJson(
				@RequestParam(defaultValue = "") String searchType,
				@RequestParam(defaultValue = "") String keyword,
				@RequestParam(defaultValue = "1") int pageNum,
				Model model,
				HttpServletResponse response) {
			
			// 페이징 처리를 위한 변수 선언
			int listLimit = 10; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
			int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산
//			System.out.println("startRow = " + startRow);
			// ---------------------------------------------------------------------------
			// Service 객체의 getBoardList() 메서드를 호출하여 게시물 목록 조회
			// => 파라미터 : 검색타입, 검색어, 시작행번호, 목록갯수   
			// => 리턴타입 : List<BoardVO>(boardList)
			List<PdVO> pdList = service.getPdList(searchType, keyword, startRow, listLimit);
			// ---------------------------------------------------------------------------

			JSONArray jsonArray = new JSONArray();
			
			for(PdVO product : pdList) {
				
				JSONObject jsonObject = new JSONObject();
//				JSONObject jsonObject = new JSONObject(product);
				
//				System.out.println(jsonObject);
			
				jsonArray.add(jsonObject);
//				jsonArray.put(jsonObject);
				
			}
			
//			System.out.println(jsonArray);
			
			try {
				// 응답 데이터를 직접 생성하여 웹페이지에 출력
				// response 객체의 setCharacterEncodinf() 메서드로 출력 데이터 인코딩 지정 후
				// response 객체의 getWriter() 메서드로 PrintWriter 객체를 리턴받아
				// PrintWriter 객체의 print() 메서드를 호출하여 응답데이터 출력
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print(jsonArray);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		// ===============================================================================
		// 품목 그룹 조회
		@RequestMapping(value = "/Pd_group_bottom_SearchForm", method = RequestMethod.GET)
		public String pd_group_bottom_Search(Model model) {
			System.out.println("품목 그룹 선택 팝업");
			return "pd/pd_group_bottom_Search";
		}

		@SuppressWarnings("unchecked")
		@ResponseBody
		@RequestMapping(value = "/Pd_group_bottom_Search", method = RequestMethod.POST)
		public String pd_group_bottom_SearchPro(String keyword) {
			System.out.println("품목 그룹 선택 : keyword="+keyword);

			ArrayList<PdVO> pd_group_bottom_List = service.getPd_group_bottom_Search(keyword);

			JSONObject jsonStr = new JSONObject();
			JSONArray arr = new JSONArray();

			for(PdVO bean : pd_group_bottom_List) {
				JSONObject product = new JSONObject();
				
				product.put("PRODUCT_GROUP_TOP_CD", bean.getPRODUCT_GROUP_TOP_CD());
				product.put("PRODUCT_GROUP_BOTTOM_CD", bean.getPRODUCT_GROUP_BOTTOM_CD());
				product.put("PRODUCT_GROUP_BOTTOM_NAME", bean.getPRODUCT_GROUP_BOTTOM_NAME());
				arr.add(product);
			}

			jsonStr.put("jsonPd_group_bottom", arr);
			
//			System.out.println("pd_group_bottom_List: " + pd_group_bottom_List);
//			System.out.println("jsonStr: " + jsonStr);
			
			return jsonStr.toJSONString();
		}
		
		// ===============================================================================
		//품목 구분 조회
		@RequestMapping(value = "/Pd_type_SearchForm", method = RequestMethod.GET)
		public String pd_type_Search(Model model) {
			System.out.println("품목 구분 선택 팝업");
			return "pd/pd_type_Search";
		}
		
		@SuppressWarnings("unchecked")
		@ResponseBody
		@RequestMapping(value = "/Pd_type_Search", method = RequestMethod.POST)
		public String pd_type_SearchPro(String keyword) {
			System.out.println("품목 구분 선택 : keyword="+keyword);
			
			ArrayList<PdVO> pd_type_List = service.getPd_type_Search(keyword);
			
			JSONObject jsonStr = new JSONObject();
			JSONArray arr = new JSONArray();
			
			for(PdVO bean : pd_type_List) {
				JSONObject product = new JSONObject();
				
				product.put("PRODUCT_TYPE_CD", bean.getPRODUCT_TYPE_CD());
				product.put("PRODUCT_TYPE_NAME", bean.getPRODUCT_TYPE_NAME());
				arr.add(product);
			}
			
			jsonStr.put("jsonPd_type", arr);
			
//			System.out.println("pd_group_bottom_List: " + pd_group_bottom_List);
//			System.out.println("jsonStr: " + jsonStr);
			
			return jsonStr.toJSONString();
		}
		
	// ===============================================================================
	// 바코드 생성
	   @ResponseBody
	   @RequestMapping(value = "/Barcode", method = RequestMethod.GET)
	   public String newBarcode() {
	      System.out.println("바코드생성");
	
	      char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	      String barcode= "";
	      int idx = 0;
	      for (int i = 0; i < 10; i++) {
	         idx = (int) (charSet.length * Math.random());
	         barcode += charSet[idx];
	         
	         System.out.println("barcode: " + barcode);
	         // pd_regist에서 ajax로 해야함(href지우기!!)
	      
	   }
	      return "";
		
	   }	
}
