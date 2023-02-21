package com.itwillbs.team1_final.controller;

import java.io.IOException;
import java.net.SocketException;
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

import com.itwillbs.team1_final.svc.EmailService;
import com.itwillbs.team1_final.svc.HrService;
import com.itwillbs.team1_final.svc.S3Service;
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

	@Autowired
	private S3Service S3Service;

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
	public String hrRegistPro(HrVO newEmp, HttpSession session, Model model) throws SocketException, IOException {
		System.out.println("신규 사원 등록");

		////이메일, 전화번호, 주소 합치기
		newEmp.setEMP_DTEL(newEmp.getEMP_DTEL1()+"-"+newEmp.getEMP_DTEL2()+"-"+newEmp.getEMP_DTEL3());
		newEmp.setEMP_TEL(newEmp.getEMP_TEL1()+"-"+newEmp.getEMP_TEL2()+"-"+newEmp.getEMP_TEL3());
		newEmp.setEMP_EMAIL(newEmp.getEMP_EMAIL1()+"@"+newEmp.getEMP_EMAIL2());

		if(newEmp.getEMP_DTEL().equals("--")) {
			newEmp.setEMP_DTEL("");
		}
		if(newEmp.getEMP_TEL().equals("--")) {
			newEmp.setEMP_TEL("");
		}


		///업로드이름
		String uploadFile = newEmp.getRegistPHOTO().getOriginalFilename();
		boolean isNewImg = false;

		if(uploadFile.length() > 0) {
			isNewImg = true;
			String uuidString = UUID.randomUUID().toString();
			newEmp.setPHOTO(uuidString+"_"+uploadFile);
		}

		///사번 생성
		int idxNum = service.getEmpIdx()+1;
		String idx = Integer.toString(idxNum);
		while(idx.length()<3) {
			idx = "0" + idx;
		}

		String nowYear = new SimpleDateFormat("yy").format(new Date());
		newEmp.setIdx(idxNum);
		newEmp.setEMP_NUM( newEmp.getDEPT_CD()+nowYear+idx);

		int insertCount = service.registEmp(newEmp);

		if(insertCount>0) {

			if(isNewImg) {
				S3Service.upload(newEmp.getRegistPHOTO(), newEmp.getPHOTO());
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
	@RequestMapping(value = "/HrListForm", method = {RequestMethod.POST, RequestMethod.GET})
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
			, @RequestParam(defaultValue = "") String workType
			, Model model) {

		System.out.println("사원 조회");

		int listLimit = 5; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산
		ArrayList<HrVO> empList = service.getEmpList(workType, searchType, keyword, startRow, listLimit);

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
			, @RequestParam(defaultValue = "1") String workType
			, Model model) {

		System.out.println("사원 조회 목록 페이지처리");

		int listLimit = 5; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int listCount = service.getEmpListCount(searchType, keyword, workType);
		int pageListLimit = 5;
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
	public String hrEditPro(HrVO updateEmp, Model model, HttpSession session) throws IOException {
		System.out.println("사원 수정 작업");

		///삭제할 파일 미리 저장해놓기
		String beforeImg = updateEmp.getPHOTO();

		////이메일, 전화번호, 주소 합치기
		updateEmp.setEMP_DTEL(updateEmp.getEMP_DTEL1()+"-"+updateEmp.getEMP_DTEL2()+"-"+updateEmp.getEMP_DTEL3());
		updateEmp.setEMP_TEL(updateEmp.getEMP_TEL1()+"-"+updateEmp.getEMP_TEL2()+"-"+updateEmp.getEMP_TEL3());
		updateEmp.setEMP_EMAIL(updateEmp.getEMP_EMAIL1()+"@"+updateEmp.getEMP_EMAIL2());

		if(updateEmp.getEMP_DTEL().equals("--")) {
			updateEmp.setEMP_DTEL("");
		}
		if(updateEmp.getEMP_TEL().equals("--")) {
			updateEmp.setEMP_TEL("");
		}

		////만약 비밀번호가 넘어왔다면 (=관리자가 아니고 당사자라면)
		if(updateEmp.getEMP_PASS() != null) {

			////비밀번호 확인해서 안 맞으면 돌려보내기
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			HrVO loginEmp = service.getLoginEmpInfo("EMP_NUM",updateEmp.getEMP_NUM());

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

		if(updateEmp.getNewEMP_NUM().equals("")) { ///새 사번이 비어있다면
			////신규 비밀번호 암호화해주기
			updateEmp.setNewEMP_NUM(updateEmp.getEMP_NUM()); //기존 사번으로 덮어쓰기
		}


		System.out.println("로그인 후 수정작업 ");

		///파일 올라와있으면 새 파일 올리고 기존 삭제
		boolean isNewImg = false;

		if(updateEmp.getRegistPHOTO().getOriginalFilename().length() > 0) {
			isNewImg = true;
			///업로드이름
			String uuidString = UUID.randomUUID().toString();
			updateEmp.setPHOTO(uuidString+"_"+updateEmp.getRegistPHOTO().getOriginalFilename());
		}

		int updateCount = service.updateEmp(updateEmp);

		///수정 성공했을시
		if(updateCount >0) {

			////새파일 올라와있으면
			if(isNewImg) {
				///옛날 파일 삭제
				S3Service.delete(beforeImg);
				///새 파일 등록
				S3Service.upload(updateEmp.getRegistPHOTO(), updateEmp.getPHOTO());
			}
			
		} else {
			model.addAttribute("msg", "사원 수정 실패!");
			return "fail_back";
		}
		return "redirect:/HrListForm";
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
		HrVO loginEmp = service.getLoginEmpInfo("EMP_EMAIL",emp.getEMP_EMAIL());

		if(loginEmp==null || !passwordEncoder.matches(emp.getEMP_PASS(), loginEmp.getEMP_PASS())) {

			model.addAttribute("msg", "로그인 실패!");
			return "fail_back";

		} else if(!loginEmp.getWORK_CD().equals("1")){ ///재직이 아니면

			model.addAttribute("msg", "휴퇴직 중인 사원은 로그인이 불가합니다.");
			return "fail_back";

		}	else { //로그인 성공

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
