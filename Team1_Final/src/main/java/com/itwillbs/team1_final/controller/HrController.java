package com.itwillbs.team1_final.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

		ArrayList<HrVO> gradeList = service.getGradeInfo();

		model.addAttribute("gradeList",gradeList);

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

	@RequestMapping(value = "/HrRegistPro", method = RequestMethod.POST)
	public String hrRegistPro(HrVO newEmp, HttpSession session, Model model) {
		System.out.println("신규 사원 등록");

		////이메일, 전화번호, 주소 합치기
		
		newEmp.setEMP_DTEL(newEmp.getEMP_TEL1()+"-"+newEmp.getEMP_TEL2()+"-"+newEmp.getEMP_TEL3());
		newEmp.setEMP_TEL(newEmp.getEMP_TEL1()+"-"+newEmp.getEMP_TEL2()+"-"+newEmp.getEMP_TEL3());
		newEmp.setEMP_EMAIL(newEmp.getEMP_EMAIL1()+"@"+newEmp.getEMP_EMAIL2());
		newEmp.setEMP_ADDR(newEmp.getEMP_ADDR()+" "+newEmp.getEMP_ADDR_detail());
		
		
		String uploadDir = "/resources/upload";
		String saveDir = session.getServletContext().getRealPath(uploadDir);

		File f = new File(saveDir);

		//파일 저장경로가 여러단계 들어갈 경우 폴더 동시에 생성함
		if(!f.exists()) {
			f.mkdirs();
		}

		///업로드이름
		String uuidString = UUID.randomUUID().toString();
		newEmp.setPHOTO(uuidString+"_"+newEmp.getRegistPHOTO().getOriginalFilename()+"/");

		System.out.println("========================");
		System.out.println(newEmp);
		System.out.println("========================");
		
		int insertCount = service.registHr(newEmp);

		if(insertCount>0) {
			try {
				MultipartFile mFile = newEmp.getRegistPHOTO();
				mFile.transferTo(new File(saveDir, newEmp.getPHOTO()));

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "redirect:/HrInquiry";
		} else {
			model.addAttribute("msg", "사원 등록 실패!");
			return "fail_back";
		}

	}

}
