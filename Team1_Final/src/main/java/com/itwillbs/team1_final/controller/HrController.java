package com.itwillbs.team1_final.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.team1_final.svc.HrService;
import com.itwillbs.team1_final.vo.HrVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HrController {

	@Autowired
	HrService service;

	@RequestMapping(value = "/HrRegist", method = RequestMethod.GET)
	public String hrRegist(Model model) {
		System.out.println("사원 등록");

		ArrayList<HrVO> hrList = service.getHrInfoJoin();

		model.addAttribute("hrList",hrList);

		return "hr/hr_regist";
	}
	@RequestMapping(value = "/HrInquiry", method = RequestMethod.GET)
	public String hrInquiry(Model model) {
		System.out.println("사원 조회");

		return "hr/hr_inquiry";
	}

}
