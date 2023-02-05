package com.itwillbs.team1_final.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@GetMapping(value = "/IN_Process")
	public String in_process() {
		System.out.println("입고 처리 목록");
		return "in/in_process_list";
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
	
	
//	@PostMapping(value = "/IncomingRegiPro")
//	public String regiPro(Model model, HttpSession session, @ModelAttribute InVO in
//			, HttpServletRequest request) {
//		System.out.println("입고 예정 품목 등록");
//		
//		String today = request.getParameter("TODAY"); // 날짜 받아오기
//		int product_num = service.searchToday(today); // 입고예정코드 조회
//		int idx = 1;
//		String schedule_cd = "";
//		
//		if(product_num == 0) { // 입고예정코드 조회 결과 0일 때
//			schedule_cd = today + "-" + idx; 
//			in.setIN_SCHEDULE_CD(schedule_cd);
//		}else { // 입고예정코드 조회 결과가 있을 때
//			product_num += 1;
//			schedule_cd = today + "-" + product_num;
//			in.setIN_SCHEDULE_CD(schedule_cd);
//		}
//		
//		int insertCount = service.regiIncoming(in);
//		
//		if(insertCount > 0) {
//			
//		}else {
//			model.addAttribute("msg", "입고 예정 상품 등록 실패!");
//			return "fail_back";
//		}
//		
//		return "in/in_schedule_list";
//	}
	
	@GetMapping(value = "/ScheduleProgress")
	public String progress() {
		return "in/product_progress";
	}
	
	@ResponseBody
	@GetMapping(value = "/ProductProgress")
	public String progressBody(HttpServletResponse response) {
		System.out.println("입고처리 진행상태 에이젝스");
		
		ArrayList<InPdVO> ProgressList = service.getProgress();
		JSONArray jsonArray = new JSONArray();
		
		for(InPdVO progress : ProgressList) {
			JSONObject jsonObject = new JSONObject(progress);
			jsonArray.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ProgressList", jsonArray.toString());
		
		return jsonObject.toString();
	}
}
