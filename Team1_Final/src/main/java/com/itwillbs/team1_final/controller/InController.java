package com.itwillbs.team1_final.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.itwillbs.team1_final.svc.InService;
import com.itwillbs.team1_final.vo.AccVO;
import com.itwillbs.team1_final.vo.HrVO;
import com.itwillbs.team1_final.vo.InListVO;
import com.itwillbs.team1_final.vo.InPdVO;
import com.itwillbs.team1_final.vo.InVO;
import com.itwillbs.team1_final.vo.PdVO;

@Controller
public class InController {
	
	@Autowired
	private InService service;
	
	@GetMapping(value = "/IN_Schedule")
	public String in_schedule() {
		System.out.println("입고 예정 목록");
		
		return "in/in_schedule_list";
	}
	
	@ResponseBody 
	@GetMapping(value = "/in_schedule_list")
	public String in_list() {
		System.out.println("입고 예정 목록 에이젝스 - 전체");
		ArrayList<InListVO> in_scList = service.getScheduleList();
		JSONArray jsonArray = new JSONArray();
		
		for(InListVO in : in_scList) {
			JSONObject jsonObject = new JSONObject(in);
			jsonArray.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("in_scList", jsonArray.toString());
		
		return jsonObject.toString();
	}
	
	@ResponseBody 
	@GetMapping(value = "/in_schedule_list_status")
	public String in_list_ing(HttpServletResponse response, String keyword) {
		System.out.println("입고 예정 목록 에이젝스 - 진행중");
		ArrayList<InListVO> in_scList = service.getScheduleList(keyword);
		JSONArray jsonArray = new JSONArray();
		
		for(InListVO in : in_scList) {
			JSONObject jsonObject = new JSONObject(in);
			jsonArray.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("in_scList", jsonArray.toString());
		
		return jsonObject.toString();
		
	}
	
	@GetMapping(value = "/IncomingRegistration")
	public String inRegi() {
		System.out.println("입고 등록 폼 페이지");
		return "in/in_registration";
	}
	
	@GetMapping(value = "/SearchEMP")
	public String searchEmp() {
		System.out.println("사원 검색");
		return "in/search_employee";
	}
	
	@ResponseBody 
	@GetMapping(value = "/SearchEmp")
	public String search_emp(HttpServletResponse response, String keyword) {
		System.out.println("사원 검색 에이젝스");
		
		ArrayList<HrVO> hrList = service.getHrList(keyword);
		JSONArray jsonArray = new JSONArray();
		
		for(HrVO hr : hrList) {
			JSONObject jsonObject = new JSONObject(hr);
			jsonArray.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("EmpList", jsonArray.toString());
		
		return jsonObject.toString();

	}
	
	@GetMapping(value = "/searchBusiness_no")
	public String SearchBus() {
		System.out.println("거래처 검색");
		return "in/search_business_no";
	}
	
	@ResponseBody
	@GetMapping(value = "/AccSearchPro")
	public String searchAccPro(String keyword, Model model, HttpServletResponse response) {
		System.out.println("거래처 검색 에이젝스");
		List<AccVO> accList = service.searchAcc(keyword);
		
		JSONArray jsonAcc = new JSONArray();
		
		for(AccVO acc : accList) {
			
			JSONObject jsonObject = new JSONObject(acc);
			jsonAcc.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("accList", jsonAcc.toString());
		
		return jsonObject.toString();
	}
	
	@GetMapping(value = "/SearchProduct")
	public String SearchPro() {
		System.out.println("품목 검색");
		return "in/search_product";
	}
	
	@GetMapping(value = "/SearchProductUpdate")
	public String SearchProUpdate() {
		System.out.println("업데이트 품목 검색");
		return "in/search_product_update";
	}
	
	@ResponseBody
	@GetMapping(value = "/SearchPro")
	public String searchProduct(Model model, HttpServletResponse response, String keyword) {
		System.out.println("품목 검색 에이젝스");
		
		ArrayList<PdVO> productList = service.getProductList(keyword);
		JSONArray jsonArray = new JSONArray();
		
		for(PdVO product : productList) {

			JSONObject jsonObject = new JSONObject(product);
			jsonArray.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("productList", jsonArray.toString());
		System.out.println("jsonObject : " + jsonObject);
		return jsonObject.toString();
	}
	
	@ResponseBody
	@PostMapping(value = "/IncomingRegiPro")
	public int regiPro(Model model, HttpSession session, @ModelAttribute InVO in
			, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("입고 예정 품목 등록");
		
		String today = request.getParameter("TODAY"); // 날짜 받아오기
		int product_num = service.searchToday(today); // 입고예정코드 조회
		int idx = 1;
		String schedule_cd = "";
		
		if(product_num == 0) { // 입고예정코드 조회 결과 0일 때
			schedule_cd = today + "-" + idx; 
			in.setIN_SCHEDULE_CD(schedule_cd);
		}else { // 입고예정코드 조회 결과가 있을 때
			product_num += 1;
			schedule_cd = today + "-" + product_num;
			in.setIN_SCHEDULE_CD(schedule_cd);
		}
		
		int insertCount = service.regiIncoming(in);
		
		return insertCount;
	}
	
	@GetMapping(value = "/ScheduleProgress")
	public String progress() {
		System.out.println("입고처리 진행상태");
		return "in/product_progress";
	}
	
	@ResponseBody
	@GetMapping(value = "/ProductProgress")
	public String progressBody(HttpServletResponse response, String keyword) {
		System.out.println("입고처리 진행상태 에이젝스");

		ArrayList<InPdVO> ProgressList = service.getProgress(keyword);
		JSONArray jsonArray = new JSONArray();
		
		for(InPdVO progress : ProgressList) {
			JSONObject jsonObject = new JSONObject(progress);
			jsonArray.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ProgressList", jsonArray.toString());
		
		return jsonObject.toString();
	}
	
	@GetMapping(value = "/ScheduleComplete")
	public String complete() {
		System.out.println("입고 예정 목록 종결 / 취소 변환");
		return "in/in_complete";
	}
	
	@ResponseBody
	@GetMapping(value = "/UpdateComplete")
	public int updateCom(HttpServletResponse response, String keyword, String com_status) {
		System.out.println("입고 예정 목록 종결 / 취소 변환 에이젝스");

		int updateCount = service.modifyComplete(keyword , com_status);
		return updateCount;
	}
	
	@GetMapping(value = "/IN_Process")
	public String in_process() {
		System.out.println("입고 처리 목록");
		return "in/in_process_list";
	}
	
	@ResponseBody 
	@GetMapping(value = "/in_process_list")
	public String processList(HttpServletResponse response) {
		System.out.println("입고 처리 목록 에이젝스");
		ArrayList<InListVO> progressList = service.getProgressList();
		JSONArray jsonArray = new JSONArray();
		
		for(InListVO in : progressList) {
			JSONObject jsonObject = new JSONObject(in);
			jsonArray.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("progressList", jsonArray.toString());
		
		return jsonObject.toString();
		
	}
	
	@GetMapping(value = "/ScheduleUpdate")
	public String update(Model model, HttpSession session, InVO in) {
		System.out.println("입고예정 수정");

		return "in/in_product_update";
	}
	
	@ResponseBody
	@GetMapping(value = "/UpdateProductInfo")
	public InPdVO productInfo(HttpServletResponse response, String product_cd, String product_name) {
		System.out.println("상품 수정 폼 에이젝스");

		if(product_name.contains("[")) {
			product_name = product_name.split("\\[")[0];
		}
		InPdVO ProductInfo = service.getProductInfo(product_cd, product_name);

		return ProductInfo;
	}
	
	@ResponseBody
	@PostMapping(value = "/InProductUpdate")
	public int productUpdate(HttpServletResponse response, @ModelAttribute InPdVO product, HttpServletRequest request) {
		System.out.println("입고 예정 수정 에이젝스");
		
		String product_cd = request.getParameter("in_product_cd");
		String product_name = request.getParameter("in_product_name");
		
		if(product_name.contains("[")) {
			product_name = product_name.split("\\[")[0];
		}
		
		int updateCount = service.modifyProduct(product_cd, product_name, product);
		
		return updateCount;
	}
	
	@GetMapping(value = "/incomingProcess")
	public String process() {
		System.out.println("입고 처리");
		
		return "in/in_product_process";
	}
	
	@GetMapping(value = "/SearchStockCd")
	public String searchStock() {
		System.out.println("재고 번호 검색");
		
		return "in/search_stock_cd";
	}
	
	@ResponseBody
	@GetMapping(value = "/CreateStockCd")
	public int create(HttpServletResponse response, @RequestParam("arrList[]") List<Integer> arrList) {
		int stock_cd = service.createStockCD();

		int count = 0;
		List<Integer> maxList = new ArrayList<Integer>();
		
		for(int i = 0; i < arrList.size(); i++) {
			if(arrList.get(i) == null) {
				count++;
			}
		}
		
		if(count == arrList.size()) {
			return stock_cd;
		}else {
			
			for(int i = 0; i < arrList.size(); i++) {
				if(arrList.get(i) != null) {
					maxList.add(arrList.get(i));
				}
			}
			
			int max = Collections.max(maxList);

			if(max > stock_cd) {
				max += 1;
			}else if(max == stock_cd){
				max = stock_cd + 1;
			}else if(max < stock_cd){
				max = stock_cd;
			}
			
			return max;
			
		}
	}
	
	
	@ResponseBody
	@GetMapping(value = "/SearchStock")
	public String search_stock(HttpServletResponse response, String keyword) {
		System.out.println("재고 번호 검색 에이젝스");
		
		ArrayList<InPdVO> stockList = service.getstockList(keyword);
		JSONArray jsonArray = new JSONArray();
		
		for(InPdVO stock : stockList) {
			JSONObject jsonObject = new JSONObject(stock);
			jsonArray.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("stockList", jsonArray.toString());
		
		return jsonObject.toString();

	}
	
	@GetMapping(value = "/SearchWh")
	public String searchWh() {
		System.out.println("창고 검색");
		return "in/search_wh";
	}
	
	@ResponseBody
	@GetMapping(value = "/SearchWhLoc")
	public String whloc(HttpServletResponse response, String keyword) {
		System.out.println("창고 검색 에이젝스");
		
		ArrayList<InPdVO> stockList = service.getstockList(keyword);
		JSONArray jsonArray = new JSONArray();
		
		for(InPdVO stock : stockList) {
			JSONObject jsonObject = new JSONObject(stock);
			jsonArray.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("stockList", jsonArray.toString());
		
		return jsonObject.toString();
	}
	
	@ResponseBody
	@GetMapping(value = "/in_process_list_status")
	public String in_process_ing(HttpServletResponse response, String keyword) {
		System.out.println("입고 처리 목록 에이젝스 - 전체 / 입고 예정 / 입고 완료");

		ArrayList<InListVO> in_scList = service.getProcessIngList(keyword);
		JSONArray jsonArray = new JSONArray();
		
		for(InListVO in : in_scList) {
			JSONObject jsonObject = new JSONObject(in);
			jsonArray.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("in_scList", jsonArray.toString());
		
		return jsonObject.toString();
		
	}
	
}

