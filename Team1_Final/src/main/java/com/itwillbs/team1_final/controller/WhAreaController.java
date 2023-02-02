package com.itwillbs.team1_final.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.itwillbs.team1_final.svc.WhAreaService;
import com.itwillbs.team1_final.vo.WhAreaVO;

public class WhAreaController {

	@Autowired
	private WhAreaService service;
	
	@GetMapping(value = "/WhAreaRegist")
	public String WhAreaRegist(Model model, HttpSession session) {
		
//		String sId = (String)session.getAttribute("sId");
//		if(sId == null || sId.equals("")) {
//			model.addAttribute("msg", "로그인 필수");
//			return "fail_back";
//		}
		
		return "wh/area_regist";
	}
	
	@PostMapping(value = "/WhAreaRegistPro")
	public String WhAreaRegistPro(@ModelAttribute WhAreaVO WhArea, Model model, HttpSession session) {
//		String sId = (String)session.getAttribute("sId");
//		if(sId == null || sId.equals("")) {
//			model.addAttribute("msg", "로그인 필수");
//			return "fail_back";
//		}	
		
		
		
		
		return "redirect:/WhList";
	}
	
}
