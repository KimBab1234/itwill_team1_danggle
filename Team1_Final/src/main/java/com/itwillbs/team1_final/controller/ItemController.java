package com.itwillbs.team1_final.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

	@GetMapping(value = "/ItemRegist")
	public String itemRegist() {
		return "item/item_regist";
	}
	
	
}
