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
	public StockVO getStockDetail(String stockNo) {
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
	public int putStock(StockVO stock) {
		return mapper.insertStock(stock);
	}
	public int getNewStockCD() {
		return mapper.selectNewStockCD();
	}
	
	
	
	

}
