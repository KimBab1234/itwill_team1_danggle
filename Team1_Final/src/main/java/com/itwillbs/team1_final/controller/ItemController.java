package com.itwillbs.team1_final.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {

	@GetMapping(value = "/ItemRegist")
	public String itemRegist() {
		System.out.println("품목 등록");
		return "item/item_regist";
	}
	
	@PostMapping(value = "/ItemRegistPro")
	public String itemRegistPro() {
		
		return"";
	}
	
	
	
	
}
