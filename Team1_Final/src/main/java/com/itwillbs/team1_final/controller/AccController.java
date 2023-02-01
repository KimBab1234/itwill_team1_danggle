package com.itwillbs.team1_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.itwillbs.team1_final.svc.AccService;
import com.itwillbs.team1_final.vo.AccVO;

@Controller
public class AccController {
	
	@Autowired
	private AccService service;
	
	@GetMapping(value = "/AccRegist")
	public String accRegist() {
		System.out.println("거래처 등록");
		return "acc/acc_regist";
	}
	
	@PostMapping(value = "/accregistPro")
	public String accRegistPro(@ModelAttribute AccVO acc, Model model) {
		System.out.println(acc);
		
		int insertCount = service.insertAcc(acc);
		
		if(insertCount > 0) {
			
			return "redirect:/";
		} else {
			model.addAttribute("msg", "거래처 등록 실패!");
			return "fail_back";
		}
		
	}
}
