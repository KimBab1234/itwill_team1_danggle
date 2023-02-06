package com.itwillbs.team1_final.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.team1_final.svc.OutService;
import com.itwillbs.team1_final.vo.AccVO;
import com.itwillbs.team1_final.vo.HrVO;
import com.itwillbs.team1_final.vo.OutSchVo;
import com.itwillbs.team1_final.vo.PdVO;

@Controller
public class OutController {

	@Autowired
	OutService service;
	
	// ------------------ 출고 예정 Controller ------------------
	// 출고예정 목록 창
	@GetMapping(value = "/OutSchList")
	public String OutList() {
		return "out/out_schedule_list";
	}
	
	
	// 출고예정 등록 창
	@GetMapping(value = "/OutRegist")
	public String regitOut() {
		return "out/out_regist";
	}
	
	// 출고예정 등록
	@PostMapping(value = "OutSchRegistPro")
	@ResponseBody
	public void schRegist(
			@RequestParam(defaultValue = "") String OUT_TODAY,
			@RequestParam(defaultValue = "") OutSchVo outSch,
			HttpServletResponse response) {
		
//		System.out.println(OUT_TODAY);
//		System.out.println(outSch);
		System.out.println("보냄X");
		
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("성공");
			System.out.println("보냄");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	// ----------------------------------------------------------
	
	
	// ------------------ 정보 검색 Controller ------------------
	// 거래처 검색 창
	@GetMapping(value = "/AccSearch")
	public String searchAcc() {
		return "out/out_accSearch";
	}
	
	// 거래처 검색
	@PostMapping(value = "/AccSearchPro")
	@ResponseBody
	public String searchAccPro(
			String searchType,String keyword,
			Model model,
			HttpServletResponse response) {
		
		List<AccVO> accList = service.searchAcc(searchType, keyword);
		
		JSONArray jsonAcc = new JSONArray();
		
		for(AccVO acc : accList) {
			
			JSONObject jsonObject = new JSONObject(acc);
			jsonAcc.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("accList", jsonAcc.toString());
		
		return jsonObject.toString();
		
	}
	
	
	// 담당자 검색 창
	@GetMapping(value = "/EmpSearch")
	public String searchEmp() {
		return "out/out_empSearch";
	}
	
	// 담당자 검색
	@PostMapping(value = "/EmpSearchPro")
	@ResponseBody 
	public String search_emp(Model model, HttpServletResponse response, String keyword) {
		
		ArrayList<HrVO> hrList = service.getHrList(keyword);
		JSONArray jsonArray = new JSONArray();
		
		for(HrVO hr : hrList) {
			JSONObject jsonObject = new JSONObject(hr);
			jsonArray.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("empList", jsonArray.toString());
		return jsonObject.toString();

	}
	
	
	// 품목 검색 창
	@GetMapping(value = "/PdSearch")
	public String SearchPro() {
		return "out/out_pdSearch";
	}
	
	// 품목 검색
	@ResponseBody
	@PostMapping(value = "/PdSearchPro")
	public String searchProduct(
			String searchType, String keyword,
			Model model, HttpServletResponse response) {
		
		ArrayList<PdVO> pdList = service.getPdList(searchType, keyword);
		JSONArray jsonArray = new JSONArray();
		
		for(PdVO pd : pdList) {

			JSONObject jsonObject = new JSONObject(pd);
			jsonArray.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pdList", jsonArray.toString());
		return jsonObject.toString();
	}
	// ----------------------------------------------------------
	
	
	// ------------------ 출고 처리 Controller ------------------
	// 출고처리 목록 창
	@GetMapping(value = "/OutProList")
	public String schedule() {
		return "out/out_process_list";
	}
	// ----------------------------------------------------------
	
}
