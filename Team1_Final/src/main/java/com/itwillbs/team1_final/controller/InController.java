package com.itwillbs.team1_final.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.team1_final.svc.InService;
import com.itwillbs.team1_final.vo.HrVO;

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
//		System.out.println("검색어 확인 : " + keyword);
		
//		ArrayList<HrVO> hrList = service.getHrList(keyword);
		
		
		return "";
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
	
}
