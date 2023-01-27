package com.itwillbs.team1_final.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccController {
	
	@GetMapping(value = "/AccRegist")
	public String accRegist() {
		System.out.println("거래처 등록");
		return "acc/acc_regist";
	}
}
