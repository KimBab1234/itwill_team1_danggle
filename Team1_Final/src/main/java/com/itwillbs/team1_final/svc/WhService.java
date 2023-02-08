package com.itwillbs.team1_final.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.WhMapper;
import com.itwillbs.team1_final.vo.StockVO;
import com.itwillbs.team1_final.vo.WhVO;


@Service
public class WhService {
	@Autowired
	private WhMapper mapper;

	public List<WhVO> getWhList(String searchType, String keyword, int startRow, int listLimit) {

		return mapper.selectWhList(searchType,keyword,startRow,listLimit);
	}

	public int getWhListCount(String searchType, String keyword) {
		
		return mapper.selectWhListCount(searchType,keyword);
	}

	public int codeCheck(String WH_CD) {

		return mapper.selectWhCode(WH_CD);
	}

	public int registWh(WhVO wh) {

		return mapper.insertWhCode(wh);
	}

	public WhVO getWh(String WH_CD) {

		return mapper.selectWh(WH_CD);
	}

	public int removeWh(String WH_CD) {

		return mapper.deleteWh(WH_CD);
	}

	public int modifyWh(WhVO wh) {

		return mapper.updateWh(wh);
	}
	
	public int registWhArea(WhVO wh) {

		return mapper.insertWhArea(wh);
	}


//	public List<WhVO> selectWhArea(String WH_CD) {
//		
//		return mapper.selectWhArea(WH_CD);
//	}


	public int registWhLocation(WhVO wh) {

		return mapper.insertWhLocation(wh);
	}

	public List<WhVO> getWhLocationList(int WH_AREA_CD) {
		
		return mapper.selectWhLocation(WH_AREA_CD);
	}

	public int removeWhArea(int wh_AREA_CD) {
		return mapper.deleteWhArea(wh_AREA_CD);
	}

	public WhVO getWhArea(WhVO wh) {

		return mapper.selectWhArea(wh);
	}

	public int modifyWhArea(WhVO wh) {
		// TODO Auto-generated method stub
		return mapper.updateWhArea(wh);
	}

	public int removeWhLocation(String wh_LOC_IN_AREA_CD) {
		// TODO Auto-generated method stub
		return mapper.deleteWhLocation(wh_LOC_IN_AREA_CD);
	}

	public int modifyWhLocation(WhVO wh) {
		// TODO Auto-generated method stub
		return mapper.updateWhLocation(wh);
	}

	public List<StockVO> getStockList() {
		// TODO Auto-generated method stub
		return mapper.selectStockList();
	}
}
