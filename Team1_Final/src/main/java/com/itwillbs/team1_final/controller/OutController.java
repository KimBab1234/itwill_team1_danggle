package com.itwillbs.team1_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwillbs.team1_final.svc.OutService;

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
	
	
	// 출고처리 목록 창
	@GetMapping(value = "/OutProList")
	public String schedule() {
		return "out/out_process_list";
	}
	
}
