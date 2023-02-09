package com.itwillbs.team1_final.svc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.team1_final.mapper.StockMapper;
import com.itwillbs.team1_final.vo.StockVO;
import com.itwillbs.team1_final.vo.WhVO;

@Service
@Transactional
public class StockService {

	@Autowired
	StockMapper mapper;

	public ArrayList<StockVO> getStockList(String searchType, String keyword, int startRow, int listLimit) {

		String search = "";
		if(searchType!=null && !searchType.equals("") ) {
			search = "WHERE " + searchType + " LIKE '%" + keyword + "%'";
		}
		return mapper.selectStockList(search,startRow,listLimit);
	}
	public int getStockListCount(String searchType, String keyword) {

		String search = "";
		if(searchType!=null && !searchType.equals("") ) {
			search = "WHERE " + searchType + " LIKE '%" + keyword + "%'";
		}
		return mapper.selectStockListCount(search);
	}
	public ArrayList<StockVO> getStockDetail(String stockNo) {
		return mapper.selectStockDetail(stockNo);
	}

	public ArrayList<WhVO> getWhList(String searchType, String keyword, int startRow, int listLimit) {
		String search = "";
		if(searchType!=null && !searchType.equals("") ) {
			search = "WHERE " + searchType + " LIKE '%" + keyword + "%'";
		}
		return mapper.selectWhList(search,startRow,listLimit);
	}
	
	public int putStockHistory(StockVO stock) {
		return mapper.insertStockHistory(stock);
	}
	public int inStock(StockVO stock) {
		return mapper.insertStock(stock);
	}
	public int getNewStockCD(int product_cd, int target_loc) {
		///해당 위치에 같은 제품 있는지 확인
		Integer isExistStock = mapper.isExistStock(product_cd, target_loc);
		if(isExistStock==null) {
			///없으면 max값으로 리턴
			return mapper.selectNewStockCD();
		} else {
			return isExistStock;
		}
	}
	public int outStock(StockVO stock) {
		return mapper.updateOutStock(stock);
	}
	public boolean updateQTY(StockVO stock) {
		System.out.println("================");
		System.out.println(stock.getPRODUCT_CD()+"-"+stock.getMOVE_QTY()+"-"+stock.getOUT_SCHEDULE_QTY());
		if(mapper.updateOutSchStock(stock)>0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	

}
