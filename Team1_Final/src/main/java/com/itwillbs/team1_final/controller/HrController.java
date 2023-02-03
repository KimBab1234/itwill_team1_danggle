package com.itwillbs.team1_final.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.itwillbs.team1_final.svc.EmailService;
import com.itwillbs.team1_final.svc.HrService;
import com.itwillbs.team1_final.vo.HrVO;
import com.itwillbs.team1_final.vo.PageInfo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HrController {

	@Autowired
	HrService service;

	@Autowired
	private EmailService mailService;

	/////사원 등록 폼
	@RequestMapping(value = "/HrRegist", method = RequestMethod.GET)
	public String hrRegist(Model model) {
		System.out.println("사원 등록");

		ArrayList<HrVO> gradeList = service.getGradeInfo();

		model.addAttribute("gradeList",gradeList);

		return "hr/hr_regist";
	}

	/////사원 등록 Pro
	@RequestMapping(value = "/HrRegistPro", method = RequestMethod.POST)
	public String hrRegistPro(HrVO newEmp, HttpSession session, Model model) {
		System.out.println("신규 사원 등록");

		////이메일, 전화번호, 주소 합치기
		newEmp.setEMP_DTEL(newEmp.getEMP_TEL1()+"-"+newEmp.getEMP_TEL2()+"-"+newEmp.getEMP_TEL3());
		newEmp.setEMP_TEL(newEmp.getEMP_TEL1()+"-"+newEmp.getEMP_TEL2()+"-"+newEmp.getEMP_TEL3());
		newEmp.setEMP_EMAIL(newEmp.getEMP_EMAIL1()+"@"+newEmp.getEMP_EMAIL2());

		if(newEmp.getEMP_DTEL().equals("--")) {
			newEmp.setEMP_DTEL("");
		}
		if(newEmp.getEMP_TEL().equals("--")) {
			newEmp.setEMP_TEL("");
		}

		String uploadDir = "/resources/img";
		String saveDir = session.getServletContext().getRealPath(uploadDir);

		File f = new File(saveDir);

		//파일 저장경로가 여러단계 들어갈 경우 폴더 동시에 생성함
		if(!f.exists()) {
			f.mkdirs();
		}

		///업로드이름
		String uuidString = UUID.randomUUID().toString();
		newEmp.setPHOTO(uuidString+"_"+newEmp.getRegistPHOTO().getOriginalFilename()+"/");

		///사번 생성
		int idxNum = service.getEmpIdx();
		String idx = Integer.toString(idxNum);
		while(idx.length()<3) {
			idx = "0" + idx;
		}

		String nowYear = new SimpleDateFormat("yy").format(new Date());

		newEmp.setEMP_NUM(newEmp.getDEPT_CD()+nowYear+idx);
		System.out.println(newEmp.getEMP_NUM());

		int insertCount = service.registEmp(newEmp);

		if(insertCount>0) {
			try {
				MultipartFile mFile = newEmp.getRegistPHOTO();
				mFile.transferTo(new File(saveDir, newEmp.getPHOTO()));

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			model.addAttribute("empNo",newEmp.getEMP_NUM());
			return "redirect:/HrRegistEnd";
		} else {
			model.addAttribute("msg", "사원 등록 실패!");
			return "fail_back";
		}

	}


	/////사원 등록 끝
	@RequestMapping(value = "/HrRegistEnd", method = RequestMethod.GET)
	public String hrRegistEnd(Model model) {
		return "hr/hr_regist_end";
	}

	/////사원 목록 폼
	@RequestMapping(value = "/HrListForm", method = {RequestMethod.GET})
	public String hrListForm() {
		return "hr/hr_list";
	}
	
	/////사원 조회
	@ResponseBody
	@RequestMapping(value = "/HrList", method = {RequestMethod.POST, RequestMethod.GET})
	public String hrList(
			@RequestParam(defaultValue = "1") int pageNum
			,@RequestParam(defaultValue = "") String searchType
			, @RequestParam(defaultValue = "") String keyword
			, Model model) {
		
		System.out.println("사원 조회");
		
		int listLimit = 10; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산
		ArrayList<HrVO> empList = service.getEmpList(searchType, keyword, startRow, listLimit);
		
		JSONObject jsonStr = new JSONObject();
		JSONArray arr = new JSONArray();

		for(HrVO bean : empList) {
			JSONObject emp = new JSONObject(bean);
			arr.put(emp);
		}

		jsonStr.put("jsonEmp", arr);
		return jsonStr.toString();
	}
	
	/////페이징처리
	@ResponseBody
	@RequestMapping(value = "/HrListPage", method = {RequestMethod.POST, RequestMethod.GET})
	public String hrListPage(
			@RequestParam(defaultValue = "1") int pageNum
			,@RequestParam(defaultValue = "") String searchType
			, @RequestParam(defaultValue = "") String keyword
			, Model model) {
		
		System.out.println("사원 조회 목록 페이지처리");
		
		int listLimit = 10; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int listCount = service.getEmpListCount(searchType, keyword);
		int pageListLimit = 3;
		int maxPage = listCount / listLimit + (listCount % listLimit == 0 ? 0 : 1); 
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		int endPage = startPage + pageListLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(listCount, pageListLimit, maxPage, startPage, endPage);
		
		JSONObject pageObj = new JSONObject(pageInfo);
		JSONObject jsonPage = new JSONObject();
		jsonPage.put("jsonPage", pageObj.toString());
		return jsonPage.toString();
	}


	/////부서 조회
	@RequestMapping(value = "/DepartSearchForm", method = RequestMethod.GET)
	public String departSearch(Model model) {
		System.out.println("부서 검색 팝업");
		return "hr/hr_departSearch";
	}

	@ResponseBody
	@RequestMapping(value = "/DepartSearch", method = RequestMethod.POST)
	public String departSearchPro(String keyword) {
		System.out.println("부서 검색 : keyword="+keyword);

		ArrayList<HrVO> hrList = service.getDepartSearch(keyword);

		JSONObject jsonStr = new JSONObject();
		JSONArray arr = new JSONArray();

		for(HrVO bean : hrList) {
			JSONObject depart = new JSONObject(bean);
			arr.put(depart);
		}

		jsonStr.put("jsonDepart", arr);
		return jsonStr.toString();
	}


	/////사원 상세 조회
	@RequestMapping(value = "/HrDetail", method = RequestMethod.GET)
	public String hrDetail(String empNo, Model model) {

		System.out.println("사원 상세 조회");

		HrVO emp = service.getEmpDetail(empNo);
		model.addAttribute("emp",emp);

		return "hr/hr_regist";
	}

	/////사원 수정폼
	@RequestMapping(value = "/HrEdit", method = RequestMethod.GET)
	public String hrEdit(String empNo, Model model) {
		System.out.println("사원 수정 폼");

		ArrayList<HrVO> gradeList = service.getGradeInfo();
		model.addAttribute("gradeList",gradeList);

		HrVO emp = service.getEmpDetail(empNo);

		String[] arr = emp.getEMP_TEL().split("-");
		if(arr.length==3) {
			emp.setEMP_TEL1(arr[0]);
			emp.setEMP_TEL2(arr[1]);
			emp.setEMP_TEL3(arr[2]);
		}

		arr = emp.getEMP_DTEL().split("-");
		if(arr.length==3) {
			emp.setEMP_DTEL1(arr[0]);
			emp.setEMP_DTEL2(arr[1]);
			emp.setEMP_DTEL3(arr[2]);
		}

		arr = emp.getEMP_EMAIL().split("@");
		if(arr.length==2) {
			emp.setEMP_EMAIL1(arr[0]);
			emp.setEMP_EMAIL2(arr[1]);
		}

		model.addAttribute("emp",emp);

		return "hr/hr_regist";
	}

	/////사원 수정
	@RequestMapping(value = "/HrEditPro", method = RequestMethod.POST)
	public String hrEditPro(HrVO updateEmp, Model model, HttpSession session) {
		System.out.println("사원 수정 작업");

		///삭제할 파일 미리 저장해놓기
		String beforeImg = updateEmp.getPHOTO();

		////이메일, 전화번호, 주소 합치기
		updateEmp.setEMP_DTEL(updateEmp.getEMP_DTEL1()+"-"+updateEmp.getEMP_DTEL2()+"-"+updateEmp.getEMP_DTEL3());
		updateEmp.setEMP_TEL(updateEmp.getEMP_TEL1()+"-"+updateEmp.getEMP_TEL2()+"-"+updateEmp.getEMP_TEL3());
		updateEmp.setEMP_EMAIL(updateEmp.getEMP_EMAIL1()+"@"+updateEmp.getEMP_EMAIL2());
		
		////만약 비밀번호가 넘어왔다면 (=관리자가 아니고 당사자라면)
		if(updateEmp.getEMP_PASS() != null) {
			
			////비밀번호 확인해서 안 맞으면 돌려보내기
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			HrVO loginEmp = service.getLoginEmpInfo(updateEmp.getEMP_EMAIL());

			//아이디가 존재하지 않거나, 비밀번호가 맞지 않는 경우
			if(loginEmp==null || !passwordEncoder.matches(updateEmp.getEMP_PASS(), loginEmp.getEMP_PASS())) {
				model.addAttribute("msg", "비밀번호가 맞지 않습니다!");
				return "fail_back";
			}
			
			////신규 비밀번호를 입력했다면
			if(!updateEmp.getEMP_PASS_NEW().equals("")) {
				////신규 비밀번호 암호화해주기
				updateEmp.setEMP_PASS_NEW(passwordEncoder.encode(updateEmp.getEMP_PASS_NEW()));
			}
		}
		
		///파일 올라와있으면 새 파일 올리고 기존 삭제
		boolean isNewImg = false;
		String uploadDir = "/resources/upload";
		String saveDir = session.getServletContext().getRealPath(uploadDir);

		if(updateEmp.getRegistPHOTO().getOriginalFilename().length() > 0) {

			isNewImg = true;
			File f = new File(saveDir);

			//파일 저장경로가 여러단계 들어갈 경우 폴더 동시에 생성함
			if(!f.exists()) {
				f.mkdirs();
			}

			///업로드이름
			String uuidString = UUID.randomUUID().toString();
			updateEmp.setPHOTO(uuidString+"_"+updateEmp.getRegistPHOTO().getOriginalFilename());
		}

		int updateCount = service.updateEmp(updateEmp);

		///수정 성공했을시
		if(updateCount >0) {

			////새파일 올라와있으면
			if(isNewImg) {
				try {
					////기존 파일 삭제
					File f = new File(saveDir, beforeImg);
					if(f.exists()) {
						f.delete();
					}

					////신규 파일 등록
					MultipartFile mFile = updateEmp.getRegistPHOTO();
					mFile.transferTo(new File(saveDir, updateEmp.getPHOTO()));

				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return "redirect:/HrListForm";
		} else {
			model.addAttribute("msg", "사원 수정 실패!");
			return "fail_back";
		}

	}

	//////////AJAX 임시 비밀번호 발송
	@ResponseBody
	@RequestMapping(value = "/HrTempPass", method = RequestMethod.POST)
	public void hrTempPass(String empNo) {
		System.out.println("임시 비밀번호 발송");

		mailService.sendEmail(empNo);
	}

	/////로그인 폼
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String login(Model model) {
		System.out.println("로그인 폼");
		return "hr/login";
	}
	
	/////로그인작업
	@RequestMapping(value = "/LoginPro", method = RequestMethod.POST)
	public String loginPro(HrVO emp, Model model, HttpSession session) {
		System.out.println("로그인 작업");

		///이메일 합쳐주기
		emp.setEMP_EMAIL(emp.getEMP_EMAIL1()+"@"+emp.getEMP_EMAIL2());
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		HrVO loginEmp = service.getLoginEmpInfo(emp.getEMP_EMAIL());

		
		System.out.println("====================================");
		System.out.println(emp.getEMP_PASS());
		System.out.println("====================================");
		
		
		//아이디가 존재하지 않거나, 비밀번호가 맞지 않는 경우
		if(loginEmp==null || !passwordEncoder.matches(emp.getEMP_PASS(), loginEmp.getEMP_PASS())) {
			
			model.addAttribute("msg", "로그인 실패!");
			return "fail_back";
			
		} else { //로그인 성공
			
			///권한 저장해주기
			session.setAttribute("priv", loginEmp.getPRIV_CD());
			session.setAttribute("empNo", loginEmp.getEMP_NUM());
			session.setAttribute("empName", loginEmp.getEMP_NAME());
			session.setAttribute("email", loginEmp.getEMP_EMAIL());
			
			///임시 비밀번호일 경우 바꾸라고 하기
			if(emp.getEMP_PASS().length() < 8) {
				session.setAttribute("needPassChange", "Y");
			}
			
			return "redirect:/";
		}
	}
	
	/////로그아웃
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		System.out.println("로그아웃");
		session.invalidate();
		return "redirect:/";
	}
	
	////비밀번호 변경하러가기
	@RequestMapping(value = "/MovePassChange", method = RequestMethod.GET)
	public String movePassChange(Model model, HttpSession session) {
		System.out.println("비밀번호 변경하러가기");
		return "hr/needPassChange";
	}



}
