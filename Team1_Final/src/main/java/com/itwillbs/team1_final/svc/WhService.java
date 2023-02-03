package com.itwillbs.team1_final.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.team1_final.mapper.WhMapper;
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


	public List<WhVO> selectWhArea(String wH_CD, String wH_AREA, String wH_AREA_CD) {
		
		return mapper.selectWhArea(wH_CD,wH_AREA,wH_AREA_CD);
	}

	public List<WhVO> getWhLocationList(String wH_AREA_CD, String wH_LOC_IN_AREA, String wH_LOC_IN_AREA_CD) {
		
		return mapper.selectLocationList(wH_AREA_CD,wH_LOC_IN_AREA,wH_LOC_IN_AREA_CD);
	}

	public int registWhLocation(WhVO wh) {

		return mapper.insertWhLocation(wh);
	}
}
