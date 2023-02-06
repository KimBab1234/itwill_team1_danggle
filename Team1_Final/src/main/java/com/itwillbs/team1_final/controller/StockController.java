package com.itwillbs.team1_final.controller;

import java.sql.Date;
import java.time.LocalDate;
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

		int insertCount = 0;
		int updateCount = 0;
		int productCount = updateStock.getPRODUCT_CD_Arr().length;
		String control_cd = updateStock.getSTOCK_CONTROL_TYPE_CD();

		for(int i=0; i<productCount; i++) {


			////일단 들어온 데이터 저장하기
			StockVO stock = new StockVO();
			stock.setSTOCK_CONTROL_TYPE_CD(updateStock.getSTOCK_CONTROL_TYPE_CD());
			stock.setPRODUCT_CD(updateStock.getPRODUCT_CD_Arr()[i]);
			stock.setMOVE_QTY(updateStock.getMOVE_QTY_Arr()[i]);
			stock.setEMP_NUM(updateStock.getEMP_NUM());
			stock.setSTOCK_DATE(updateStock.getSTOCK_DATE_Arr()[i]);
			stock.setREMARKS(updateStock.getREMARKS_Arr()[i]);
			System.out.println(stock);
			boolean isSuccess = false;
			if(control_cd.equals("0")) { /////입고
				
			} else if(control_cd.equals("1")) { ///출고
			
				
			} else if(control_cd.equals("2")) { /// 조정
			
				isSuccess = inStock(updateStock, stock, 2, i);
			
			} /////여기까지 정보 입력 완료

			if(!isSuccess) {
				model.addAttribute("msg", "입출고 수정 실패");
				return "fail_back";
			}
			
		} ////for문 작업 끝, 다음 품목으로

		return ""; ////모두 성공했을때 어디로 갈지?
				
	} /////재고조정 메서드 끝

	
	/////입고 처리 
	public boolean inStock(StockVO updateStock, StockVO stock, int control, int i) {
		

		int target_stockNo = service.getNewStockCD();///일단 재고번호 max값 가져와주기
		stock.setWH_LOC_IN_AREA_CD(updateStock.getWH_LOC_IN_AREA_CD_Arr()[i]); ///도착지는 지정한 위치
		stock.setSTOCK_CD(target_stockNo); ///일단 저장해놓고 있으면 안쓰고 없으면 쓰기
		if(control==0) {
			stock.setSOURCE_STOCK_CD(null); ////입고는 출발지 없음
		} else { ///조정은 출발지가 있음
			stock.setSTOCK_CD(updateStock.getSTOCK_CD_Arr()[i]);
		}
		int insertCount = service.putStock(stock);
		
		/////모든 정보 입력 완료되면 P/F 판별하기
		if(insertCount==0) {
			return false;
		} else {
			///성공했으면 history 남기기
			if(control==2) {
			}
			if(service.putStockHistory(stock) == 0) {
				return false;
			}
		}
		return true;
	}
	
	/////출고 처리 
	public int outStock(StockVO updateStock,  StockVO stock, int control, int i) {
		
		stock.setSTOCK_CD(updateStock.getSTOCK_CD_Arr()[i]);
		stock.setSOURCE_STOCK_CD(updateStock.getSTOCK_CD_Arr()[i]);
		stock.setMOVE_QTY((-1)*stock.getMOVE_QTY()); ///출고는 QTY 빼줘야함
		
		if(control==1) {
			stock.setTARGET_STOCK_CD(null); ///출고는 도착지 없음
		} else { ///출고가 아니니까 이동 수정, 다른 점은 도착지가 있음
			stock.setTARGET_STOCK_CD(null);
		}
		
//		updateCount = service.updateStock(stock);
		
		
		
		return 0;
	}
	
	
	
}
