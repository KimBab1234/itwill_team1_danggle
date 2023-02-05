package com.itwillbs.team1_final.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.team1_final.svc.StockService;
import com.itwillbs.team1_final.svc.WhService;
import com.itwillbs.team1_final.vo.StockVO;
import com.itwillbs.team1_final.vo.WhVO;
import com.itwillbs.team1_final.vo.HrVO;
import com.itwillbs.team1_final.vo.PageInfo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class StockController {

	@Autowired
	StockService service;
	
	/////창고 조회 폼
	@RequestMapping(value = "/WhSearchForm", method = RequestMethod.GET)
	public String whSearchForm(Model model) {
		System.out.println("창고 검색 팝업");
		return "stock/search_wh";
	}

	/////창고 조회 Pro
	@ResponseBody
	@RequestMapping(value = "/WhSearch", method = RequestMethod.POST)
	public String whSearch(@RequestParam(defaultValue = "1") int pageNum
			,@RequestParam(defaultValue = "") String searchType
			, @RequestParam(defaultValue = "") String keyword) {
		
		System.out.println("창고 검색 : keyword="+keyword);
		
		int listLimit = 10; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산

		ArrayList<WhVO> whList = service.getWhList(searchType, keyword, startRow, listLimit);

		JSONObject jsonStr = new JSONObject();
		JSONArray arr = new JSONArray();

		for(WhVO bean : whList) {
			JSONObject wh = new JSONObject(bean);
			arr.put(wh);
		}

		jsonStr.put("jsonWh", arr);
		return jsonStr.toString();
	}
	
	

	/////재고 목록 폼
	@RequestMapping(value = "/StockListForm", method = {RequestMethod.GET})
	public String stockListForm() {
		return "stock/stock_list";
	}
	/////재고 조정 폼
	@RequestMapping(value = "/StockMove", method = {RequestMethod.GET})
	public String stockMove() {
		return "stock/stock_move";
	}
	
	/////재고 목록 조회
	@ResponseBody
	@RequestMapping(value = "/StockList", method = {RequestMethod.POST, RequestMethod.GET})
	public String stockList(
			@RequestParam(defaultValue = "1") int pageNum
			,@RequestParam(defaultValue = "") String searchType
			, @RequestParam(defaultValue = "") String keyword
			, Model model) {
		
		System.out.println("재고 목록 조회");
		
		int listLimit = 10; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행번호 계산
		ArrayList<StockVO> stockList = service.getStockList(searchType, keyword, startRow, listLimit);
		
		JSONObject jsonStr = new JSONObject();
		JSONArray arr = new JSONArray();

		for(StockVO bean : stockList) {
			JSONObject stock = new JSONObject(bean);
			arr.put(stock);
		}

		jsonStr.put("jsonStock", arr);
		return jsonStr.toString();
	}
	
	/////페이징처리
	@ResponseBody
	@RequestMapping(value = "/StockListPage", method = {RequestMethod.POST, RequestMethod.GET})
	public String stockListPage(
			@RequestParam(defaultValue = "1") int pageNum
			,@RequestParam(defaultValue = "") String searchType
			, @RequestParam(defaultValue = "") String keyword
			, Model model) {
		
		System.out.println("재고 조회 목록 페이지처리");
		
		int listLimit = 10; // 한 페이지에서 표시할 게시물 목록을 10개로 제한
		int listCount = service.getStockListCount(searchType, keyword);
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


	/////재고 상세 조회 = 재고 이력 조회
	@RequestMapping(value = "/StockDetail", method = RequestMethod.GET)
	public String stockDetail(String stockNo, Model model) {

		System.out.println("재고 상세 조회");
		
		StockVO stock = service.getStockDetail(stockNo);
		
		model.addAttribute("stock",stock);
	
		return "stock/stock_detail";
	}

	/////재고 수정
	@RequestMapping(value = "/StockMovePro", method = RequestMethod.POST)
	public String stockEditPro(StockVO updateStock, Model model, HttpSession session) {
		System.out.println("재고 수정 작업");

		int updateCount=0;
		
		if(updateCount >0) {
			return "redirect:/StockListForm";
		} else {
			model.addAttribute("msg", "재고 수정 실패!");
			return "fail_back";
		}

	}

	
	


}
