package com.itwillbs.team1_final.controller;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.net.ftp.FTPClient;
import org.json.JSONArray;
import org.json.JSONObject;

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
public class PdController{

	@Autowired
	private PdService service;

	static FTPClient ftp;

	// ===============================================================================
	// 품목 등록
	@GetMapping(value = "/PdRegist")
	public String pdRegist() {
		System.out.println("품목 등록");
		return "pd/pd_regist";
	}

	@PostMapping(value = "/PdRegistPro")
	public String pdRegistPro(@ModelAttribute PdVO product, Model model, HttpSession session) throws SocketException, IOException {

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
			originalFileName = uuid + "_" + originalFileName;

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
			
			if(!originalFileName.equals("")) {
				///ftp 연결 및 로그인
				ftp = HrController.ftpControl(new FTPClient());
				///ftp로 파일 저장
				ftp.storeFile(product.getPRODUCT_IMAGE(), mFile.getInputStream());
				ftp.disconnect();
			}


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
	public String pdInquiryJson(
			@RequestParam(defaultValue = "") String searchType,
			@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "1") int pageNum,
			Model model,
			HttpServletResponse response) {
		//			System.out.println(searchType + keyword + pageNum);
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

		System.out.println(pdList);
		for(PdVO product : pdList) {
			JSONObject jsonObject = new JSONObject(product);
			//				JSONObject jsonObject = new JSONObject(product);

			//				System.out.println(jsonObject);

			jsonArray.put(jsonObject);
			//				jsonArray.put(jsonObject);

		}

		//			System.out.println(jsonArray);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pdList", jsonArray.toString());


		return  jsonObject.toString();

	}
	// ===============================================================================
	// 품목 그룹 조회
	@RequestMapping(value = "/Pd_group_bottom_SearchForm", method = RequestMethod.GET)
	public String pd_group_bottom_Search(Model model) {
		System.out.println("품목 그룹 선택 팝업");
		return "pd/pd_group_bottom_Search";
	}

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
			arr.put(product);
		}

		jsonStr.put("jsonPd_group_bottom", arr);

		//			System.out.println("pd_group_bottom_List: " + pd_group_bottom_List);
		//			System.out.println("jsonStr: " + jsonStr);

		return jsonStr.toString();
	}

	// ===============================================================================
	//품목 구분 조회
	@RequestMapping(value = "/Pd_type_SearchForm", method = RequestMethod.GET)
	public String pd_type_Search(Model model) {
		System.out.println("품목 구분 선택 팝업");
		return "pd/pd_type_Search";
	}

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
			arr.put(product);
		}

		jsonStr.put("jsonPd_type", arr);

		//			System.out.println("pd_group_bottom_List: " + pd_group_bottom_List);
		//			System.out.println("jsonStr: " + jsonStr);

		return jsonStr.toString();
	}

	// ===============================================================================
	// 바코드 생성
	@ResponseBody
	@RequestMapping(value = "/Barcode", method = RequestMethod.GET)
	public String Barcode() {
		System.out.println("바코드생성");

		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		String barcode= "";
		int idx = 0;
		for (int i = 0; i < 10; i++) {
			idx = (int) (charSet.length * Math.random());
			barcode += charSet[idx];

			//	         System.out.println("barcode: " + barcode);

		}
		return barcode;
	}	

	// ===============================================================================
	//거래처 조회
	@RequestMapping(value = "/Business_No_SearchForm", method = RequestMethod.GET)
	public String business_no_Search(Model model) {
		System.out.println("거래처 선택 팝업");
		return "pd/business_no_Search";
	}

	@ResponseBody
	@RequestMapping(value = "/Business_no_Search", method = RequestMethod.POST)
	public String business_no_SearchPro(String keyword) {
		System.out.println("거래처 선택 : keyword="+keyword);

		ArrayList<PdVO> business_no_List = service.getBusiness_no_Search(keyword);

		JSONObject jsonStr = new JSONObject();
		JSONArray arr = new JSONArray();

		for(PdVO bean : business_no_List) {
			JSONObject product = new JSONObject();

			product.put("BUSINESS_NO", bean.getBUSINESS_NO());
			product.put("CUST_NAME", bean.getCUST_NAME());
			arr.put(product);
		}

		jsonStr.put("jsonBusiness_no", arr);

		//				System.out.println("pd_group_bottom_List: " + pd_group_bottom_List);
		//				System.out.println("jsonStr: " + jsonStr);

		return jsonStr.toString();
	}

	// ===============================================================================
	// 품목 그룹 신규 등록
	@RequestMapping(value = "/Pd_group_bottom_registForm", method = RequestMethod.GET)
	public String pd_group_bottom_Regist(Model model) {
		System.out.println("품목 그룹 신규 등록");
		return "pd/pd_group_bottom_regist";
	}

	@RequestMapping(value = "/Pd_group_bottom_registPro", method = RequestMethod.POST)
	public String pd_group_bottom_RegistPro(@ModelAttribute PdVO product, Model model, HttpSession session) {

		// --------------------------------------------------------------------
		// Service 객체의 registBoard() 메서드를 호출하여 게시물 등록 작업 요청
		// => 파라미터 : BoardVO 객체    리턴타입 : int(insertCount)
		int insertCount = service.registPd_group_bottom(product);

		// 등록 성공/실패에 따른 포워딩 작업 수행
		if(insertCount > 0) { // 성공

			return "redirect:/PdRegist";
		} else { // 실패
			// "msg" 속성명으로 "글 쓰기 실패!" 메세지 전달 후 fail_back 포워딩
			model.addAttribute("msg", "품목 그룹 등록 실패!");
			return "fail_back";
		}
	}

	// ===============================================================================
	//품목 그룹(대) 조회
	@RequestMapping(value = "/Pd_group_top_SearchForm", method = RequestMethod.GET)
	public String pd_group_top_Search(Model model) {
		System.out.println("품목 그룹(대) 선택 팝업");
		return "pd/pd_group_top_Search";
	}

	@ResponseBody
	@RequestMapping(value = "/Pd_group_top_Search", method = RequestMethod.POST)
	public String pd_group_top_SearchPro(String keyword) {
		System.out.println("품목 그룹(대) 선택 : keyword="+keyword);

		ArrayList<PdVO> pd_group_top_List = service.getPd_group_top_Search(keyword);

		JSONObject jsonStr = new JSONObject();
		JSONArray arr = new JSONArray();

		for(PdVO bean : pd_group_top_List) {
			JSONObject product = new JSONObject();

			product.put("PRODUCT_GROUP_TOP_CD", bean.getPRODUCT_GROUP_TOP_CD());
			product.put("PRODUCT_GROUP_TOP_NAME", bean.getPRODUCT_GROUP_TOP_NAME());
			arr.put(product);
		}

		jsonStr.put("jsonPd_group_top", arr);

		//					System.out.println("pd_group_bottom_List: " + pd_group_bottom_List);
		//					System.out.println("jsonStr: " + jsonStr);

		return jsonStr.toString();
	}

	// ===============================================================================
	//품목 삭제
	@GetMapping(value = "/Pd_deletePro")
	public String pd_deletePro(@RequestParam String deleteProdArr, 
			Model model, HttpSession session) throws SocketException, IOException {
		
		System.out.println("품목 삭제");
		System.out.println("deleteProdArr : "+deleteProdArr);
		
		///삭제할 이미지명 가져오기
		List<String> deleteImg = service.getImgList(deleteProdArr.substring(0,deleteProdArr.length()-1));
		
		String[] arr = deleteProdArr.split(",");
		
		int deleteCount = 0;
		///ftp 연결 및 로그인
		ftp = HrController.ftpControl(new FTPClient());
		
		for(int i=0; i<arr.length; i++) {
			deleteCount += service.removeProduct(Integer.parseInt(arr[i]));
			///ftp 파일 삭제
			ftp.deleteFile(deleteImg.get(i));
		}

		// 게시물 삭제 성공 시 해당 게시물의 파일도 삭제
		if(deleteCount == arr.length) { // 삭제 성공
			ftp.disconnect();
			return "redirect:/PdInquiry";
		} else { // 삭제 실패
			model.addAttribute("msg", "품목 삭제 실패!");
			return "fail_back";
		}

	}

	// ===============================================================================
	//품목 수정
	//수정 전에 품목 조회 먼저
	@GetMapping("/PdUpdate")
	public String pd_update(@RequestParam int PRODUCT_CD, Model model, HttpSession session) {

		System.out.println("여기1");
		PdVO product = service.getProduct(PRODUCT_CD);

		model.addAttribute("product", product);

		return "pd/pd_update";
	}

	@PostMapping(value = "/PdUpdatePro")
	public String pd_updatePro(
			@ModelAttribute PdVO product, 
			Model model, HttpSession session) {
		
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
			originalFileName = uuid + "_" + originalFileName;

		} 

		product.setPRODUCT_IMAGE(originalFileName);
		// => 실제로는 UUID 를 결합한 파일명만 사용하여 원본파일명과 실제파일명 모두 처리 가능
		// => 실제파일명에서 가장 먼저 만나는 "_" 기호를 기준으로 분리하면
		//    두번째 배열 인덱스 데이터가 원본 파일명이 된다!
		// --------------------------------------------------------------------
		// Service - modifyBoard() 메서드 호출하여 수정 작업 요청
		// => 파라미터 : BoardVO 객체, 리턴타입 : int(updateCount)
		int updateCount = service.updatePd(product);

		// 등록 성공/실패에 따른 포워딩 작업 수행
		if(updateCount > 0) { // 성공
			
			if(!originalFileName.equals("")) {
				try {
					///ftp 연결 및 로그인
					ftp = HrController.ftpControl(new FTPClient());
					///ftp로 파일 저장
					ftp.storeFile(product.getPRODUCT_IMAGE(), mFile.getInputStream());
					//원래 파일 삭제
					ftp.disconnect();
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return "redirect:/PdInquiry";
		} else {
			model.addAttribute("msg", "게시물 수정 실패!");
			return "fail_back";
		}


	}


}
