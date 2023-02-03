package com.itwillbs.team1_final.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.team1_final.svc.InService;
import com.itwillbs.team1_final.vo.HrVO;
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
	public String search_emp(Model model, HttpServletResponse response, String keyword) {
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
}
