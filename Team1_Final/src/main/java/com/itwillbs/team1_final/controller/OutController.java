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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.team1_final.svc.OutService;
import com.itwillbs.team1_final.vo.AccVO;

@Controller
public class OutController {

	@Autowired
	OutService service;
	
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
		System.out.println(jsonObject);
		
		return jsonObject.toString();
		
	}
	
	
	
	
	// 출고처리 목록 창
	@GetMapping(value = "/OutProList")
	public String schedule() {
		return "out/out_process_list";
	}
	
}
