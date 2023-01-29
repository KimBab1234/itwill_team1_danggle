package com.itwillbs.team1_final.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
		return "hr/hr_regist";
	}
	@RequestMapping(value = "/HrInquiry", method = RequestMethod.GET)
	public String hrInquiry(Model model) {
		System.out.println("사원 조회");

		return "hr/hr_inquiry";
	}
	@RequestMapping(value = "/DepartSearchForm", method = RequestMethod.GET)
	public String departSearch(Model model) {
		System.out.println("부서 검색 팝업");
		return "hr/hr_departSearch";
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/DepartSearch", method = RequestMethod.POST)
	public String departSearchPro(String keyword) {
		System.out.println("부서 검색 : keyword="+keyword);
		
		ArrayList<HrVO> hrList = service.getDepartSearch(keyword);

		JSONObject jsonStr = new JSONObject();
		JSONArray arr = new JSONArray();
		
		for(HrVO bean : hrList) {
			JSONObject depart = new JSONObject();
			depart.put("DEPT_CD", bean.getDEPT_CD());
			depart.put("DEPT_NAME", bean.getDEPT_NAME());
			arr.add(depart);
		}
		
		jsonStr.put("jsonDepart", arr);
		return jsonStr.toJSONString();
	}

}
