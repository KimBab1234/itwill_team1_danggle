package com.itwillbs.team1_final.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HrController {
	
	@RequestMapping(value = "/HrRegist", method = RequestMethod.GET)
	public String hrRegist(Model model) {
		System.out.println("사원 등록");
		
		return "hr/hr_regist";
	}
	@RequestMapping(value = "/HrInquiry", method = RequestMethod.GET)
	public String hrInquiry(Model model) {
		System.out.println("사원 조회");
		
		return "hr/hr_inquiry";
	}
	
}
