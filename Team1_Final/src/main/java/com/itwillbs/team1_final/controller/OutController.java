package com.itwillbs.team1_final.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.team1_final.svc.OutService;
import com.itwillbs.team1_final.vo.AccVO;
import com.itwillbs.team1_final.vo.HrVO;
import com.itwillbs.team1_final.vo.OutPdVO;
import com.itwillbs.team1_final.vo.OutSchVo;
import com.itwillbs.team1_final.vo.PdVO;

@Controller
public class OutController {

	@Autowired
	OutService service;
	
	// ------------------ 출고 예정 Controller ------------------
	// 출고예정 목록 창
	@GetMapping(value = "/OutSchList")
	public String outList() {
		return "out/out_schedule_list";
	}
	
	// 출고예정 목록 조회
	@PostMapping(value = "/OutSchListJson")
	@ResponseBody
	public String searchOutList(
			@RequestParam(defaultValue = "") String keyword) {
		List<OutSchVo> outSchList = service.searchOutSchList(keyword);
		JSONArray jsonAcc = new JSONArray();
		
		for(OutSchVo outSch : outSchList) {
			JSONObject jsonObject = new JSONObject(outSch);
			jsonAcc.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("searchOutSchList", jsonAcc.toString());
		return jsonObject.toString();
	}
	
	
	// 출고예정 등록 창
	@GetMapping(value = "/OutRegist")
	public String regitOut() {
		return "out/out_regist";
	}
	
	// 출고예정 등록
	@PostMapping(value = "/OutSchRegistPro")
	@ResponseBody
	public int schRegist(
			@RequestParam(value = "OUT_TODAY") String OUT_TODAY,
			@ModelAttribute OutSchVo outSch,
			HttpServletResponse response) {
		System.out.println(outSch);
		int todayCount = service.searchToday(OUT_TODAY); // 입고예정코드 조회
		int idx = 1;
		String out_schedule_cd = "";
		
		if(todayCount > 0) {
			todayCount += 1;
			out_schedule_cd = OUT_TODAY + "-" + todayCount;
			outSch.setOUT_SCHEDULE_CD(out_schedule_cd);
		} else {
			out_schedule_cd = OUT_TODAY + "-" + idx;
			outSch.setOUT_SCHEDULE_CD(out_schedule_cd);
		}
		
		int insertCount = service.registOutSchAndPd(outSch);
		return insertCount;
		
	}
	// ----------------------------------------------------------
	
	
	// --------------- 출고 예정 품목 Controller ----------------
	// 출고 예정 품목 개별 목록 조회 창
	@GetMapping(value = "/OutEachPd")
	public String outEachPd() {
		return "out/out_one_of_each_pd";
	}
	
	// 출고 예정 품목 개별 목록 조회
	@PostMapping(value = "/OutEachPdPro")
	@ResponseBody
	public String outEachPdPro(String outSchCdList) {
		List<OutPdVO> ProgressList = service.getProgress(outSchCdList);
		JSONArray jsonArray = new JSONArray();
		
		for(OutPdVO progress : ProgressList) {
			JSONObject jsonObject = new JSONObject(progress);
			jsonArray.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pdList", jsonArray.toString());
		
		return jsonObject.toString();
	}
	
	
	// 출고 예정 종결여부 변경 창
	@GetMapping(value = "/OutCom")
	public String outCom() {
		return "out/out_complete";
	}
	
	// 출고 예정 종결여부 변경
	@PostMapping(value = "/OutComPro")
	@ResponseBody
	public int outComPro(String outSchCd, String comStatus) {
		int updateCount = service.modifyCom(outSchCd, comStatus);
		return updateCount;
	}
	// ----------------------------------------------------------
	
	
	// ------------------ 정보 검색 Controller ------------------
	// [ 출고 예정 ]
	// 거래처 검색 창
	@GetMapping(value = "/AccSearch")
	public String searchAcc() {
		return "out/out_accSearch";
	}
	
	// 거래처 검색
	@PostMapping(value = "/AccSearchPro")
	@ResponseBody
	public String searchAccPro(
			String searchType,String keyword,
			Model model,
			HttpServletResponse response) {
		
		List<AccVO> accList = service.searchAcc(searchType, keyword);
		
		JSONArray jsonAcc = new JSONArray();
		
		for(AccVO acc : accList) {
			
			JSONObject jsonObject = new JSONObject(acc);
			jsonAcc.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("accList", jsonAcc.toString());
		
		return jsonObject.toString();
		
	}
	
	
	// 담당자 검색 창
	@GetMapping(value = "/EmpSearch")
	public String searchEmp() {
		return "out/out_empSearch";
	}
	
	// 담당자 검색
	@PostMapping(value = "/EmpSearchPro")
	@ResponseBody 
	public String search_emp(Model model, HttpServletResponse response, String keyword) {
		
		ArrayList<HrVO> hrList = service.getHrList(keyword);
		JSONArray jsonArray = new JSONArray();
		
		for(HrVO hr : hrList) {
			JSONObject jsonObject = new JSONObject(hr);
			jsonArray.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("empList", jsonArray.toString());
		return jsonObject.toString();

	}
	
	
	// 품목 검색 창
	@GetMapping(value = "/PdSearch")
	public String SearchPro() {
		return "out/out_pdSearch";
	}
	
	// 품목 검색
	@ResponseBody
	@PostMapping(value = "/PdSearchPro")
	public String searchProduct(
			String searchType, String keyword,
			Model model, HttpServletResponse response) {
		
		ArrayList<PdVO> pdList = service.getPdList(searchType, keyword);
		JSONArray jsonArray = new JSONArray();
		
		for(PdVO pd : pdList) {

			JSONObject jsonObject = new JSONObject(pd);
			jsonArray.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pdList", jsonArray.toString());
		return jsonObject.toString();
	}
	
	
	// 재고 검색 창
	@GetMapping(value = "/StockSearch")
	public String searchStock() {
		return "out/out_stockSearch";
	}
	
	
	// [ 출고 처리 ]
	// 출고처리 - 품목 검색 창
	@GetMapping(value = "/UpdatePdSearch")
	public String SearchUpdatePro() {
		return "out/out_pdSearch_update";
	}
		
	// 출고처리 - 품목 검색 
	@ResponseBody
	@PostMapping(value = "/UpdatePdSearch")
	public String searchUpdateProduct(String searchType, String keyword) {
		
		ArrayList<PdVO> pdList = service.getPdList(searchType, keyword);
		JSONArray jsonArray = new JSONArray();
		
		for(PdVO pd : pdList) {
			
			JSONObject jsonObject = new JSONObject(pd);
			jsonArray.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pdList", jsonArray.toString());
		return jsonObject.toString();
	}
	// ----------------------------------------------------------
	
	
	// ------------------ 출고 처리 Controller ------------------
	// 출고처리 목록 창
	@GetMapping(value = "/OutProList")
	public String outProList() {
		return "out/out_process_list";
	}
	
	// 출고처리 목록 조회
	@PostMapping(value = "/OutProList")
	@ResponseBody
	public String outProListPro() {
		String keyword = "";
		List<OutSchVo> searchOutProList = service.searchOutProList(keyword);
		JSONArray jsonAcc = new JSONArray();
		
		for(OutSchVo outSchPro : searchOutProList) {
			JSONObject jsonObject = new JSONObject(outSchPro);
			jsonAcc.put(jsonObject);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("searchOutProList", jsonAcc.toString());

		return jsonObject.toString();
	}
	
	// 출고 처리 품목 수정 창
	@GetMapping(value = "/OutSchPdUpdate")
	public String OutSchPdUpdateWin() {
		return "out/out_process_update";
	}
	
	// 출고 처리 품목 수정 - 품목 데이터 조회
	@PostMapping(value = "/OutSchPdUpdate")
	@ResponseBody
	public OutSchVo OutSchPdUpdate(String pd_outSch_cd, String product_name) {
		OutSchVo searchOutProList = service.searchOutUpdatePd(pd_outSch_cd, product_name);

		return searchOutProList;
	}
	
	// 출고 처리 품목 수정
	@PostMapping(value = "/OutSchPdUpdatePro")
	public int OutSchPdUpdate(String OUT_TODAY, OutSchVo outSchPd) {
	
		int todayCount = service.searchToday(OUT_TODAY); // 입고예정코드 조회
		int idx = 1;
		String out_schedule_cd = "";
		
		if(todayCount > 0) {
			todayCount += 1;
			out_schedule_cd = OUT_TODAY + "-" + todayCount;
			
			System.out.println(out_schedule_cd);
			
			outSchPd.setOUT_SCHEDULE_CD(out_schedule_cd);
		} else {
			out_schedule_cd = OUT_TODAY + "-" + idx;
			
			System.out.println(out_schedule_cd);
			
			outSchPd.setOUT_SCHEDULE_CD(out_schedule_cd);
		}
		
//		int updateCount = service.modifyOutSchPd(outSchPd);
		
//		return updateCount;
		return 0;
	}
	// ----------------------------------------------------------
	
}
