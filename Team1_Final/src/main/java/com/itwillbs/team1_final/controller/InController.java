package com.itwillbs.team1_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwillbs.team1_final.svc.InService;

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
	
	
}
